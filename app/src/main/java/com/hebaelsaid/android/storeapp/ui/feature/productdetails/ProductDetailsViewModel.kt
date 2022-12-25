package com.hebaelsaid.android.storeapp.ui.feature.productdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.storeapp.data.model.ProductResponseModel
import com.hebaelsaid.android.storeapp.domain.usecase.ProductDetailsUseCase
import com.hebaelsaid.android.storeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "ProductDetailsViewModel"
@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase
) : ViewModel(){
    private val _productDetailsState = MutableStateFlow<ProductDetailsState>(ProductDetailsState.Idle)
    val productDetailsState: StateFlow<ProductDetailsState> get() = _productDetailsState

    sealed class ProductDetailsState {
        data class Success(var productDetails: ProductResponseModel) : ProductDetailsState()
        data class Error(val message: String) : ProductDetailsState()
        class Loading(val isLoading: Boolean) : ProductDetailsState()
        object Idle : ProductDetailsState()
    }

    private fun getProductDetails(id:Int) {
        productDetailsUseCase(id = id).onEach { resultState ->
            when (resultState) {
                is Resource.Success -> {
                    _productDetailsState.value = ProductDetailsState.Success(productDetails = resultState.data!!)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "getProductDetails: Resource.Loading: true")
                    _productDetailsState.value = ProductDetailsState.Loading(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d(TAG, "getProductDetails: Resource.Error")
                    _productDetailsState.value = ProductDetailsState.Error(message = resultState.message ?: "un expected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}