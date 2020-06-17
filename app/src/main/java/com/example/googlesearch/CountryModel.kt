package com.example.googlesearch

class CountryModel {
    lateinit var results:MutableList<Result>

    class Result {
        var name: String = ""
        var iso2: String = ""
    }
}