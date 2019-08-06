# Clean Architecture focused project

## About this project

This is a simple project to play a bit with clean architecture on Android.

## Project structure

+ domain:
    + Models and repositories (interfaces). Use cases and Executors. Android independant.
+ presentation:
    + Presenters and presentation logic. View interfaces. Android independent.
+ data:
    + Repositories implementation and DataSources (Local and remote). Mappers Entity-Model. Framework dependant.
+ platform:
    + App, activities, fragments, navigation. Executors implementation. DI. Android dependant.
    
    
Use cases are build from inside to outside: start writing a model,
     then a use case, then data layer, then a presenter and finally, the view.
     
__DI is platform dependant__

## Deppendencies 

DI => Dagger

Bind views => Butternife
    
## TO-DO:  
 Validate data on save
 
 Generate ids with UUID 
 
 Use Exercise fields apart from question in views and data
 
 Test with JUnit and Expresso
 
 Try Room lib
 
 Try Retrofit (RemoteDatasource) or Firebase
  
 
 