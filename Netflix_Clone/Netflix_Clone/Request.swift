//
//  Request.swift
//  Flixster
//
//  Created by Kavey Zheng on 3/7/23.
//

import Foundation

struct Request {
    let session = URLSession.shared
    
    func getResults(completion: @escaping ((Data) -> Void)) {
        let url = URL(string: "https://api.themoviedb.org/3/movie/now_playing?api_key=4839203bdfa64b8ad8c61215968461ea")
        var urlRequest = URLRequest(url: url!)
        
        session.dataTask(with: urlRequest) { data, response, error in
            guard error == nil else { return }
            
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode == 200 {
                    if let confirmedData = data {
                        completion(confirmedData)
                    }
                }
            }
        }.resume()
    }
}

