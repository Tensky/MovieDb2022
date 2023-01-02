## About the Project
#### This project has 3 different style on each page.
##### 1. Authentication Page
Not much things going on here, the code is pretty simple and commentless. There is almost nothing on UI side.
On the core side, I'm using the Data-Domain separation. The big difference here is that I'm not using flow and instead just waiting for the API with coroutine suspend. There is also no caching with room here.

Another thing to note here is that im using Hilt DI to inject API key into Authentication Repository. I'm quite unsure whether this is a good thing to do.
##### 2. Home Page
Almost the exact opposite of how things works in Authentication Page, I'm mainly using flow here for reactive programming, and actively using room to cache Home Page's data.

Also, I'm not injecting API key into Repository using DI and instead adding it as default parameter on IRepository (The interface). Which one do you prefer?

I'm actively using Paging3 in this page, an Android Jetpack Library for Pagination. There is a paging package inside data layer. Inside, there is RemoteMediator Class from Paging3 which is used to mediate the paging, call remote implementation when needed, and cache result from remote implementation.

For more information about paging
https://developer.android.com/topic/libraries/architecture/paging/v3-overview

I believe that there was nothing much to explain on UI layer on this page.

##### 3. Detail Page
This page uses NetworkBoundResource, an implementation to help caching that i found on StackOverflow, Dicoding, etc. This helps us to cache remote result, determine whether we should get new result from remote, and returns local result from room. This is all done reactively.

Please note that the NetworkBoundResource is still on improvement.

## What I am asking from you
I'm trying to get any ideas on code improvement from you. This is how i usually code currently, and i believe there is much things to improve.
