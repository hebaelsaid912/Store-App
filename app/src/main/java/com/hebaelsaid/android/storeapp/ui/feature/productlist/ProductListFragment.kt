package com.hebaelsaid.android.storeapp.ui.feature.productlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProductListFragment"
@AndroidEntryPoint
class ProductListFragment : Fragment() , ProductListAdapter.ProductListViewHolder.OnItemClickListener {
    private lateinit var binding: FragmentProductListBinding
    private val viewModel:ProductListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderProductListData()
    }

    private fun renderProductListData() {
        lifecycleScope.launchWhenCreated {
            viewModel.productListState.collect { state ->
                when(state){
                    is ProductListViewModel.ProductListState.Success -> {
                        Log.d(TAG, "renderProductListData: Success")
                        Log.d(TAG, "renderProductListData: list size ${state.productListResponseModel.size}")
                        setUpProductList(state.productListResponseModel)
                    }
                    is ProductListViewModel.ProductListState.Error -> {
                        Log.d(TAG, "renderProductListData: Error ${state.message}")
                    }
                    is ProductListViewModel.ProductListState.Loading -> {
                        Log.d(TAG, "renderProductListData: Loading")
                    }
                    else -> {
                        Log.d(TAG, "renderProductListData: else ")
                    }
                }
            }
        }
    }

    private fun setUpProductList(productList: ProductListResponseModel) {
        val adapter = ProductListAdapter(this)
        adapter.submitList(productList)
        binding.productListRv.adapter = adapter
    }

    override fun onItemClick(productId: Int?) {
        findNavController().navigate(ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(productId.toString()))
    }

}