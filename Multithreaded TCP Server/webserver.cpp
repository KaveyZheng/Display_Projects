// C++ libraries
#include <iostream>     // std in and out
#include <string>       // string manipulation
#include <fstream>      // file in and out

// C libraries
#include <stdio.h>      // c std in and out
#include <stdlib.h>     // c std library
#include <string.h>     // c string manipulation

// Socket 
#include <sys/socket.h> // socket API
#include <sys/types.h>  // more data types for processes, sizes, etc
#include <netinet/in.h> // IP protocol
#include <arpa/inet.h>  // IP protocol
#include <netdb.h>      // DNS

// OS 
#include <unistd.h>     // OS API
#include <sys/uio.h>    // read and write buffers
#include <sys/time.h>   // get time
#include <sys/wait.h>   // wait and signal handling
#include <fcntl.h>      // file manipulation 

// Threads
#include <thread>       // multithreading
#include <vector>       // vectors to keep track of threads
#include <mutex>        // synchronization

using namespace std;
using namespace chrono;

void udp_server(int port) {
    // server buffer
    char msg[1024];
     
    // UDP server socket
    sockaddr_in servAddr, cliAddr;
    bzero((char*)&servAddr, sizeof(servAddr));
    bzero((char*)&cliAddr, sizeof(cliAddr));
    servAddr.sin_family = AF_INET;
    servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAddr.sin_port = htons(port);

    // server socket descriptor
    int serverSD = socket(AF_INET, SOCK_DGRAM, 0);
    if(serverSD < 0)
    {
        cerr << "Socket error!" << endl;
        exit(EXIT_FAILURE);
    }
    
    // bind socket
    int bindSock = ::bind(serverSD, (const struct sockaddr *)&servAddr, sizeof(servAddr));
    if(bindSock < 0)
    {
        cerr << "Bind error!" << endl;
        exit(EXIT_FAILURE);
    }

    // terminate program after 30 seconds of no client pings
    struct timeval timeout;
    timeout.tv_sec = 30;
    timeout.tv_usec = 0;   
    setsockopt(serverSD,SOL_SOCKET,SO_RCVTIMEO,&timeout,sizeof(timeout)); 

    while (1)
    {

        cout << "Wait for client message..." << endl;
        memset(&msg, 0, sizeof(msg));
        socklen_t len = sizeof(cliAddr);
        int bytes = recvfrom(serverSD, (char *)&msg, sizeof(msg), MSG_WAITALL, (struct sockaddr *)&cliAddr, &len);

        if (bytes<0) break;

        // simulate packet loss
        srand((unsigned)time(NULL));
        int random = rand() % 100;
        if (random < 30) continue;
        // message not lost
        cout << "Client message: " << msg << endl;

        // parse message: ping,seq,timestamp
        int seq;
        long long timestamp;
        sscanf(msg, "ping,%d,%lld", &seq, &timestamp);

        // prepare response message
        auto start = duration_cast<nanoseconds>(high_resolution_clock::now().time_since_epoch()).count();
        snprintf(msg, sizeof(msg), "echo,%d,%lld", seq, start);
        // send the server response message
        bytes = sendto(serverSD, msg, strlen(msg), 0, (const struct sockaddr *)&cliAddr, sizeof(cliAddr));

    }
    close(serverSD);
    cout << "Server UDP Pinger Timed Out" << endl;
}

int server_socket;
vector<thread> threads; // vector of all running threads
mutex thread_mutex;     // mutex lock

void terminate(int signal) {
  cout << "\nctrl+c -> closing server\n";
  thread_mutex.lock();
  for (auto &t : threads) {
    if (t.joinable()) {
      cout << t.get_id() << " joined" << endl;
      t.join();
    }
  }
  thread_mutex.unlock();
  close(server_socket);
  exit(0);
}

