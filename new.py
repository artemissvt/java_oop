#name = input('Whats your name? ').strip().title()
#name.split (" ")
#first, last = name.split(" ")
#print (f"hello, {first}")
#x = int(input('Type a number: '))
#y = int(input('Type another number: '))
#z = round(x / y, 2)
#print (f"{z:,}")
#def main():
#    name = input('Whats your name? ')
#    hello(name) 
#   
#def hello(to="world"):
#    print("hello", to)

#main()
def main():
    x =int(input("whats x? "))
    print("x squared is", square(x))

def square (n):
    return n * n 
main()