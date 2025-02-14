def factorial(n):
    if n == 0 or n == 1:
        # Base case
        return 1
    # Recursive case  
    return n * factorial(n - 1)  

if __name__ == "__main__":
    num = int(input("Enter a number to calculate factorial: "))
    print(f"Factorial of {num} is: {factorial(num)}")
