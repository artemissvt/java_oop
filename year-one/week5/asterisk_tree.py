n = int(input("Enter a number for the diamond's width: "))
if n % 2 == 0:
    n += 1
    print(f"The input was even, so it has been adjusted to {n}.")
for i in range(1 , n):
    print(" " * ((n - i) // 2) + "* " * i)
for i in range(n - 2, 0, -2):
    print(" " * ((n - i) // 2) + "* " * i)

