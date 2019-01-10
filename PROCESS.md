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
- Implemented the Toolbar in each Activity separate instead of using a BaseActivity
