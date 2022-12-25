package com.hebaelsaid.android.storeapp.data.repository

import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.data.model.ProductResponseModel
import com.hebaelsaid.android.storeapp.data.remote.StoreApiInterface
import com.hebaelsaid.android.storeapp.domain.repository.StoreApIRepo
import javax.inject.Inject

class StoreApiRepoImpl @Inject constructor(private val api: StoreApiInterface) : StoreApIRepo{
    override suspend fun getProductsList(): ProductListResponseModel {
        return api.getProductsList()
    }

    override suspend fun getProductByID(id: Int): ProductResponseModel {
        return api.getProductByID(id = id)
    }
}