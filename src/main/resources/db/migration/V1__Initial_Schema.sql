-- Create the `users` table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL
);

-- Create the `leagues` table
CREATE TABLE leagues (
    league_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    logo VARCHAR(255)
);

-- Create the `teams` table
CREATE TABLE teams (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    icon VARCHAR(255),
    league_id INT,
    FOREIGN KEY (league_id) REFERENCES leagues(league_id) ON DELETE CASCADE
);

-- Create the `games` table
CREATE TABLE games (
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    is_live BOOLEAN NOT NULL DEFAULT FALSE,
    score_home_team INT NOT NULL DEFAULT 0,
    score_away_team INT NOT NULL DEFAULT 0,
    user_id INT,
    home_team_id INT,
    away_team_id INT,
    league_id INT,
    start_date VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE SET NULL,
    FOREIGN KEY (home_team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY (away_team_id) REFERENCES teams(team_id) ON DELETE CASCADE,
    FOREIGN KEY (league_id) REFERENCES leagues(league_id) ON DELETE CASCADE
);