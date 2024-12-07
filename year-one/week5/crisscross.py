the_num =int(input('Type a number: '))
patern = ''
for i in range (1, the_num +1):
    if i % 2 == 0:
        patern += '%'
    else: 
        patern += '#'
print (patern)