void handle_client(int client_socket) {
  // receive HTTP request
  char buffer[2048]; 
  memset(&buffer, 0, sizeof(buffer));
  ssize_t bytes_received = recv(client_socket, buffer, sizeof(buffer), 0);
  if (bytes_received<0) {
    cerr << "\nin request\n";
    close(client_socket);
    return;
  }

  // parse request
  // reference: CS428 "HTTP Request and Response Message Format"
  string request(buffer, bytes_received);
  size_t first_space = request.find(' ');
  string method = request.substr(0, first_space);
  size_t second_space = request.find(' ', first_space+1);
  string path = request.substr(first_space+1, second_space-first_space-1);
  cout << "Received " << method << " request for " << path << endl;
    
  if (method == "GET") {
    string file_path = '.' + path;
    ifstream file(file_path, ios::binary);
    if (file) {
      
      // handle extension
      // reference: https://stackoverflow.com/questions/22881934/c-extract-string-after-dot 
      string type;
      size_t dot_pos = path.find_last_of('.');
      if (dot_pos != string::npos) {
        string extension = path.substr(dot_pos);
        if (extension == ".html") { type = "text/html"; } 
        else if (extension == ".pdf") { type = "application/pdf"; } 
        else if (extension == ".jpg") { type = "image/jpeg"; }
      }
      
      // response format
      // reference: CS428 "HTTP Request and Response Message Format"
      string response = "HTTP/1.1 200 OK\r\nContent-Type: " + type;
      if (type == "text/html") { response += "; charset=utf-8"; }  
      response += "\r\n\r\n";
      response += string((istreambuf_iterator<char>(file)), istreambuf_iterator<char>());
      send(client_socket, response.c_str(), response.length(), 0);
    } else {
      string response = "HTTP/1.1 404 Not Found\r\n\r\n404 Not Found";
      send(client_socket, response.c_str(), response.length(), 0);
    }
  }
  close(client_socket);  
}

int main(int argc, char *argv[]) {
  // ctrl+c terminates program and all threads
  signal(SIGINT, terminate);

  int tcp_port;
  int udp_port;
  if (argc == 3) { 
    tcp_port = atoi(argv[1]); 
    udp_port = atoi(argv[2]);
  }
  else {
    cout << "\nin port\n";
    return 1;
  }

  // Create a thread for the UDP server
  thread udp_thread(udp_server, udp_port);
  threads.push_back(::move(udp_thread));

  // server socket creation
  // reference: https://github.com/bozkurthan/Simple-TCP-Server-Client-CPP-Example/blob/master/tcp-Server.cpp
  // welcome socket
  server_socket = socket(AF_INET, SOCK_STREAM, 0);
  if (server_socket<0) { cout<<"\nin socket\n"; return -1; }

  // socket addr
  sockaddr_in serv_addr;
  memset(&serv_addr, 0, sizeof(serv_addr));
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(tcp_port);

  // reuse socket
  // reference: https://stackoverflow.com/questions/6380057/address-already-in-use-error-when-binding-a-socket-in-python 
  // reference: https://stackoverflow.com/questions/59330934/c-socket-problems-random-client-address-and-port-number
  int opt = 1;
  if (setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt))<0) {
    perror("setsockopt failed");
    exit(EXIT_FAILURE);
  }

  // bind socket to addr
  int bind_status = ::bind(server_socket, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
  if (bind_status<0) {
    perror("bind failed");
    return -1;
  }

  // server begin listening for incoming TCP request (10 threads)
  int listen_status = listen(server_socket, 10);
  if (listen_status<0) { cout << "\nin listen\n"; return -1; }
  cout << "Server listening to port " << tcp_port << endl;

  // server loops and waits for incoming request
  while (true) {

    // client socket creation
    // reference: https://github.com/bozkurthan/Simple-TCP-Server-Client-CPP-Example/blob/master/tcp-Server.cpp
    // accept incoming connection
    sockaddr_in client_addr;
    socklen_t client_addr_len = sizeof(client_addr);
    int client_socket = accept(server_socket, (sockaddr*)&client_addr, &client_addr_len);
    if (client_socket<0) { 
      cout<<"\nin connection\n"; 
      close(client_socket);
      continue;
    }

    thread client_thread(handle_client, client_socket);
    thread_mutex.lock();
    // identify connecting client addr & port
    // reference: https://stackoverflow.com/questions/59330934/c-socket-problems-random-client-address-and-port-number
    cout << "Accepted connection from " << inet_ntoa(client_addr.sin_addr) << ":" << ntohs(client_addr.sin_port) << endl;
    // pushing back thread
    // reference: http://earlh.com/blog/2014/03/24/c-plus-plus-is-horrific/
    // reference: https://stackoverflow.com/questions/17674156/moving-stdthread
    threads.push_back(::move(client_thread));
    thread_mutex.unlock();

  }
  thread_mutex.lock();
  for (auto &t : threads) {
    if (t.joinable()) {
      cout << t.get_id() << " joined" << endl;
      t.join();
    }
  }
  thread_mutex.unlock();
  close(server_socket);
  cout << "******Session******" << endl;
  cout << "Connection closed..." << endl;
  return 0;
}