package com.hebaelsaid.android.storeapp.domain.usecase

import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.domain.repository.StoreApIRepo
import com.hebaelsaid.android.storeapp.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val repository: StoreApIRepo
) {
    operator fun invoke() = flow<Resource<ProductListResponseModel>> {
        try {
            emit(Resource.Loading<ProductListResponseModel>())
            val productResponseModel = repository.getProductsList()
            if (!productResponseModel.isEmpty()) {
                emit(Resource.Success<ProductListResponseModel>(data = productResponseModel))
            } else {
                emit(Resource.Error<ProductListResponseModel>("Data Return With Null"))
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error<ProductListResponseModel>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ProductListResponseModel>("Couldn't reach server. Please check your internet connection"))
        }
    }
}