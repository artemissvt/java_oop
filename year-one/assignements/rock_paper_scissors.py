import random
import csv

objectlist = ['rock','paper','scissors']
secret = random.choice(objectlist)
#print(secret)
counterplayer = 0
countercomputer = 0

# Function to append scores to a text file
def append_scores_to_file(player_name, player_choice, computer_choice, result, player_score, computer_score):
    with open('rock_paper_scissors.csv', mode='a') as file:
        file.write(f"Player ({player_name}) picked: {player_choice}, Computer picked: {computer_choice}\n")
        file.write(f"Result: {result}\n")
        file.write(f"Total Scores - Player ({player_name}): {player_score}, Computer: {computer_score}\n")
        file.write("-" * 40 + "\n")  # Separator for each round

for i in (objectlist), True:
    player = input('Type username: ')
    playerchoice = input('Pick rock, paper or scissors: ')
    if playerchoice == 'rock':
        print("Computer's turn...")
        print()
        print("Computer picked: ", secret)
        if playerchoice == secret:
            print(f"You both picked {playerchoice}, its a tie.")
        elif secret == 'paper':
            score=print("You lost.")
        elif secret == 'scissors':
            score=print("You win!")
    elif playerchoice == 'paper':
        print("Computer's turn...")
        print()
        print("Computer picked: ", secret)
        if playerchoice == secret:
            print(f"You both picked {secret}, its a tie.")
        elif secret == 'rock':
            score=print("You win!")
        elif secret == 'scissors':
            score=print("You lost.")
    elif playerchoice == 'scissors':
        print("Computer's turn...")
        print()
        print("Computer picked: ", secret)
        if playerchoice == secret:
            print(f"You both picked {secret}, its a tie.")
        elif secret == 'rock':
            score=print("You lost.")
        elif secret == 'paper':
            score=print("You win!")
    else:
        print('Wrong, please pick from: rock, paper, scissors')
    score=0
#    if score == 'You win!'
#    elif: 
#        score == 'You lost.'
#        print(f"{player} :{counterplayer}, computer: {countercomputer}")
    for j in [i]:
        playagain = input('Do you want to play again? yes/no: ')
        if playagain == 'yes':
            True
        else: break

    # Append the result and scores to the text file
    append_scores_to_file(player, playerchoice, secret, result, counterplayer, countercomputer)

    # Display scores
    print(f"\nCurrent Scores: {player}: {counterplayer}, Computer: {countercomputer}")

    # Ask if the player wants to play again
    playagain = input('Do you want to play again? yes/no: ').strip().lower()
    if playagain != 'yes':
        print("\nThanks for playing!")
        break