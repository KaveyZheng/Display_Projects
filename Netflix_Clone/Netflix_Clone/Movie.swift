//
//  Movie.swift
//  Flixster
//
//  Created by Kavey Zheng on 2/28/23.
//

import Foundation

struct MoviesResponse: Decodable {
    var results: [Movie]
}

// Movie struct
struct Movie: Decodable {
    let title: String
    let overview: String
    
    let vote_average: Float
    let vote_count: Int
    let release_date: String
    let adult: Bool
    
    let poster_path: URL
    let backdrop_path: URL
}

extension Movie {
    static var mockMovies: [Movie] = [
        Movie(title: "Black Panther: Wakanda Forever",
              overview: "Queen Ramonda, Shuri, M’Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T’Challa’s death.  As the Wakandans strive to embrace their next chapter, the heroes must band together with the help of War Dog Nakia and Everett Ross and forge a new path for the kingdom of Wakanda.",
              vote_average: 7.4,
              vote_count: 3622,
              release_date: "2022-11-09",
              adult: false,
              poster_path: URL(string: "https://image.tmdb.org/t/p/w500/sv1xJUazXeYqALzczSZ3O6nkH75.jpg")!,
              backdrop_path: URL(string: "https://image.tmdb.org/t/p/w500/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg")!),
        
        Movie(title: "Puss in Boots: The Last Wish",
              overview: "Puss in Boots discovers that his passion for adventure has taken its toll: He has burned through eight of his nine lives, leaving him with only one life left. Puss sets out on an epic journey to find the mythical Last Wish and restore his nine lives.",
              vote_average: 8.5,
              vote_count: 4070,
              release_date: "2022-12-07",
              adult: false,
              poster_path: URL(string: "https://image.tmdb.org/t/p/w500/kuf6dutpsT0vSVehic3EZIqkOBt.jpg")!,
              backdrop_path: URL(string: "https://image.tmdb.org/t/p/w500/b1Y8SUb12gPHCSSSNlbX4nB3IKy.jpg")!),
        
        Movie(title: "Ant-Man and the Wasp: Quantumania",
              overview: "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
              vote_average: 6.5,
              vote_count: 766,
              release_date: "2023-02-10",
              adult: false,
              poster_path: URL(string: "https://image.tmdb.org/t/p/w500/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg")!,
              backdrop_path: URL(string: "https://image.tmdb.org/t/p/w500/3JSoB7eMbCd8sE8alxNiUtrNiTz.jpg")!),
        
        Movie(title: "The Whale",
              overview: "A reclusive English teacher suffering from severe obesity attempts to reconnect with his estranged teenage daughter for one last chance at redemption.",
              vote_average: 8,
              vote_count: 526,
              release_date: "2022-12-09",
              adult: false,
              poster_path: URL(string: "https://image.tmdb.org/t/p/w500/jQ0gylJMxWSL490sy0RrPj1Lj7e.jpg")!,
              backdrop_path: URL(string: "https://image.tmdb.org/t/p/w500/r7Dfg9aRZ78gJsmDlCirIIlNH3d.jpg")!),
        
        Movie(title: "Legion of Super-Heroes",
              overview: "Kara, devastated by the loss of Krypton, struggles to adjust to her new life on Earth. Her cousin, Superman, mentors her and suggests she leave their space-time to attend the Legion Academy in the 31st century, where she makes new friends and a new enemy: Brainiac 5. Meanwhile, she must contend with a mysterious group called the Dark Circle as it searches for a powerful weapon held in the Academy’s vault.",
              vote_average: 6.6,
              vote_count: 49,
              release_date: "2023-02-07",
              adult: false,
              poster_path: URL(string: "https://image.tmdb.org/t/p/w500/3whQLi8RI7h2h2Si2KTDFJxfEcR.jpg")!,
              backdrop_path: URL(string: "https://image.tmdb.org/t/p/w500/jhi3K0rN46SSu9wEu6zYVCOeVtH.jpg")!),
    ]
}
