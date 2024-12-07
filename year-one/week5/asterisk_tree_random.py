import random
n = int(input('Type a number: '))

if n % 2 == 0:
    n+=1
    print(f"{n}.")
def rnd_char():
    return random.choice(['*', '0', '#'])

for i in range(1, n+1, 2):
    row = [rnd_char() for _ in range(i)]
    print(" " * ((n-i)//2)+" ".join(row))
for i in range(n-2,0,-2):
    row = [rnd_char() for _ in range(i)]
    print(" " * ((n-i)//2)+" ".join(row))
     
