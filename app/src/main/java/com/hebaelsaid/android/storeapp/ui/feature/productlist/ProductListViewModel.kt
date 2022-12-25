package com.hebaelsaid.android.storeapp.ui.feature.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.domain.usecase.ProductListUseCase
import com.hebaelsaid.android.storeapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val TAG = "ProductListViewModel"
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase
) : ViewModel(){
    private val _productListState = MutableStateFlow<ProductListState>(ProductListState.Idle)
    val productListState: StateFlow<ProductListState> get() = _productListState

    sealed class ProductListState {
        data class Success(var productListResponseModel: ProductListResponseModel) : ProductListState()
        data class Error(val message: String) : ProductListState()
        class Loading(val isLoading: Boolean) : ProductListState()
        object Idle : ProductListState()
    }
    init {
        getProductList()
    }
    private fun getProductList() {
        productListUseCase().onEach { resultState ->
            when (resultState) {
                is Resource.Success -> {
                    _productListState.value = ProductListState.Success(productListResponseModel = resultState.data!!)
                }
                is Resource.Loading -> {
                    Log.d(TAG, "getProductList: Resource.Loading: true")
                    _productListState.value = ProductListState.Loading(isLoading = true)
                }
                is Resource.Error -> {
                    Log.d(TAG, "getProductList: Resource.Error")
                    _productListState.value = ProductListState.Error(message = resultState.message ?: "un expected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}