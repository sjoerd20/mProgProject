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

### LoginActivity.java

### GroupCreatorHomeActivity.java

### GroupMemberHomeActivty.java

### SettingsActivity.java

## Fragments

### PlaylistFragment

### SearchFragment

### YoutubePlayerFragment

## Models

### Group

### Playlist

### Video

## Additional

### FireBase

### VideoAdapter

### YoutubeSearchRequest

