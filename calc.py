def addition(a, b):
    """
    Function that adds two numbers
    """
    return a + b
def subtraction(a, b):
    """
    Function that subtracts two numbers
    """
    return a - b
def multiplication(a, b):
    """
    Function that multiplies two numbers
    """
    return a * b
def division(a, b):
    """
    Function that divides two numbers
    """
    return a / b
def calculator():
    """
    Main function that provides to the user the menu and the functions of the calculator
    """
    print('Simple calculator.')
    print('1. Addition')
    print('2. Subtraction')
    print('3. Multiplication')
    print('4. Division')
    print('5. Exit')

while True:
    choice = (input('Type the number of the desirable calculation: '))
    another = input('Do you want to make another calculation?').lower
    if choice == '5':
        print('Exiting calculator.')
        break
    if choice not in ['1', '2', '3', '4']:
        print('Not an option, try again. ')
        continue
    try:
        num1 = float(input('Type the first number: '))
        num2 = float(input('Type the second number: '))
    except ValueError:
        print('Invalid input. Type only numbers.')
        continue
    if choice == '1':
        print(f"The result is: {addition(num1, num2)}")
    elif choice == '2':
        print(f"The result is: {subtraction(num1, num2)}")
    elif choice == '3':
        print(f"The result is: {multiplication(num1, num2)}")
    elif choice == '4':
        print(f"Th result is: {division(num1, num2)}")
    
    
    print ()

if __name__ == "__main__":
    calculator()
