# FoodLocator - Android App

### App Functionality
This app utilises the JustEat api to display a list of restaurants based on a user inputted PostCode or the users GPS location.

## Tech-Stack
- Kotlin
- Dagger 2 (For Dependency Injection)
- RxJava (For Managing Background Tasks)
- Retrofit (For Networking)
- GSON (For Mapping Json Data)
- JetPack
  - ViewModel (For managing UI related data in a lifecycle conscious way)
  - LiveData (For notifying views of data changes)
- Picasso (For displaying images
- Architecture
  - Clean Architecture (Used in Module/Backend Layer
  - MVVM (Used in Presentation Layer
- Testing
  - JUnit
  - Mockito
  - Espresso
  
  ![FoodLocator App](https://media.giphy.com/media/MbG90zAlZmTSLvZF0A/giphy.gif)

  
### Architecture
The Architecture follows Uncle Bob's Clean Architecture with three modules, App, Data and Domain, however the presentation layer i.e the App module uses the MVVM pattern with LiveData as recommended by Google.

It is not necessary to have a separate module for each layer, but I've used it as it maintains strict coding discipline as an app grows and it creates a clear dependency chain.

### Tests
- Unit Tests
  - JUnit
  - Mockito
- UI Tests
  - Espresso
  - Barista
