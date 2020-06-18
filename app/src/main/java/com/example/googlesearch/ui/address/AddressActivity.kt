package com.example.googlesearch.ui.address

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import com.example.googlesearch.*
import com.example.googlesearch.ui.country.ChooseCountryActivity
import com.example.googlesearch.ui.country.CountryModel
import com.example.googlesearch.network.DataLoader
import com.example.googlesearch.network.FutureCallback
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.util.ArrayList

// https://run.mocky.io/v3/90aa5377-649b-4e39-ab7c-fa6a21a6882d
class AddressActivity : AppCompatActivity() {
    companion object {
        const val CHOOSE_COUNTRY = 11
        const val CHOOSE_ADDRESS = 10
    }
    private val countries = mutableListOf<CountryModel.Result>()
    private var countryId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        //tvStreetAddress.setOnClickListener {
        //    val intent = Intent(this, SearchAddressActivity::class.java)
        //    startActivity(intent)
        //}

        //val parsedJson = Gson().fromJson(jsonString, CountryModel::class.java)

        //val address = intent.getStringExtra("address")
        init()
    }

    private fun init(){

        setSupportActionBar(toolbar)
        tvTitle.text = "Address"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getCountries()

        tvStreetAddress.setOnClickListener {
            // Nothing here yet
        }

        tvCountry.setOnClickListener {
            // Nothing here yet
        }
    }

    private fun getCountries(){
        DataLoader.getRequest(DataLoader.PATH, mutableMapOf(),object: FutureCallback {
            override fun onSuccess(result: String) {
                countries.addAll(Gson().fromJson(result, CountryModel::class.java).results)
                tvCountry.text = countries[0].name
                countryId = countries[0].iso2
            }

            override fun onFailure(error: String) {
                // Add error function below
            }
        })
    }

    private fun chooseCountry(){
        val intent = Intent(this, ChooseCountryActivity::class.java)
        intent.putParcelableArrayListExtra("countries", countries as ArrayList<out Parcelable>)
        intent.putExtra("countryId", countryId) // countryId უნდა გადავცეთ
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun next(view: View) {
        Tools.initDialog(
            this,
            getString(R.string.incorrect_request),
            getString(R.string.please_fill_all_fields)
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CHOOSE_COUNTRY){
                // more comming soon
                print("")
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}