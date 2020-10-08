# FoodLocator - Android App

### App Functionality
This app utilises the JustEat api to display a list of restaurants based on a user inputted PostCode or the users GPS location.

### Tech-Stack
- Kotlin
- RxJava
- Retrofit
- JetPack
  - ViewModel
  - LiveData
  
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
