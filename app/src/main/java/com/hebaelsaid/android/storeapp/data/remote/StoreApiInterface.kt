package com.hebaelsaid.android.storeapp.data.remote

import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.data.model.ProductResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApiInterface {
    @GET("products")
    suspend fun getProductsList(): ProductListResponseModel
    @GET("products/{product_id}")
    suspend fun getProductByID(@Path("product_id") id:Int): ProductResponseModel
}