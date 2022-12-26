# Store-App
Store App is an app for presenting most of items in store. this application using the API from https://fakestoreapi.com/

[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

## Code Installation

you can clone code and run it using :

``
  IDE : Android Studio
  kotlinCompilerExtensionVersion: 1.2.0-rc02
  Compile Sdk : 33
``

## App Screen Recording
 <table>
  <tr>
    <th>Splash Screen</th>
    <th>Home</th>
    <th>Product Details 1</th>
    <th>Product Details 2</th>
    <th>Connection </th>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/72816466/209556665-8a0e1306-9ce3-4f35-9b44-7e8c63c1c620.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/209556681-73123fde-9569-4d91-aa92-1bdfda63d2f7.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/209556701-4e6f0f45-1538-46f6-ab89-1be4dfce1b32.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/209556707-529c0d0b-f51a-448c-afbb-406085e04ce1.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/209557115-14c83f44-6f14-4619-ad30-0cb4deed1c99.jpg" width="350"></td>
  </tr>
</table> 

## Architecture
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture. 
![1 9fa8VrWQyNtpxvgGXghMPQ](https://user-images.githubusercontent.com/72816466/202196876-39bb8b5d-aa81-4693-8a5e-b1b588133975.jpeg)

## Tech stack & Open-source libraries
- Minimum SDK level 26
- Kotlin based.
- Android Gradle plugin requires Java 11 to run.
- StateFlow - emit state updates and emit values to consumers.
- ViewModel - UI related data holder, lifecycle aware.
- Coroutines for asynchronous.
- Architecture
    - MVVM Architecture ( DataBinding - ViewModel ).
    - Single Activity
       - multiple screens handled together using Navigation.
    - Repository Design Pattern
- Clean code, Use Cases
- Retrofit2 & Gson - construct the REST APIs.
- Dependency Injection (dagger-hilt).
