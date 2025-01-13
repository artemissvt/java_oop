file = open('usernames_PINS.csv')
lines = file.read()
username = input('Enter usename: ')

for i in range(len(lines)):
    if username in (lines):
        password = input('Enter PIN: ')
        if password in (lines):
            print('Welcome')
        else:
            print ('Wrong PIN.')
    else:
        print('Wrong username.')
    break