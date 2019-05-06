# BitfinexClient

An android app that showing the details of the BTC/USD currency pair, at real-time:
- a summary (current price, price change, volume, low, high) - ticker
- a table with the order book

---

### MVVM pattern with Clean architecture (as much as possible) developed with Kotlin.
Clean architecture consists of three layers:
- **Data**, which includes databases, clients, repositories, network
- **Domain**, which includes entities, usecases, and interactors
- **Presentation**, which includes UI related components, such as ViewModels, Presenters, Layouts, Fragments, Activities

Each layer has its own entities/models which are specific to that package. Mapper is used for conversion of one layer to another.

Sources: [1](https://medium.com/androiddevelopers/google-i-o-2018-app-architecture-and-testing-f546e37fc7eb), [2](https://rubygarage.org/blog/clean-android-architecture), [3](https://proandroiddev.com/kotlin-clean-architecture-1ad42fcd97fa)

##### Android Jetpack Components used:
- Fragment
- ViewModel 
- LiveData 
- Data Binding 
- ActivityScenario, for instrumentation test (part of AndroidX Test) 

##### Libraries:
- [Koin](https://insert-koin.io/), an easy-to-use DI framework. [Nice comparison with Dagger](https://medium.com/@farshidabazari/android-koin-with-mvvm-and-retrofit-e040e4e15f9d)

- [RxKotlin](https://github.com/ReactiveX/RxKotlin) / [RxAndroid](https://github.com/ReactiveX/RxAndroid), RxJava bindings for Kotlin / Android 

- [Scarlet](https://github.com/Tinder/Scarlet), a Retrofit inspired WebSocket client, manages the client-server connection for you. It makes use of a StateMachine to handle WebSocket connection correctly. Developed by Tinder, and has been in production for more than one year. 

- [OkHttp](https://square.github.io/okhttp/), an HTTP client for Android and Java applications 

- [moshi](https://github.com/square/moshi), JSON library for Kotlin and Java 

- [Timber](https://github.com/JakeWharton/timber), a logger which provides utility on top of Android’s Log class
