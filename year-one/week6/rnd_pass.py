import random
import string
lower_case_letters = random.choice(string.ascii_lowercase)
upper_case_letters = random.choice(string.ascii_uppercase)
digits = [random.randint(0,9) for _ in range(9)]
special_char = string.punctuation
rnd_special_char = random.choice(special_char)

password = [
    random.choice(lower_case_letters),
    random.choice(upper_case_letters),
    random.choice(digits),
    random.choice(rnd_special_char),
]

rnd_pass = [random.choice(password) for _ in range(10)]
print(rnd_pass)