package com.hebaelsaid.android.storeapp.data.model

data class ProductResponseModel(
    val category: String?, // men's clothing
    val description: String?, // Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday
    val id: Int?, // 1
    val image: String?, // https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg
    val price: Double?, // 109.95
    val rating: Rating?,
    val title: String? // Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops
) {
    data class Rating(
        val count: Int?, // 120
        val rate: Float? // 3.9
    )
}