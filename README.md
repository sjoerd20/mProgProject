# mProgProject

## Problem Statement
The music at parties is usually done by one person on a single device, because it is impossible to connect multiple 		   device to a single speaker. This means thate a single person has to choose all songs or pass his/her telephone round the group. 
This is something that happens a lot at parties of people from a young age (around 16) to people in their late twenties/early thirties.
	
## Solution
The solution is to make an app that contains a playlist shared with multiple people whereby one device is the master device that plays the music from the playlist via a music API.

### Main features
(features that are part of the minimum viable product (MVP) are marked __bold__)
- Login screen
	- __Create a new group and login code__
	- __Join a existing group through a login code__
- Home screen
	- __Play and pause the current song__
	- __View the next songs in the queue__
	- Down- and upvote songs
- Search screen
	- __Search for new numbers and add them to the queue__
- Settings screen
	- __View group login code__
	- __Terminate current group (only available for group creator)__
	- Change minimum number of downvotes before a song is removed from the queue
	- __View all current users and be able to kick them (only available for group creator)__

### Login screen
![Login screen](/doc/Login_screen.jpg)

### Home screen
![Home screen](/doc/Group_leader_screen.jpg)

### Search screen
![Search screen](/doc/Search_screen.jpg)

### Settings screen
![Settings screen](/doc/Settings_screen.jpg)
	
## Prerequisites
### data sources
Youtube app
	
### external components
[Youtube API](https://www.youtube.com/yt/dev/api-resources/)
[firebase](https://firebase.google.com/)
	
### review of similar apps

### identify hardest parts to implement
Hardest part to implement is to get the youtube API working properly for each device. Linking all users via Firebase shouldn't be too hard to implement.
