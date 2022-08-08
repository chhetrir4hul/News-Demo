# News Demo

News Demo is a native Android Application written in Kotlin. It is a demo project that uses API from [newsapi.org](https://newsapi.org/) to display list of top headlines.

The news detail page shows more details about the news.

## Configuration
### Keystores:
`apiKey.properties` is also pushed to repository on purpose to make it easy to run application without having to make any changes. This file should be added in `.gitignore` file.

Edit `apiKey.properties` in project root directory with the following info if you want to use your own API keys:
```gradle
NEWS_API_KEY = "INSERT_NEWS_API_KEY_HERE"
```


## Architecture
My News application uses Android MVVM architecture with Android Architecture Components. The following are the key Android libraries used:

* Retrofit and okHttp3 (Making network calls)
* Glide (Image loading library)
* Hilt (Dependency injection)
* Navigation component
* Kotlin Coroutines
* Truth
* Mockk


## Developers
This project is developed by [Rahul Chhetri](https://github.com/chhetrir4hul)