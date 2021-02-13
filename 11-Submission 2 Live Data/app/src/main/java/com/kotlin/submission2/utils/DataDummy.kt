package com.kotlin.submission2.utils

import com.kotlin.submission2.R
import com.kotlin.submission2.data.DataEntity

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
object DataDummy {

    fun generateDummyMovies(): List<DataEntity> {
        val movies = ArrayList<DataEntity>()

        movies.add(
            DataEntity(
                "MOVIE_1",
                "Jumanji: The Next Level",
                "As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world’s most dangerous game.",
                "Adventure, Comedy, Fantasy",
                "2019",
                "6.7",
                "Jake Kasdan",
                "Dwayne Johnson, jake Black, Kevin hart",
                58f,
                "981 user | 244 critic",
                "251",
                R.drawable.bg_jumanji,
                R.drawable.poster_jumanji
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_2",
                "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Fantasy, Action, Adventure",
                "2020",
                "5.4",
                "Patty Jenkins",
                "Gal Gadot, Chris Pine, Kristen Wiig",
                60f,
                "5,960 user | 310 critic",
                "9",
                R.drawable.bg_wonder_woman,
                R.drawable.poster_wonder_woman
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_3",
                "Parasite",
                "All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                "Comedy, Thriller, Drama",
                "2019",
                "8.6",
                "Bong Joon Ho",
                "Kang-ho Song, Lee Sun-kyun, Cho Yeo-jeong",
                96f,
                "2,970 user | 568 critic",
                "39",
                R.drawable.bg_parasite,
                R.drawable.poster_parasite
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_4",
                "Godzilla: King of the Monsters",
                "Follows the heroic efforts of the crypto-zoological agency Monarch as its members face off against a battery of god-sized monsters, including the mighty Godzilla, who collides with Mothra, Rodan, and his ultimate nemesis, the three-headed King Ghidorah. When these ancient super-species, thought to be mere myths, rise again, they all vie for supremacy, leaving humanity's very existence hanging in the balance.",
                "Science Fiction, Action",
                "2019",
                "6.0",
                "Michael Dougherty",
                "Kyle Chandler, Vera Farmiga, Millie Bobby Brown",
                48f,
                "2,223 user | 378 critic",
                "16",
                R.drawable.bg_godzilla,
                R.drawable.poster_godzilla
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_5",
                "Ashfall",
                "Stagnant since 1903, at an elevation of 9000', a volcano erupts on the mythical and majestic Baekdu Mountain.",
                "Action, Adventure, Thriller",
                "2019",
                "6.2",
                "Byung-seo Kim, Hae-jun Lee",
                "Lee Byung-Hun, Jung-woo Ha, Hye-jin Jeon",
                0f,
                "27 user | 33 critic",
                "3,229",
                R.drawable.bg_ashfall,
                R.drawable.poster_ashfall
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_6",
                "Birds of Prey",
                "Harley Quinn joins forces with a singer, an assassin and a police detective to help a young girl who had a hit placed on her after she stole a rare diamond from a crime lord.",
                "Action, Crime",
                "2020",
                "6.1",
                "Cathy Yan",
                "Margot Robbie, Rosie Perez, Mary Elizabeth Winstead",
                60f,
                "2,393 user | 389 critic",
                "181",
                R.drawable.bg_bird_of_prey,
                R.drawable.poster_bird_of_prey
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_7",
                "Tenet",
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "Action, Thriller, Science Fiction",
                "2020",
                "7.5",
                "Christopher Nolan",
                "John David Washington, Robert Pattinson, Elizabeth Debicki",
                69f,
                "4,288 user | 450 critic",
                "8",
                R.drawable.bg_tenet,
                R.drawable.poster_tenet
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_8",
                "Extraction",
                "Tyler Rake, a fearless mercenary who offers his services on the black market, embarks on a dangerous mission when he is hired to rescue the kidnapped son of a Mumbai crime lord.",
                "Drama, Action, Thriller",
                "2020",
                "6.7",
                "Sam Hargrave",
                "Chris Hemsworth, Bryon Lerum, Ryder Lerum",
                56f,
                "3,005 user | 230 critic",
                "246",
                R.drawable.bg_extraction,
                R.drawable.poster_extraction
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_9",
                "Joker",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "Crime, Thriller, Drama",
                "2019",
                "8.5",
                "Todd Phillips",
                "Joaquin Phoenix, Robert De Niro, Zazie Beetz",
                59f,
                "10,777 user | 719 critic",
                "82",
                R.drawable.bg_joker,
                R.drawable.poster_joker
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_10",
                "Bloodshot",
                "After he and his wife are murdered, marine Ray Garrison is resurrected by a team of scientists. Enhanced with nanotechnology, he becomes a superhuman, biotech killing machine—'Bloodshot'. ",
                "Action, Science Fiction",
                "2020",
                "5.7",
                "Dave Wilson",
                "Vin Diesel, Eiza González, Sam Heughan",
                44f,
                "787 user | 203 critic",
                "666",
                R.drawable.bg_bloodshot,
                R.drawable.poster_bloodshot
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_11",
                "Kong: Skull Island",
                "Explore the mysterious and dangerous home of the king of the apes as a team of explorers ventures deep inside the treacherous, primordial island.",
                "Action, Adventure, Fantasy",
                "2017",
                "6.6",
                "Jordan Vogt-Roberts",
                "Tom Hiddleston, Samuel L. Jackson, Brie Larson",
                62f,
                "976 user | 591 critic",
                "22",
                R.drawable.bg_kong_skull_island,
                R.drawable.poster_kong_skull_island
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_12",
                "Hobbs & Shaw",
                "Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have swapped smacks and bad words. But when cyber-genetically enhanced anarchist Brixton's ruthless actions threaten the future of humanity, both join forces to defeat him.",
                "Action, Adventure, Comedy",
                "2019",
                "6.4",
                "David Leitch",
                "Dwayne Johnson, Jason Statham, Idris Elba",
                60f,
                "1,272 user | 312 critic",
                "307",
                R.drawable.bg_hobbs_shaw,
                R.drawable.poster_hobbs_shaw
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_13",
                "The New Mutant",
                "Five young mutants, just discovering their abilities while held in a secret facility against their will, fight to escape their past sins and save themselves.",
                "Action, Horror, Science Fiction",
                "2020",
                "5.3",
                "Josh Boone",
                "Maisie Williams, Anya Taylor-Joy, Charlie Heaton",
                43f,
                "1,064 user | 178 critic",
                "121",
                R.drawable.bg_the_new_mutant,
                R.drawable.poster_the_new_mutant
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_14",
                "Greenland",
                "John Garrity, his estranged wife and their young son embark on a perilous journey to find sanctuary as a planet-killing comet hurtles toward Earth. Amid terrifying accounts of cities getting levelled, the Garrity's experience the best and worst in humanity.",
                "Action, Thriller",
                "2020",
                "6.4",
                "Ric Roman Waugh",
                "Gerard Butler, Morena Baccarin, Roger Dale Floyd",
                61f,
                "817 user | 125 critic",
                "60",
                R.drawable.bg_greenland,
                R.drawable.poster_greenland
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_15",
                "Underwater",
                "After an earthquake destroys their underwater station, six researchers must navigate two miles along the dangerous, unknown depths of the ocean floor to make it to safety in a race against time.",
                "Action, Horror, Science Fiction, Thriller",
                "2020",
                "5.8",
                "William Eubank",
                "Kristen Stewart, Vincent Cassel, Mamoudou Athie",
                48f,
                "1,000 user | 248 critic",
                "324",
                R.drawable.bg_underwater,
                R.drawable.poster_underwater
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_16",
                "Avengers: Endgame",
                "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                "Adventure, Science Fiction, Action",
                "2019",
                "8.4",
                "Anthony Russo, Joe Russo",
                "Robert Downey Jr., Chris Evans, Mark Ruffalo",
                78f,
                "9,012 user | 571 critic",
                "26",
                R.drawable.bg_avenger_end_game,
                R.drawable.poster_avenger_end_game
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_17",
                "Mulan",
                "When the Emperor of China issues a decree that one man per family must serve in the Imperial Chinese Army to defend the country from Huns, Hua Mulan, the eldest daughter of an honored warrior, steps in to take the place of her ailing father. She is spirited, determined and quick on her feet.",
                "Adventure, Fantasy",
                "2020",
                "5.6",
                "Niki Caro",
                "Yifei Liu, Donnie Yen, Gong Li",
                66f,
                "2,781 user | 242 critic",
                "69",
                R.drawable.bg_mulan,
                R.drawable.poster_mulan
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_18",
                "6 Underground",
                "After faking his death, a tech billionaire recruits a team of international operatives for a bold and bloody mission to take down a brutal dictator.",
                "Action, Thriller",
                "2019",
                "6.1",
                "Michael Bay",
                "Ryan Reynolds, Mélanie Laurent, Manuel Garcia-Rulfo",
                41f,
                "2,391 user | 119 critic",
                "390",
                R.drawable.bg_6_underground,
                R.drawable.poster_6_underground
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_19",
                "Spider-Man: Far From Home",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "Action, Adventure, Science Fiction",
                "2019",
                "7.5",
                "Jon Watts",
                "Tom Holland, Samuel L. Jackson, Jake Gyllenhaal",
                59f,
                "2,090 user | 423 critic",
                "269",
                R.drawable.bg_spiderman,
                R.drawable.poster_spiderman
            )
        )
        movies.add(
            DataEntity(
                "MOVIE_20",
                "Venom",
                "Investigative journalist Eddie Brock attempts a comeback following a scandal, but accidentally becomes the host of Venom, a violent, super powerful alien symbiote.",
                "Science Fiction, Action",
                "2018",
                "6.7",
                "Ruben Fleischer",
                "Tom Hardy, Michelle Williams, Riz Ahmed",
                35f,
                "3,706 user | 415 critic",
                "570",
                R.drawable.bg_venom,
                R.drawable.poster_venom
            )
        )
        return movies
    }

