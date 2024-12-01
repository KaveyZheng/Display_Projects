#include <iostream>     // std in and out
#include <string>       // string manipulation
#include <cstring>      // c string manipulation

#include <sys/socket.h> // socket interface
#include <netinet/in.h> // IP protocol
#include <arpa/inet.h>  // IP protocol

#include <unistd.h>     // OS interface
#include <chrono>       // timer
#include <limits>       

using namespace std;
using namespace chrono;

int main(int argc, char *argv[]) {

    string serverIP = argv[1];
    int serverPort = atoi(argv[2]);

    // server address setup
    sockaddr_in servAddr;
    memset(&servAddr, 0, sizeof(servAddr));
    servAddr.sin_family = AF_INET;
    servAddr.sin_addr.s_addr = inet_addr(serverIP.c_str());
    servAddr.sin_port = htons(serverPort);

    // client UDP socket
    int client_sock = socket(AF_INET, SOCK_DGRAM, 0);
    if (client_sock < 0)
    {
        cerr << "Socket" << endl;
        exit(EXIT_FAILURE);
    }

    // set datagram socket timeout value 
    struct timeval timeout;
    timeout.tv_sec = 1;
    timeout.tv_usec = 0;     
    if (setsockopt(client_sock, SOL_SOCKET, SO_RCVTIMEO, &timeout, sizeof(timeout)) < 0) {
        cerr << "SetSockOpt" << endl;
        exit(EXIT_FAILURE);
    }

    long long mini = numeric_limits<long long>::max();
    long long maxi = numeric_limits<long long>::min();
    int success = 0;
    double lost = 0;
    long long total_time = 0;

    // start program timer
    auto program_start = high_resolution_clock::now();

    char buffer[1024];
    int seq = 0;
    while (true) {

        // terminate program after 3 minutes
        auto curr = high_resolution_clock::now();
        auto elapsed = duration_cast<seconds>(curr - program_start).count();
        if (elapsed >= 180) break;

        // start timer
        auto start = duration_cast<nanoseconds>(high_resolution_clock::now().time_since_epoch()).count();

        // put message into buffer 
        snprintf(buffer, sizeof(buffer), "ping,%d,%lld", seq, start);
        // send buffer to server
        sendto(client_sock, buffer, strlen(buffer), 0, (struct sockaddr *)&servAddr, sizeof(servAddr));

        // wait for response
        socklen_t servLen = sizeof(servAddr);
        int bytes = recvfrom(client_sock, (char*)&buffer, sizeof(buffer), 0, (struct sockaddr *)&servAddr, &servLen);

        // end timer
        auto end = duration_cast<nanoseconds>(high_resolution_clock::now().time_since_epoch()).count();
        auto RTT = end - start;
    
        if (bytes >= 0) {
            cout << buffer << endl;
            cout << "RTT: " << RTT << " ns" << endl;

            mini = (RTT<mini) ? RTT : mini;
            maxi = (RTT>maxi) ? RTT : maxi;
            success++;
            total_time += RTT;
        } else {
            cout << "client ping timed out" << endl;
            lost++;
        }

        seq++;
        sleep(1);

    }

    cout << endl;
    cout << "Minimum RTT: " << mini << " ns" << endl;
    cout << "Maximum RTT: " << maxi << " ns" << endl;
    cout << "Total RTTs: " << success << endl;
    cout << "Packet Loss Rate: " << (lost/seq)*100 << "%" << endl;
    cout << "Average RTT: " << (total_time/success) << " ns" << endl;
    cout << endl;

}