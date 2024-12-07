file = open('usernames_PINS.csv')
lines = file.read()
username = input('Enter usename: ')

for i in range(len(lines)):
    if username in (lines):
        password = input('Enter password: ')
        if password in (lines):
            print('Welcome')
        else:
            print ('Wrong password.')
    else:
        print('Wrong username.')
    break