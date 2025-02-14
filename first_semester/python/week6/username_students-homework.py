def generate_usernames_from_csv(file_path):
    """
    Reads student names from a CSV file (without using the `csv` module),
    generates unique usernames, and prints them.
    """
    usernames = []  # List to store final usernames
    username_counts = {}  # Dictionary to track the number of duplicates for each base username

    # Open the CSV file and read its lines
    with open(file_path, 'r') as file:

        # Skip the header
        lines = file.readlines()[1:]
        
        # Extract student names
        students = [line.strip() for line in lines]

    # Generate unique usernames
    for student in students:
        first_name, last_name = student.split()
        base_username = f"{first_name[0].lower()}{last_name.lower()}"

        if base_username in username_counts:
            username_counts[base_username] += 1
            unique_username = f"{base_username}{username_counts[base_username]}"
        else:
            username_counts[base_username] = 0
            unique_username = base_username

        usernames.append(unique_username)

    # Print the generated usernames
    print("Generated Usernames:")
    for username in usernames:
        print(username)

    return usernames


# Run the program with the CSV file
file_path = 'data.csv'
generate_usernames_from_csv(file_path)
