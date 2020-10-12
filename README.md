## DKQuakes: Earthquakes Data Feed Example Android App

An example project that demonstrates use of the latest Android Development best-practices
for UI (JetPack/Lifecycle), Navigation, MVVM (ViewBinding and DataBinding), Kotlin Coroutines, etc.

----

### Features & Architectural Considerations

 - Leverages approaches from Google's latest **Android Architecture Blueprints** per [https://github.com/android/architecture-samples](https://github.com/android/architecture-samples)
 - A single-activity UI architecture, using the **Navigation Component** to manage fragment operations; see [https://developer.android.com/guide/navigation/navigation-getting-started](https://developer.android.com/guide/navigation/navigation-getting-started)
 - A **Presentation Layer** using a **ViewModel** per screen or feature; see [https://developer.android.com/topic/libraries/architecture/viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel)
 - Reactive UIs using **LiveData** observables and **Data Binding**; i.e. changes to underlying data propagate via observation, so are updated only when necessary.
 - An abstracted **data layer** using the **Service Locator** pattern to provide either a **mocked** or real implementation for an abstract **repository interface**, describing the web-service / data-source layer.
 - Service layer leverages **Kotlin Coroutines** (suspend functions; launched in viewModelScope) per [https://developer.android.com/topic/libraries/architecture/coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
 - Entity objects and DTOs are correctly distinct per proper **Separation of Concerns** and **CLEAN** architecture principles; see [https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
