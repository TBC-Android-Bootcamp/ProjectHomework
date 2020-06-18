package com.example.googlesearch.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googlesearch.ui.address.AddressActivity
import com.example.googlesearch.R
import com.example.googlesearch.ui.address.SearchAddressActivity
import com.example.googlesearch.ui.address.SearchModel
import kotlinx.android.synthetic.main.item_layout.view.*

class SearchRecyclerViewAdapter(private val items: MutableList<SearchModel>, val context: SearchAddressActivity): RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var model: SearchModel
        fun onBind(){
            model = items[adapterPosition]
            itemView.descriptionTextView.text = model.description
            val address = itemView.descriptionTextView
            itemView.setOnClickListener {
                val intent = Intent(context, AddressActivity::class.java)
                intent.putExtra("address", model.description)
                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_layout, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }
}