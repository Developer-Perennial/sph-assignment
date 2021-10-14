# Introduction
## Functionality
- The first screen listing the total amount of data per year in a recyclerView.
- When an entry in the first screen is clicked (or selected), a second screen (another fragment), listing amount of data in each quarter for the selected year. 
- You should be able to swipe left and right to see quarter data for the next or previous year.
- When the internet is turned off, app still display the previous data.
- A class to handle the receipt of which year the user is currently viewing.

### Project Structure
 #### data 
 - local - Contains local database implementations
 - network - Contains api implementations,, Resource - for response creations
 #### di 
 Contains AppModule class for DI
 #### common 
 Contains Constants, Event - for data events
 #### repositories 
 Contains repository implementation for data access
 #### viewmodel 
 DataStoreListViewModel - viewmodel connected to First Fragment, YearDetailsViewModel- viewmodel connected to second fragment
 #### view 
 Contains fragments - to display UI, DataStoreListAdapter - adapter for displaying list items

### First Fragment
Allows you to see the data consumption yearly.
Each result from api is kept in the database in records_data table where the list of year data quarterly stored. Each time an api is called, the same RecordsData record in the Database is updated with the new list of repository ids.

### Second Fragment
Allows you to see listing amount of data in each quarter for the selected year.
Allows you to swipe left and right to see quarter data for the next or previous year
Each result from api is kept in the database in records_data table where the list of year data quarterly stored. Each time an api is called, the same RecordsData record in the Database is updated with the new list of repository ids.

### Testing
The project uses local unit tests that run on your computer. To run and generate a coverage report, you can run:

./gradlew fullCoverageReport

### Local Unit Tests
ViewModel Tests
Each ViewModel is tested using local unit tests with mock Repository implementations.

### Repository Tests
Each Repository is tested using local unit tests with mock web service and mock database.

### Webservice Tests
The project uses MockWebServer project to test REST api interactions.

### Libraries
Android Support Library
Android Architecture Components
MVVM
Android Data Binding
Dagger 2 for dependency injection
Retrofit for REST api communication
RoomDB
mockito for mocking in tests
