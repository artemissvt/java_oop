import random
def load_users(filename="users.txt"):
    """
    Loads users and passwords from the file and returns them as a dictionary.
    """
    users = {}
    try:
        with open(filename, "r") as file:
            lines = file.readlines()
            for line in lines:
                username, password = line.strip().split(',')
                users[username] = password
    except FileNotFoundError:
        print("No user data file found. Starting fresh.")
    return users


def save_user(username, password, filename="users.txt"):
    """
    Saves a new user to the file.
    """
    with open(filename, "a") as file:
        file.write(f"{username},{password}\n")


def login(users):
    """
    Attempts to log in a user. If the user doesn't exist, offer sign-up.
    """
    print("Welcome to the game!")

    # Ask for username
    username = input("Enter your username: ")

    # Check if the username exists
    if username in users:
        # User exists, ask for password
        password = input("Enter your password: ")
        if users[username] == password:
            print(f"Welcome back, {username}!")
            return username
        else:
            print("Incorrect password.")
            return None
    else:
        # User doesn't exist, offer sign-up
        print(f"Username {username} not found.")
        choice = input("Would you like to sign up (y/n)? ").lower()
        if choice == 'y':
            sign_up(username, users)
            return username
        else:
            print("Goodbye!")
            return None

users = load_users()
username = login(users)  # Now username stores the logged-in username

if username:
    print("Starting the game...\n")
else:
    print("Login failed, exiting the program.")
    exit()


def sign_up(username, users):
    """
    Signs up a new user by adding their username and password to the file.
    """
    password = input("Enter a password: ")
    # Save the new user to the file
    save_user(username, password)
    users[username] = password
    print(f"Account created for {username}!")

# We open the text file with the questions and the answers, and we read the lines:
file = open('quiz.txt', 'r')
lines = file.readlines()

# We shuffle the questions in the file and pick 5 random questions for the player:
random.shuffle(lines)
num_questions = 5
selected_lines = lines[:num_questions]

# Create the quiz data dictionary:
quiz_data = {}

# We create aloop that appends data into the quiz_data dictionary
for line in lines:
    parts = line.strip().split(', ')
    question = parts[0]
    correct_answer = parts[1]
    wrong_answers = parts[2:]

    # We store store them in the dictionary
    quiz_data[question] = {
        'correct_answer': correct_answer,
        'wrong_answers' : wrong_answers
    }

# We initialize the question counter:
question_count = 1
user_cor_ans = 0
# We ask the player the question:
for question, answers in quiz_data.items():
    # If the program has printed 5 questions already, break:
    if question_count >= 6:
        break
    correct_answer = answers['correct_answer']
    wrong_answers = answers['wrong_answers']

    # We combine all answers in order to shuffle them:
    all_answers = [correct_answer] + wrong_answers
    random.shuffle(all_answers)

    # We display the question and then all the possible answers:
    print(f"Question: {question}")
    for i, answer in enumerate(all_answers, 1):
        print(f"{i}. {answer}")

    # We ask the user to pick an answer:
    player_choice = int(input("Pick an answer: 1-4:"))
    if (player_choice) in [1, 2, 3, 4]:
        selected_ans = all_answers[player_choice - 1]

        # Then we check wether the choice player made is correct or not and we print the appropriate message:
        if selected_ans == correct_answer:
            print ("Correct!")
            user_cor_ans += 1
        elif selected_ans in wrong_answers:
            print (f"Wrong answer! Correct answer was: {correct_answer}")
    else:
        print ("Invalid choice. Please choose from 1-4!")
    question_count += 1
    print("\n")
    print(user_cor_ans)
    play_again = input("Do you want to play again?y/n")
    if play_again == 'y':
        continue
    elif play_again == 'n':
        print("Goodbye.")
        break
    else:
        print("Invalid option, please choose from y/n")

def append_scores_to_file(username, user_cor_ans, filename="player_scores.txt"):
    """Appends the player's username and score to a different file."""
    with open(filename, "a") as file:
        file.write(f"{username}, {user_cor_ans}\n")

# After the quiz is done, append the score
append_scores_to_file(username, user_cor_ans)
