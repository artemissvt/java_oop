from tkinter import *

root = Tk()

e = Entry(root, width=30, border=5)
e.pack()
e.insert(0, "Enter your name: ")


def my_click():
    hello = "Hello " + e.get()
    my_label = Label(root, text=hello).pack()

my_button = Button(root, text="Enter your name", command=my_click, fg="black",bg="red", padx=20, pady=10).pack()


root.mainloop()