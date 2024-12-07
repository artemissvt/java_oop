import random
objectlist = ['rock','paper','scissors']
secret = random.choice(objectlist)
#print(secret)
counterplayer = 0
countercomputer = 0

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
            score=print("You lost!")
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
    if score == 'You win!':
        counterplayer += 1
    elif score == 'You lost.':
        countercomputer += 1
    print(f"{player} :{counterplayer}, computer: {countercomputer}")
    for j in [i]:
        playagain = input('Do you want to play again? yes/no: ')
        if playagain == 'yes':
            True
        else: break
