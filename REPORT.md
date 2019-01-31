# Music4Party

Author: Sjoerd Terpstra, 11251980
Course: Programmeerproject, minor Programmeren, UvA  
Datum: 31-1-2019  
  
### Short description
This is a music app to create a realtime playlist of youtube videos accross multiple mobile devices, with the youtube video playing on the device of the group creator to provide music for a group of people.  

_Screenshot of the home screen of the group creator_  
<img title="Home screen group creator" src="/doc/CreatorHome.jpg" alt="drawing" height="300"/>    

Product video: [Product video](https://youtu.be/a-um6Nw5oYA)

# Application overview
_Overview of all screens_  
<img src="/doc/Screenoverview.jpg" alt="drawing" height="800"/> 

## Login activity
- Create a new session as a group creator
- Join a existing session with a 4-digit login code provided by a group creator

## Group creator home activity 
- Plays the current youtube song
- View the playlist
- Search for youtube videos and add them to the playlist

## Group member home activity
- View the current youtube song that is playing on the group creators device
- View the playlist
- Search for youtube videos and add them to the playlist

## Settings activity
- View login code
- Exit current session

# Detailed overview

## Activities
There are 4 different activities. The application starts with the login activity. This activity can then direct the user to GroupCreatorActivity or GroupMemberHomeActivty, depending if they started a new session in the login activity or logged in to a existing group.

### LoginActivity
- User can enter login code to login. This code is checked with FireBase to check if the group exists. If that is the case, user joins the group and is directed to GroupMemberHomeActivity.
- Create new session button. This starts a new session, generates a login code and makes a new Firebase entry through an instance of the FireBase class. If successful, the user is directed to GroupCreatorHomeActivity.

### GroupCreatorActivity
- Implements the youtube player via the YoutubePlayerFragment class. 
- 2 other fragments in this activity, which are controlled with the Playlist button and the Search button
  - SearchFragment
  - PlaylistFragment
- Has a settings button in the toolbar that redirects the user to SettingsActivity

### GroupMemberHomeActivty
- A view with the current youtube video that is played on the group creators device
- 2 fragments in this activity, which are controlled with the Playlist button and the Search button
  - SearchFragment
  - PlaylistFragment
- Has a settings button in the toolbar that redirects the user to SettingsActivity

### SettingsActivity
- View the login code
- Exit current session. The user is redirected to the LoginActivity after this

## Fragments

### PlaylistFragment
- This fragment shows the playlist with a listview in the HomeActivities

### SearchFragment
- This fragment shows the search UI for searching new youtube videos. 

### YoutubePlayerFragment
- This fragment contains the YoutubePlayerFragment using the Youtube Data API.
- Contains the logic to go to the next video after a video has ended

## Models

### Group
- Contains info about a single group:
  - loginCode (int)
  - groupId (This is the login code parsed to a String, needed for FireBase database)

### Playlist
- Singleton, contains a single arraylist of videos
- Methods to add, retrieve and remove videos

### Video
- Contains info about a single video:
  - videoTitle (String)
  - videoId (String)
  - thumbnailURL

## Additional

### FireBase
- Controls the interaction with the FireBase realtime database
- Checks login code for users who are login in
- Singleton
- Instantiates Group and Playlist object
- Write and read from database  

Each group has a database entry under "g" with their logincode and a database entry under "p" to store their playlist.

### VideoAdapter
- This adapter is used to display videos properly in a listview. It uses an arraylist of videos

### YoutubeSearchRequest
- Sends a volleyrequest to the Youtube Data API to search videos using a search query provided by the user. This class interacts with GroupCreatorHomeActivity and GroupMemberHomeActivity who both have a search functionality implemented
