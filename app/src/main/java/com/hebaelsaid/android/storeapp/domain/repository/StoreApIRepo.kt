package com.hebaelsaid.android.storeapp.domain.repository

import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.data.model.ProductResponseModel

interface StoreApIRepo {
    suspend fun getProductsList(): ProductListResponseModel
    suspend fun getProductByID(id:Int): ProductResponseModel
}