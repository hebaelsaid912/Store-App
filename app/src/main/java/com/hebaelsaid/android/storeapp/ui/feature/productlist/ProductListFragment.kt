package com.hebaelsaid.android.storeapp.ui.feature.productlist

import android.content.IntentFilter
import android.net.ConnectivityManager
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
import com.hebaelsaid.android.storeapp.utils.ConnectivityReceiver
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProductListFragment"
@AndroidEntryPoint
class ProductListFragment : Fragment() , ProductListAdapter.ProductListViewHolder.OnItemClickListener,
    ConnectivityReceiver.ConnectivityReceiverListener {
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
        requireActivity().registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
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
                    is ProductListViewModel.ProductListState.Idle -> {
                        Log.d(TAG, "renderProductListData: Idle")
                        viewModel.getProductList()
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

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }
    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }
    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            binding.notInternetConnectionLayout.root.visibility = View.VISIBLE
            binding.productListRv.visibility = View.GONE
        } else {
            binding.notInternetConnectionLayout.root.visibility = View.GONE
            binding.productListRv.visibility = View.VISIBLE
            if(binding.productListRv.adapter == null)
                viewModel.getProductList()

        }
    }
}