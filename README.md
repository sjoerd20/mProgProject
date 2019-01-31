# Music4Party

## Problem Statement
The music at parties is usually done by one person on a single device, because it is impossible to connect multiple 		   device to a single speaker. This means that a single person has to choose all songs or pass his/her telephone round the group. 
This is something that happens a lot at parties of people from a young age (around 16) to people in their late twenties/early thirties.
	
## Solution
The solution is to make an app that contains a playlist shared with multiple people whereby one device is the master device that plays the music from the playlist via a music API.

### Main features
- Login screen
	- Create a new group and login code
	- Join a existing group through a login code
- Home screen
	- Play and pause the current song
	- View the next songs in the playlist
	- Search for new numbers and add them to the queue
- Settings screen
	- View group login code
	- Terminate current session

### Screenshots
<p float="center">
	<img title="Login screen" src="/doc/Login.jpg" alt="drawing" height="300"/>
	<em>Login screen</em>
	<img title="Home screen group creator" src="/doc/CreatorHome.jpg" alt="drawing" height="300"/>
	<em>Home screen group creator</em>
	<img title="Home screen playlist" src="/doc/CreatorPlaylist.JPG" alt="drawing" height="300"/>
	<em>Home screen playlist</em>
	<img title="Home screen group member" src="/doc/HomeMember.JPG" alt="drawing" height="300"/>
	<em>Home screen group member</em>
	<img title="Settings screen" src="/doc/Settings.JPG" alt="drawing" height="300"/>
	<em>Settings screen</em>
</p>

## Copyright
For the license see [License](LICENSE). 

## Prerequisites
### data sources
The music used for this app originates from the Youtube app. The user has to have a updated version of the Youtube app on their phone. To establish the connection between the Youtube player and this app, the [Youtube API](https://www.youtube.com/yt/dev/api-resources/) is used.
	
### external components
For the communication between the different connected devices, [firebase](https://firebase.google.com/) is used. This is the place where the playlist, the users and the up- and downvotes are stored. The [Youtube API](https://www.youtube.com/yt/dev/api-resources/) is also used for providing the youtube player itself. The user is required to have an update version of the Youtube app installed.
	
### review of similar apps
There are many apps that use the following concept: have a group of friends and create a shared playlist. Spotify, for example, has such features. Each person can then play that playlist independently. But the market lacks an app that creates and plays a playlist simultaniously with multiple people, whereby the music is just played from one of the connected devices.

### identify hardest parts to implement
The hardest part to implement is to get the youtube API working properly for each device. A correct setup has to be made to make it possible to alter all needed features, for instance updating a playlist with the API. There are many tutorials online for setting up an Youtube API, thus if a problem arises many possible solutions can possibly be found.
