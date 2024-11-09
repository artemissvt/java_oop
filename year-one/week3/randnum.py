import random
random_numbers = [random.randint(1, 50) for _ in range(100)]
user_number = int(input("Enter a number between 1 and 50: "))
count = 0
for num in random_numbers:
    if num == user_number:
        count += 1
print("The number", str(user_number) ,"appears", str(count) ,"times in the list.")

