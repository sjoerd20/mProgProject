# day 2
Worked on Design Document.

### Decisions 
- For playing the Youtube video's, YoutubeFragmentPlayer will be used instead if YoutubePlayerView
  - With YoutubePlayerFragment it is easier to restore the current state when for example turning the device
  - It is also possible to keep the Youtube video playing while switching between activities

# day 3
Working on prototype

### Decisions
- I've chosen to use a Toolbar for navigation through different activities. The toolbar will be implemented with BaseActivity, so every activity can use the same toolbar. The following actions will be implemented in the toolbar:
  - Back navigation
  - Settings
  - Search  
  
# day 4
Working on prototype

### Decisions
- Implemented the Toolbar in each Activity separate instead of using a BaseActivity

# day 5
Working of connecting Firebase to application

### Decisions
- Decided to make a singleton of the FireBase class. This make it easier to use the class through multiple activities, while ensuring there is always maximal one instance of the FireBase class

# day 6
Working on implementing the youtube player. Moved the youtubePLayerFragment to a separate class named YoutubePlayerFragment

### Decisions

# day 7
Worked on YoutubePlayer and going to next video's. Also worked on pushing data to FireBase

### Decisions
- Added a Group class to hold all necessary info about the group a user is in: loginCode, groupID (FireBase group id)

# day 8
Created packages in the java folder to keep the directory neat. 

### Decisions

# day 9
Working on Youtube Search


# day 11
Working on Youtube Search

### Decisions
- Videos currently in the playlist are now displayed in a horizontal recyclerview instead of a listview

# day 12
Working on YoutubeSearch and UI

### Decisions
- The search function is now placed in the same activity as the youtube player to keep the youtube videos playing while searching for new videos

# day 13
Working on YoutubeSearch

### Decisions
- Doing the youtube search through a volley request with a URL instead of using the youtube builder

# day 14
Hackathon

# day 15

### Decisions
- Created new model Playlist to hold the videos in the playlist

# day 16
Reordering code, cleaning redundant code

### Decisions
- Search and playlist in home screen using fragments

# day 17
Finalized Firebase operations

# day 18 

### Decisions
- Changed Recyclerview for search results to ListView

# day 19
Writing report, ordering repo. Filmed product video and uploaded to youtube
