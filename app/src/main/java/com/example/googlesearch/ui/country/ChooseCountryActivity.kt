package com.example.googlesearch.ui.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlesearch.R
import kotlinx.android.synthetic.main.activity_choose_country.*

class ChooseCountryActivity : AppCompatActivity() {
    lateinit var adapter: ChooseCountryAdapter
    val countries = mutableListOf<CountryModel.Result>()
    private var countryId =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_country)
        init()
    }

    private fun init(){
        //countries.addAll(intent?.extras!!.getParcelableArrayList("countries")!!)
        countryId = intent!!.extras!!.getString("countryId", "")
        adapter =
            ChooseCountryAdapter(countries)

        countries.forEach {

        }
        countryRecyclerView.layoutManager = LinearLayoutManager(this)
        countryRecyclerView.setHasFixedSize(true)
    }
}