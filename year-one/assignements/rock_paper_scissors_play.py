import random

# We create an object list for the game:
objectlist = ['rock', 'paper', 'scissors']

# We initialize counters:
user_score = 0
computer_score = 0

# We create a function that appends scores to a text file:
def append_scores_to_file(username, datetime, user_score, computer_score):
    with open('scores_log.txt', 'a') as file:
        file.write(f"{username}, {datetime}, {user_score}, {computer_score}\n")

# We create another function to compare player and computer choices:
def player_vs_computer(playerchoice, secret, user_score, computer_score):
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
            print("You lost.")
            computer_score += 1
        elif secret == 'paper':
            print("You win!")
            user_score += 1
    else:
        print('Wrong, please pick from: rock, paper, scissors')
    return user_score, computer_score

# We create the main game loop:
while True:
    # We get player's username and choice:
    username = input('Type your username: ')
    datetime = input('Type the current date and time: ')
    playerchoice = input('Pick rock, paper, or scissors: ').lower()

    # Then its computer's turn:
    secret = random.choice(objectlist)
    print(f"\nComputer picked: {secret}")

    # We call the function of that compares player's and computer's choice to determine result:
    user_score, computer_score = player_vs_computer(playerchoice, secret, user_score, computer_score)

    # We append the result and scores to the text file:
    append_scores_to_file(username, datetime, user_score, computer_score)

    # We display the score:
    print(f"Current Scores: {username}: {user_score}, Computer: {computer_score}")

    # We check for winner (whoever gets first at 10 points):
    if user_score == 10:
        print(f"Game completed, {username} won!")
    elif computer_score == 10:
        print(f"Game completed, computer won!")

    # Lastly, we ask if the player wants to play again:
    playagain = input('Do you want to play again? yes/no: ').strip().lower()
    if playagain != 'yes':
        print("\nThanks for playing!")
        break
