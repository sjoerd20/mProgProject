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
