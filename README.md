# BitfinexClient

An android app that showing the details of the BTC/USD currency pair, at real-time:
- a summary (current price, price change, volume, low, high) - ticker
- a table with the order book

---

### MVVM pattern with Clean architecture (as much as possible) developed with Kotlin.
Clean architecture consists of three layers:
- **Data**, which includes databases, clients, repositories, network
- **Domain**, which includes entities, and usecases
- **Presentation**, which includes UI related components, such as ViewModels, Fragments, Activities

Each layer has its own entities/models which are specific to that package. Mapper is used for conversion of one layer to another.

##### Android Jetpack Components used:
- Fragment
- ViewModel 
- LiveData 
- Data Binding 
- ActivityScenario, for instrumentation test (part of AndroidX Test) 
- Espresso

##### Libraries:
- [Koin](https://insert-koin.io/), an easy-to-use DI framework. [Nice comparison with Dagger](https://medium.com/@farshidabazari/android-koin-with-mvvm-and-retrofit-e040e4e15f9d)

- [RxKotlin](https://github.com/ReactiveX/RxKotlin) / [RxAndroid](https://github.com/ReactiveX/RxAndroid), RxJava bindings for Kotlin / Android 

- [Scarlet](https://github.com/Tinder/Scarlet), a Retrofit inspired WebSocket client, manages the client-server connection for you. It makes use of a StateMachine to handle WebSocket connection correctly. Developed by Tinder, and has been in production for more than one year. 

- [OkHttp](https://square.github.io/okhttp/), an HTTP client for Android and Java applications 

- [moshi](https://github.com/square/moshi), JSON library for Kotlin and Java 

- [Timber](https://github.com/JakeWharton/timber), a logger which provides utility on top of Android’s Log class

##### Screenshots:
<img src="https://user-images.githubusercontent.com/12731470/63213580-561db080-c10e-11e9-8369-687245facb11.png" width="400">


Sources:
- [Google I/O 2018 app — Architecture and Testing](https://medium.com/androiddevelopers/google-i-o-2018-app-architecture-and-testing-f546e37fc7eb)
- [Clean Architecture of Android Apps with Practical Examples](https://rubygarage.org/blog/clean-android-architecture)
- [Clean Architecture Guide (with tested examples): Data Flow != Dependency Rule](https://proandroiddev.com/clean-architecture-data-flow-dependency-rule-615ffdd79e29)