package com.example.googlesearch.ui.country

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CountryModel : Parcelable {

    lateinit var results: MutableList<Result>

    @Parcelize
    class Result : Parcelable {
        var name = ""
        var iso2 = ""
    }
}