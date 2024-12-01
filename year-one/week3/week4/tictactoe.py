def display_board (b):
    """
    Function that displays the board
    b: the board to be displayed
    """
    for r in range(3):
        for c in range(3):
            print(b[r][c], end=' ')
        print()
        
def play():
    """
    Ask player to place the X or O in a row and a column 
    """
    row = int(input('Row: '))
    column = int(input('Column: '))
    return row, column

board = [['_'] * 3 for i in range(3)]
player = 'X'

while True:
    (display_board(board))
    r, c = play()
    if board[r][c] == '_':
        board[r][c] = player
        player = 'X' if player == 'O' else 'O'
    else:
        print('No empty space, try again.')
