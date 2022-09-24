package com.example.wishlist

class ItemFetcher {
    companion object {
        // This function create a list that will be returned with one 'object' element
        fun getItems(name: String, price: String, url: String): MutableList<Item> {
            var items: MutableList<Item> = ArrayList();

            var item = Item(name, price, url);

            items.add(item);

            return items;
        }

    }

}