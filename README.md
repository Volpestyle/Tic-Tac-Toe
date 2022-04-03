# Tic-Tac-Toe
Tic-Tac-Toe game in JavaFX  
Com S 319 - HW 4
## Usage 
Ensure TicTacToe.java, o.jpg, and x.jpg are all in src folder and run as Java Application.
## Approach
My JavaFX application is composed of a ‘Borderpane’ containing a ‘Gridpane’ in the center, a
‘Label’ at the bottom, and a ‘Button’ at the top. The button will reset the game, and is set to be
hidden until a game is completed. Within each Gridpane is a custom ‘Cell’ object which extends
‘Pane’. Upon the click of a cell, an ‘x’ or ‘o’ image is inserted depending on whos turn it is, and a
function is called to see if the board is full, someone has won, or it is the next player’s turn, and
reacts accordingly.
## Screenshots
![tic-tac-toe](/screenshot.png)
