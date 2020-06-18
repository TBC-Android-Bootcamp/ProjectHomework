package com.example.googlesearch.ui.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlesearch.R
import com.example.googlesearch.network.DataLoader
import com.example.googlesearch.network.FutureCallback
import com.example.googlesearch.ui.SearchRecyclerViewAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_choose_country.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_country_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.tvTitle

class ChooseCountryActivity : AppCompatActivity() {
    lateinit var adapter: ChooseCountryAdapter
    private val countries = mutableListOf<CountryModel.Result>()
    private var countryId =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_country)
        init()
    }

    private fun init(){
        setSupportActionBar(toolbar)
        tvTitle.text = "Choose Country"
        btnDone.text = "Done"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //countries.addAll(intent?.extras!!.getParcelableArrayList("countries")!!)
        countryId = intent!!.extras!!.getString("countryId", "")
        getCountries()
    }

    private fun getCountries(){
        DataLoader.getRequest(DataLoader.PATH, mutableMapOf(),object: FutureCallback {
            override fun onSuccess(result: String) {
                countries.addAll(Gson().fromJson(result, CountryModel::class.java).results)
                adapter = ChooseCountryAdapter(countries, this@ChooseCountryActivity)
                countryRecyclerView.adapter = adapter
                countryRecyclerView.layoutManager = LinearLayoutManager(this@ChooseCountryActivity)
                countryRecyclerView.setHasFixedSize(true)
            }

            override fun onFailure(error: String) {
                // Add error function below
            }
        })
    }
}