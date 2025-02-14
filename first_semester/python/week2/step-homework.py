a = int(input('Type a number(starting point): '))
b = int(input('Type another number(end point): '))
step = int(input('Type another number(step): '))
counter = 0
while True:
    if a <= b:
        print(a)
        for i in range (a,b,step):
            a = a + (step)
#            print (i) 
            print(a)
        break 
    elif a > b:
        print(a)
#        step = step * -1
        for j in range (a,b,-step):
            a = a - (step)
            print (a)
        break
    break