from tkinter import *
root = Tk()
import random
secret = random.choice(['rock', 'paper', 'scissors'])
#print(secret)

def pickobject ():
    pick = Label(root, text='Pick an object: rock, paper, or scissors').pack()
    rock = Button(root, text='rock').pack()
    paper = Button(root, text='paper').pack()
    scissors = Button(root, text='scissors').pack()
    
myLabel=Label(root, text='ROCK, PAPER, SCISSORS GAME. PRESS START GAME').pack()
print = Button(root, text='START GAME', command=pickobject).pack()    

rockButton = Label(root, text= "you picked rock, computer's turn").pack()

#computer = Label(root, text=secret).pack()
root.mainloop()