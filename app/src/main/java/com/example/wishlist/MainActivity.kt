package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var items: List<Item>

    /**
     * author: shobhan
     * This function helps to hide or show the keyboard.
     */
    fun setKeyboardVisibility(show: Boolean) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } else {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishlistRV = findViewById<RecyclerView>(R.id.wishlistRV)

        // Initialize the first item to be displayed in the wishlist
        var name = "Lamp"
        var price = "12.99"
        var url = "https://www.amazon.com/Oneach-24-5-inches-Dimmable-Included/dp/B0B813CB4Z/ref=sxin_13_pa_sp_search_thematic_sspa?content-id=amzn1.sym.38deba80-70f1-44b5-9ee5-a7944caadcc1%3Aamzn1.sym.38deba80-70f1-44b5-9ee5-a7944caadcc1&cv_ct_cx=lamps&keywords=lamps&pd_rd_i=B0B813CB4Z&pd_rd_r=86248d88-1dd1-4762-9e1a-f3b6c2ded569&pd_rd_w=MYxkd&pd_rd_wg=Bbhjw&pf_rd_p=38deba80-70f1-44b5-9ee5-a7944caadcc1&pf_rd_r=7MZ6W7B75G3WCK0EN9H4&qid=1663997505&sr=1-2-a73d1c8c-2fd2-4f19-aa41-2df022bcb241-spons&psc=1"

        // Fetch a list of (1) item
        items = ItemFetcher.getItems(name, price, url)
        // Create an adapter passing in the list of items
        val adapter = ItemAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        wishlistRV.adapter = adapter
        // Set layout manager to position the items
        wishlistRV.layoutManager = LinearLayoutManager(this)


        /**
         * When the user clicks on the button, the user's text inputs will be parsed into strings
         * that would be used to create an item object. That item object will be added to the current
         * list of items and appeared on-screen.
         */
        val button = findViewById<Button>(R.id.submit);
        button.setOnClickListener {
            setKeyboardVisibility(false)  // Turns off the keyboard

            // Convert the user's text inputs into strings
            name = findViewById<EditText>(R.id.nameInput).text.toString()
            price = findViewById<EditText>(R.id.priceInput).text.toString()
            url = findViewById<EditText>(R.id.urlInput).text.toString()

            // Fetch a new list of (1) item, using the user's text inputs
            val newItem = ItemFetcher.getItems(name, price, url)

            (items as MutableList<Item>).addAll(newItem)  // Update the list
            adapter.notifyDataSetChanged()
        }
    }
}