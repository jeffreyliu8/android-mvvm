# android-mvvm

Sample code for showing how to use livedata, room, viewmodel, retrofit2, dagger, recyclerview.

This app shows a list of my github repos.

When you start the app, the app would try to load the data from local db, and display content on the screen if any. After data has been loaded from db, it would load from network. If successful, it would update the db with latest data and update the recyclerview with minimal changes. A progress bar would appear before network callback is received. If no network, error text would appear and the list would show the old content from db.

![Output sample](https://github.com/jeffreyliu8/android-mvvm/blob/master/screenshot.png)
