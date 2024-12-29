# We open the file with the socres and we read all the lines:
file = open('scores_log.txt', 'r')
lines = file.readlines()

# We create two empty dictionaries that will keep the players' data and wins:
player_wins = {}
data = {}
 
print('Top five players are:')
print( )

# We create a loop that appends data into the data dictionary:
for line in lines: 
    parts = line.strip().split(', ') 
    data['player'] = parts[0]
    data['datetime'] = parts[1]
    data['player_score'] = int(parts[2])
    data['computer_score'] = int(parts[3])

    # We check which player has more than 10 points (one win):
    if (data['player_score']) >= 10:

        # We ensure that player is not on player wins and we initialize the
        if data['player'] not in player_wins: #αρχικοποιηση
            player_wins[data['player']] = 0 
        player_wins[data['player']] += 1

# We sort the players and then we collect the top five:
sorted_players = sorted(player_wins.items(), key=lambda x: x[1], reverse=True) #with key= lamda we collect specific column for sort
top_five_players = sorted_players[:5]

# Lastly, we create a loop that prints the top five players and their wins:
for player, wins in top_five_players:
    print(f"Player: {player}, wins: {wins}")
