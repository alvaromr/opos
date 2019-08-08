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
 
 Include a button wich adds possible answers
 
 Include a button wich removes a possible answer
 
 Include a button to select the correct answer
 
 Check if user's selection is the correct answer
 
 Validate data on save
 
 Generate ids with UUID 
 
 Test with JUnit and Expresso
 
 Try Picasso for images
 
 Try Room lib
 
 Try Retrofit (RemoteDatasource) or Firebase
  
 
 