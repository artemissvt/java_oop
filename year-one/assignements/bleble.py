import random

# Object list for the game
objectlist = ['rock', 'paper', 'scissors']

# Initialize counters
user_score = 0
computer_score = 0

# Function to append scores to a text file
def append_scores_to_file(username, datetime, user_score, computer_score):
    with open('scores_log.txt', 'a') as file:
        file.write(f"Player ({username}) picked: {user_score}, Computer picked: {computer_score}\n")
        file.write(f"Datetime: {datetime}")
        file.write(f"Total Scores - Player ({username}): {user_score}, Computer: {computer_score}\n")
        file.write("  ") 

# Main game loop
while True:
    # Get player username and choice
    username = input('Type your username: ')
    datetime = input('Type the current date and time: ')
    playerchoice = input('Pick rock, paper, or scissors: ').lower()
    # Computer's turn
    secret = random.choice(objectlist)
    print("Computer's turn...")
    print(f"Computer picked: {secret}")
    def player_vs_computer ():
        if playerchoice == 'rock':
            print("Computer's turn...")
            print()
            print("Computer picked: ", secret)
            if playerchoice == secret:
                print(f"You both picked {playerchoice}, its a tie.")
            elif secret == 'paper':
                print("You lost.")
                computer_score += 1
            elif secret == 'scissors':
                print("You win!")
                user_score += 1
        elif playerchoice == 'paper':
            print("Computer's turn...")
            print()
            print("Computer picked: ", secret)
            if playerchoice == secret:
                print(f"You both picked {secret}, its a tie.")
            elif secret == 'rock':
                print("You win!")
                user_score += 1
            elif secret == 'scissors':
                print("You lost.")
                computer_score += 1
        elif playerchoice == 'scissors':
            print("Computer's turn...")
            print()
            print("Computer picked: ", secret)
            if playerchoice == secret:
                print(f"You both picked {secret}, its a tie.")
            elif secret == 'rock':
                print("You lost."),
                computer_score += 1
            elif secret == 'paper':
                print("You win!")
                user_score += 1
        else:
            print('Wrong, please pick from: rock, paper, scissors')

    
    # Append the result and scores to the text file
    append_scores_to_file(username,datetime,user_score,computer_score)

    # Display scores
    print(f" Current Scores: {username}: {user_score}, computer: {computer_score}")

    # Ask if the player wants to play again
    playagain = input('Do you want to play again? yes/no: ').strip().lower()
    if playagain != 'yes':
        print("\nThanks for playing!")
        break
