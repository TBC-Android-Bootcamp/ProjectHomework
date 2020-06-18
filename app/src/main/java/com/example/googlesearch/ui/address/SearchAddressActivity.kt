package com.example.googlesearch.ui.address

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.googlesearch.R
import com.example.googlesearch.network.AutoComplete
import com.example.googlesearch.network.FutureCallback
import com.example.googlesearch.ui.SearchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.json.JSONObject

class SearchAddressActivity : AppCompatActivity() {

    private lateinit var searchAdapter: SearchRecyclerViewAdapter
    private val addresses = mutableListOf<SearchModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        tvTitle.text = "Search Address"
        searchAdapter = SearchRecyclerViewAdapter(
            addresses,
            this
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = searchAdapter
        searchEditText.addTextChangedListener(textWatcher)
    }

    private fun getAddresses(input: String) {
        val parameters = mutableMapOf<String, String>()
        parameters["input"] = input
        parameters["key"] = "AIzaSyBADWUmhO9XNVF_-qSZR6RQWcoHfSpAr6E"
        AutoComplete.getRequest(AutoComplete.AUTOCOMPLETE, parameters, object :
            FutureCallback {
            override fun onSuccess(result: String) {
                addresses.clear()
                val json = JSONObject(result)
                if (json.has("predictions")) {
                    val predictions = json.getJSONArray("predictions")
                    (0 until predictions.length()).forEach {
                        val prediction = predictions.getJSONObject(it)
                        addresses.add(
                            SearchModel(
                                prediction.getString("description"),
                                prediction.getString("place_id")
                            )
                        )
                    }

                    searchAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(error: String) {

            }
        })
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            d("loglog", "beforeTextChanged")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            d("loglog", "beforeTextChanged")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            getAddresses(s.toString())
        }
    }
}
