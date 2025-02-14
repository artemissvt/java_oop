import random
random_numbers = [random.randint(1,100) for _ in range(20)]
odd_nums = 0
for num in random_numbers:
    if num % 2 != 0:
        odd_nums += 1
print ('Random numbers:', random_numbers)
print ('Even numbers:', odd_nums)

