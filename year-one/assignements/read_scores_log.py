file = open('scores_log.txt', 'r')
lines = file.readlines()
player_wins = {}
data = {}
for line in lines: 
    parts = line.strip().split(', ') 
    data['player'] = parts[0]
    data['datetime'] = parts[1]
    data['player_score'] = int(parts[2])
    data['computer_score'] = int(parts[3])
    if (data['player_score']) >= 10:
        #print(data['player'], data['datetime'], data['player_score'])
        if data['player'] not in player_wins: #αρχικοποιηση
            player_wins[data['player']] = 0 
        player_wins[data['player']] += 1
    print(player_wins)
#sorted_players = sorted(player_wins.items(), key=lambda x: x[1], reverse=True) #with key= lamda we collect specific column for sort
#top_five_players = sorted_players[:5]
#print(top_five_players)