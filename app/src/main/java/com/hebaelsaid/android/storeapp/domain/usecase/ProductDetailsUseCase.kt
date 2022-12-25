package com.hebaelsaid.android.storeapp.domain.usecase

import com.hebaelsaid.android.storeapp.data.model.ProductResponseModel
import com.hebaelsaid.android.storeapp.domain.repository.StoreApIRepo
import com.hebaelsaid.android.storeapp.utils.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val repository: StoreApIRepo
) {
    operator fun invoke(id: Int) = flow<Resource<ProductResponseModel>> {
        try {
            emit(Resource.Loading<ProductResponseModel>())
            val productResponseModel = repository.getProductByID(id = id)
            emit(Resource.Success<ProductResponseModel>(data = productResponseModel))
        } catch (e: HttpException) {
            emit(
                Resource.Error<ProductResponseModel>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ProductResponseModel>("Couldn't reach server. Please check your internet connection"))
        }
    }
}