    fun generateDummyTvSeries(): List<DataEntity> {
        val tvShows = ArrayList<DataEntity>()

        tvShows.add(
            DataEntity(
                "TV_SHOW_1",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Sci-Fi & Fantasy, Mystery, Comedy, Drama",
                "2021",
                "7.8",
                "Jac Schaeffer",
                "Elizabeth Olsen, Paul Bettany, Teyonah Parris",
                0f,
                "1,183 user | 5 critic",
                "1",
                R.drawable.bg_wanda_vision,
                R.drawable.poster_wanda_vision
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_2",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "Drama",
                "2017",
                "8.2",
                "David Shore",
                "Freddie Highmore, Antonia Thomas, Hill Harper",
                0f,
                "541 user | 10 critic",
                "57",
                R.drawable.bg_good_doctor,
                R.drawable.poster_good_doctor
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_3",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Mystery, Drama, Crime",
                "2017",
                "6.9",
                "Roberto Aguirre-Sacasa",
                "K.J. Apa, Lili Reinhart, Camila Mendes",
                0f,
                "877 user | 48 critic",
                "46",
                R.drawable.bg_riverdale,
                R.drawable.poster_riverdale
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_4",
                "The Mandalorian",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "Sci-Fi & Fantasy, Action & Adventure",
                "2019",
                "8.8",
                "Jon Favreau",
                "Pedro Pascal, Gina Carano, Giancarlo Esposito",
                0f,
                "2,671 user | 97 critic",
                "10",
                R.drawable.bg_mandalorian,
                R.drawable.poster_mandalorian
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_5",
                "Cobra kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo.",
                "Action & Adventure, Drama",
                "2018",
                "8.6",
                "Unnamed",
                "Ralph Macchio, William Zabka, Xolo Maridueña",
                0f,
                "1,895 user | 112 critic",
                "6",
                R.drawable.bg_cobra_kai,
                R.drawable.poster_cobra_kai
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_6",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals.",
                "Crime, Sci-Fi & Fantasy",
                "2016",
                "8.1",
                "Tom Kapinos",
                "Tom Ellis, Lauren German, Kevin Alejandro",
                0f,
                "1,170 user | 60 critic",
                "48",
                R.drawable.bg_lucifer,
                R.drawable.poster_lucifer
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_7",
                "Snowpiercer",
                "Set more than seven years after the world has become a frozen wasteland, the remnants of humanity inhabit a gigantic, perpetually-moving train that circles the globe as class warfare, social injustice and the politics of survival play out.",
                "Sci-Fi & Fantasy",
                "2020",
                "6.8",
                "Josh Friedman, Graeme Manson",
                "Jennifer Connelly, Daveed Diggs, Mickey Sumner",
                0f,
                "582 user | 48 critic",
                "13",
                R.drawable.bg_snow_piercer,
                R.drawable.poster_snow_piercer
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_8",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Drama",
                "2005",
                "7.6",
                "Shonda Rhimes",
                "Ellen Pompeo, Chandra Wilson, James Pickens Jr.",
                0f,
                "562 user | 49 critic",
                "25",
                R.drawable.bg_greys_anatomy,
                R.drawable.poster_greys_anatomy
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_9",
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north.",
                "Sci-Fi & Fantasy, Drama, Action & Adventure, War",
                "2011",
                "9.3",
                "David Benioff, D.B. Weiss",
                "Emilia Clarke, Peter Dinklage, Kit Harington",
                0f,
                "4,732 user | 337 critic",
                "12",
                R.drawable.bg_game_of_thrones,
                R.drawable.poster_game_of_thrones
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_10",
                "The Vampire Diaries",
                "The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.",
                "Drama, Fantasy, Horror, Romance",
                "2009",
                "7.7",
                "Julie Plec, Kevin Williamson",
                "Nina Dobrev, Paul Wesley, Ian Somerhalder",
                0f,
                "586 user | 59 critic",
                "98",
                R.drawable.bg_vampire_diaries,
                R.drawable.poster_vampire_diaries
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_11",
                "The Stand",
                "In a world mostly wiped out by the plague and embroiled in an elemental struggle between good and evil, the fate of mankind rests on the frail shoulders of the 108-year-old Mother Abagail and a handful of survivors.",
                "Sci-Fi & Fantasy, Drama",
                "2020",
                "5.5",
                "Josh Boone, Benjamin Cavell",
                "Whoopi Goldberg, Alexander Skarsgård, James Marsden",
                0f,
                "406 user | 9 critic",
                "20",
                R.drawable.bg_stand,
                R.drawable.poster_stand
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_12",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands.",
                "Family, Animation, Comdey, Drama",
                "1989",
                "8.7",
                "James L. Brooks, Matt Groening, Sam Simon",
                "Dan Castellaneta, Nancy Cartwright, Harry Shearer",
                0f,
                "822 user | 132 critic",
                "128",
                R.drawable.bg_simpson,
                R.drawable.poster_simpson
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_13",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "2010",
                "8.2",
                "Frank Darabont, Angela Kang",
                "Andrew Lincoln, Norman Reedus, Melissa McBride",
                0f,
                "1,962 user | 323 critic",
                "23",
                R.drawable.bg_walking_dead,
                R.drawable.poster_walking_dead
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_14",
                "Smallville",
                "The origins of the world’s greatest hero–from Krypton refugee Kal-el’s arrival on Earth through his tumultuous teen years to Clark Kent’s final steps toward embracing his destiny as the Man of Steel.",
                "Sci-Fi & Fantasy, Action & Adventure, Drama",
                "2001",
                "7.5",
                "Alfred Gough, Miles Millar",
                "Tom Welling, Michael Rosenbaum, Allison Mack",
                0f,
                "462 user | 99 critic",
                "248",
                R.drawable.bg_smallville,
                R.drawable.poster_smallville
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_15",
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers.",
                "Drama, Mystery, Sci-Fi & Fantasy",
                "2005",
                "8.4",
                "Eric Kripke",
                "Jared Padalecki, Jensen Ackles, Jim Beaver",
                0f,
                "1,246 user | 127 critic",
                "32",
                R.drawable.bg_supernatural,
                R.drawable.poster_supernatural
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_16",
                "Money Heist",
                "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose.",
                "Crime, Drama",
                "2017",
                "8.3",
                "Álex Pina",
                "Úrsula Corberó, Álvaro Morte, Itziar Ituño",
                0f,
                "2,437 user | 79 critic",
                "72",
                R.drawable.bg_money_heist,
                R.drawable.poster_money_heist
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_17",
                "Stranger Things",
                "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
                "Sci-Fi & Fantasy, Mystery, Drama",
                "2016",
                "8.7",
                "Matt Duffer, Ross Duffer",
                "Millie Bobby Brown, Finn Wolfhard, Winona Ryder",
                0f,
                "2,459 user | 332 critic",
                "40",
                R.drawable.bg_stranger_things,
                R.drawable.poster_stranger_things
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_18",
                "Breaking Bad",
                "When Walter White, a New Mexico chemistry teacher, is diagnosed with Stage III cancer and given a prognosis of only two years left to live.",
                "Drama",
                "2008",
                "9.5",
                "Vince Gilligan",
                "Bryan Cranston, Aaron Paul, Anna Gunn",
                0f,
                "3,281 user | 174 critic",
                "26",
                R.drawable.bg_breaking_bad,
                R.drawable.poster_breaking_bad
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_19",
                "The Queen's Gambit",
                "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.",
                "Drama",
                "2020",
                "8.6",
                "Scott Frank, Allan Scott",
                "Anya Taylor-Joy, Chloe Pirrie, Bill Camp",
                0f,
                "2,172 user | 106 critic",
                "9",
                R.drawable.bg_queens_gambit,
                R.drawable.poster_queens_gambit
            )
        )
        tvShows.add(
            DataEntity(
                "TV_SHOW_20",
                "Sex Education",
                "Inexperienced Otis channels his sex therapist mom when he teams up with rebellious Maeve to set up an underground sex therapy clinic at school.",
                "Comedy, Drama",
                "2019",
                "8.3",
                "Laurie Nunn",
                "Asa Butterfield, Gillian Anderson, Ncuti Gatwa",
                0f,
                "938 user | 53 critic",
                "135",
                R.drawable.bg_sex_education,
                R.drawable.poster_sex_education
            )
        )
        return tvShows
    }

}