file = open('story.txt', 'r')
text = file.read()
file.close()

text = text.lower()
for char in ",.!?;:":
    text = text.replace(char, "")

word_to_search = input("Enter a word to find its occurrences in the story: ").strip().lower()

words = text.split()
count = 0
for word in words:
    if word == word_to_search:
        count += 1

print(f"The word '{word_to_search}' appears {count} time(s) in the story.")