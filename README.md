# FindYourSister
- Members: Chengshi Zhang, Yihao Hu, Zehua Zhao, Tiancheng Liu
- Team Pao
- EC 327 Fall 2015
- Boston University

	
# Overview
Find Your Sister is a fun and addictive game. It is simple, clean and intuitive. The user needs to find the celebrity whose name is given among twelve pictures of different celebrities. 

# Promo Video
[Find Your Sister Promo Video on Youtube](https://www.youtube.com/watch?v=DcYJP0P9nRI "Super funny promo video!")


# Important files and directories
## 1.Front End:
FindYourSister/app/src/main/res/layout:
- content_main.xml
- fragment_game.xml
- fragment_end.xml
	
## 2.Back End:
FindYourSister/app/src/main/java/teampao/findyoursister:
- MainActivity.java
- gameActivity.java
- endActivity.java
	
## 3.Resources:
FindYourSister/app/src/main/res/drawable
- 101 pictures of 19 celebrities in total

FindYourSister/app/src/main/res/raw
- sound effects and background music

FindYourSister/app/src/main/res/mipmap-hdpi
- background pictures for in-game buttons and the app icon
	


# Front End
> The UI for this game is clean and intuitive. When the user opens the screen, the title and the start button are displayed, along with the joyful music and the beautiful background image. 

> When the user clicks the start button, the main game screen is displayed. There are 12 buttons that contain pictures of different celebrities.   The instruction on the top displays the celebrity for the user to find. The score counter and the timer are right above the buttons. The goal is to find as many correct celebrities as possible before time runs out. 

> When the timer goes to 0, the endgame screen is displayed. It shows the user’s score and the high score. If the user wants to play another round, he/she can just press the “play again” button, and the main game screen will be displayed. 

# Back End
> “content_main.xml” is the main game layout file. It contains a title, a “start” button, a “playAgain” button, and a fragment container. At the start screen, only the title and the “start” button are visible. An onClickListener is set on the start button. When the user clicks the start button, the “gameActivity” fragment is added to the fragment container.

> “gameActivity” contains the name of the celebrity to find, the score display, the countdown timer, and 12 ImageButtons with different images of celebrities for the user to choose from. When the user first presses a button, the countdown timer starts from 60 seconds. Every time the user presses a button, the program checks if the celebrity the user chooses is the correct celebrity, and counts the score. When the timer goes to 0, “endActivity” fragment will replace “gameActivity” in the fragment container. 

> “endActivity” simply displays the current score and the high score. The program will store the new score to the high score savefile if the current score is higher than the previous high score. Also, the “playAgain” button is displayed on this screen. If the user presses this button, the “gameActivity” fragment will replace the “endActivity” and the user can play another round right away.
