# FoodLocator - Android App

### App Functionality
This two-page app utilises a third-party api to display a list of restaurants based on a user inputted PostCode or the users GPS location.

## Tech-Stack
<img src="https://media.giphy.com/media/MbG90zAlZmTSLvZF0A/giphy.gif" width="260" align="right" hspace="20">

* Kotlin
* Koin (For Dependency Injection)
* RxJava (For Managing Background Tasks)
* Retrofit (For Networking)
* GSON (For Mapping Json Data)
* ViewBinding
* JetPack
  * ViewModel (For managing UI related data in a lifecycle conscious way)
  * LiveData (For notifying views of data changes)
  * Navigation
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

#### App Module
The App Module acts as the Presentation and Framework layer in this application. I've decided to keep them as one layer as I would prefer to have a single Android Module. The presentation layer is the closest to what the user sees on the screen, while the framework layer contains the logic for GPS location and RxSchedulers for switching threads. The App Module is made up of MVVM Architecture pattern using ViewModel and LiveData from the Jetpack components.

##### App Module Presentation Layer Components:
* MainActivity which acts as the NavHostFragment & RecycerView Adapter
  * SearchFragment & DetailFragment which live within the lifecycle of the MainActivity
* SearchViewModel
  * DisposingViewModel (A class the viewModel inherits which handles the disposal of Rx Disposables in a lifecycle aware fashion)
* ViewState (A sealed class which defines the state of the Activity, the state is delivered via the LiveData)

##### App Module General Components:
* Dependency Injection  
  * Retrofit configuration
* App Permissions
* Extension Functions
* ViewBinding Delegation
* ContextWrapper
* Rx Scheduler Provider

#### Domain Module/Layer
This contains all of the business logic for the app and all operations are done on a background thread. It is important to keep the Domain Layer separate from the others, this separation ensures that any UI changes done in the Presentation Layer or Database changes in the Core Layer will not affect the Domain Layer, this ideally means that there will be no code change in the Domain Layer as a result.
I have ommited the usecase layer which is responsible for handling actions a user can trigger as I feel it would bloat the app without providing any value, this layer could be used as the point at which we switch to a background thread, but I prefer to do this in the ViewModel as it ensures that the Kotlin modules (Domain & Data) are accessed only by a background thread, that's cleaner and neater in my opinion.

##### Domain Module/Layer Components:
* DomainModel (in this case called "Restaurant", it is a model as required by the Presentation layer to display content to the user)
* UseCase (in this case called "RestaurantHelper" & "LocationHelper", it contains the business logic for the app, in this example it maps the data from the data layer into the Restaurant Model)
* Repository Interface (in this case it has been omitted as the app is so simple, but this is generally recommended to create separation between the domain and data layer)

#### Data Module/Layer
This layer is responsible for handling data from the network or database.

##### Data Module/Layer Components:
* API (handles the retrofit service)
* Repo (handles exposing data to the Domain Layer)
* Restaurant Response (is the original response mapped out, only a fraction of the model is required, but all of it was kept just to display what could be used)

### Data Flow
The below diagram shows the data flow when loading restaurants via a user inputted postcode<img src="https://github.com/TinNova/FoodLocator/blob/master/FoodLocator%20Flow.png?raw=true" width="1600" align="centre" hspace="20">

