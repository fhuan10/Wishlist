package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView: TextView
        val priceTextView: TextView
        val urlTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            nameTextView = itemView.findViewById(R.id.nameTV);
            priceTextView = itemView.findViewById(R.id.priceTV);
            urlTextView = itemView.findViewById(R.id.urlTV);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView);
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val item = items.get(position);

        // Set item views based on views and data models
        holder.nameTextView.text = item.name;
        holder.priceTextView.text = item.price;
        holder.urlTextView.text = item.url;

    }

    // Return the size of the current list of items
    override fun getItemCount(): Int {
        return items.size;
    }
}