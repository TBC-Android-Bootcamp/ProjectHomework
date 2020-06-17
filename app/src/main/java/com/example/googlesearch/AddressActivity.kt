package com.example.googlesearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_address.*

// https://run.mocky.io/v3/90aa5377-649b-4e39-ab7c-fa6a21a6882d
class AddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        tvStreetAddress.setOnClickListener {

            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        //val parsedJson = Gson().fromJson(jsonString, CountryModel::class.java)

        val address = intent.getStringExtra("address")
        tvStreetAddress.text = address
        tvCountry.text = "Afghanistan"
        init()
    }

    private fun init(){

    }

    fun next(view: View) {
        Tools.initDialog(this,getString(R.string.incorrect_request), getString(R.string.please_fill_all_fields))
    }
}