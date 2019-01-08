# Design Document

### Design sketch
![design](/doc/DesignSketch.jpg)

### Classes & functions

### API
- [Youtube API](https://www.youtube.com/yt/dev/api-resources/)

### Database
For real-time storage this app uses [firebase](https://firebase.google.com/). This is an online real-time database of Google. It stores the values as JSON. The following fields will be saved to this database
- user ID (int)
- Playlist (string)
- login code (int)

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
	- Change minimum number of downvotes needed before a song is removed from the queue
	- __View all current users and be able to kick them (only available for group creator)__



