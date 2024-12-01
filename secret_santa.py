import random
from tkinter import *
root = Tk()

def name_generator():
    name_list = ['Eugenia', 'Eleni', 'Kelly', 'Artemis']
    rnd = random.randint(0, len(name_list)-1)
    name = name_list[rnd]
    my_label = Label(root, text=name, bg= "#28b463", padx=80, pady=70).pack()
    root.configure(bg="#CD5C5C")


my_button = Button(root, text="Generate name", command=name_generator, fg="black",bg="white", padx=20, pady=10).pack()
root.mainloop()


