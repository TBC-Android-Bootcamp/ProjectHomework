package com.example.googlesearch.ui.country

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.googlesearch.R
import com.example.googlesearch.ui.address.AddressActivity
import kotlinx.android.synthetic.main.item_country_layout.view.*

class ChooseCountryAdapter(private var countries: MutableList<CountryModel.Result>, val context:Context) :
    RecyclerView.Adapter<ChooseCountryAdapter.ViewHolder>() {

    var selectedPosition: Int = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private lateinit var model: CountryModel.Result
        fun onBind() {
            model = countries[adapterPosition]
            itemView.tvTitle.text = model.name
            itemView.setOnClickListener {
                val intent = Intent(context, AddressActivity::class.java)
                intent.putExtra("countryName", model.name)
                context.startActivity(intent)
            }
            if (adapterPosition == selectedPosition) {
                itemView.selectedIW.visibility = View.VISIBLE
            } else {
                itemView.selectedIW.visibility = View.INVISIBLE
            }
        }

        override fun onClick(v: View?) {
            selectedPosition = adapterPosition
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = countries.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_country_layout, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}