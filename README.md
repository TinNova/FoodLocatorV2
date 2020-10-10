# FoodLocator - Android App

### App Functionality
This one-page app utilises a third-party api to display a list of restaurants based on a user inputted PostCode or the users GPS location.

## Tech-Stack
<img src="https://media.giphy.com/media/MbG90zAlZmTSLvZF0A/giphy.gif" width="260" align="right" hspace="20">

* Kotlin
* Dagger 2 (For Dependency Injection)
* RxJava (For Managing Background Tasks)
* Retrofit (For Networking)
* GSON (For Mapping Json Data)
* JetPack
  * ViewModel (For managing UI related data in a lifecycle conscious way)
  * LiveData (For notifying views of data changes)
* Picasso (For displaying images)
* Architecture
  * Clean Architecture (Used in Module/Backend Layer)
  * MVVM (Used in Presentation Layer)
* Testing
  * JUnit
  * Mockito
  * Espresso
  
  
## Architecture
<img src="https://github.com/TinNova/FoodLocator/blob/master/App%20Modules.png?raw=true" width="500" align="right" hspace="20">

The Architecture follows Uncle Bob's Clean Architecture in the background layer with three modules, App, Data and Domain. The presentation layer i.e the App module uses the MVVM pattern with LiveData as recommended by Google.

It is not necessary to have a separate module for each layer, but I've used it as it maintains strict coding discipline as an app grows as it creates a clear dependency chain.

### Tests
- Unit Tests
  - JUnit
  - Mockito
- UI Tests
  - Espresso
  - Barista
