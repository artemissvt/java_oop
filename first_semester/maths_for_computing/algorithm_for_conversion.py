def dec_to_n(number, base):
    if base < 2 or base > 36:
        print("Base must be between 2 and 36")
    
    elif number == 0:
        return "0"
    else:
        digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        result = ""
        negative = number < 0
        number = abs(number)
    
        while number > 0:
            remainder = number % base
            result = digits[remainder] + result
            number //= base
    
    if negative:
        result = "-" + result
    
    return result

if __name__ == "__main__":
    num = int(input("Enter a decimal number: "))
    base = int(input("Enter the target base (2-36): "))
    print(f"{num} in base {base} is: {dec_to_n(num, base)}")
