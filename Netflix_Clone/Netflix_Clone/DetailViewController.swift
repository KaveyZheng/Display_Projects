//
//  DetailViewController.swift
//  Flixster
//
//  Created by Kavey Zheng on 3/3/23.
//

import UIKit
import Nuke

class DetailViewController: UIViewController {
    
    var movie: Movie!
    
    @IBOutlet weak var movieImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var voteAverageLabel: UILabel!
    @IBOutlet weak var voteCountLabel: UILabel!
    @IBOutlet weak var adultLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var overviewLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        let url = URL(string: "https://image.tmdb.org/t/p/w500" + movie.poster_path.absoluteString)
        Nuke.loadImage(with: url!, into: movieImageView)
        
        titleLabel.text = movie.title
        voteAverageLabel.text = "\(movie.vote_average) Rating"
        voteCountLabel.text = "\(movie.vote_count) Votes"
        dateLabel.text = "Released " + movie.release_date
        overviewLabel.text = movie.overview
        
        if movie.adult { adultLabel.text = "Viewer Discretion Advised" }
        else { adultLabel.text = "Family Friendly" }
    }
}


