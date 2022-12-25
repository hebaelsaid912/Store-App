package com.hebaelsaid.android.storeapp.ui.feature.productdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hebaelsaid.android.storeapp.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ProductDetailsFragment"
@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel : ProductDetailsViewModel by viewModels()
    private var productId : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = arguments?.getString("product_id")!!.toInt()
        Log.d(TAG, "onCreate: productID: $productId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductDetails(id = productId)
        binding.backImgDetails.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.likeImgDetails.setOnClickListener {
            binding.likeImgDetails.visibility = View.GONE
            binding.likedImgDetails.visibility = View.VISIBLE
            Toast.makeText(requireContext(),"Product liked",Toast.LENGTH_LONG).show()
        }
        binding.likedImgDetails.setOnClickListener {
            binding.likeImgDetails.visibility = View.VISIBLE
            binding.likedImgDetails.visibility = View.GONE
            Toast.makeText(requireContext(),"Product unliked",Toast.LENGTH_LONG).show()
        }
        renderProductDetails()
    }

    private fun renderProductDetails() {
            lifecycleScope.launchWhenCreated {
                viewModel.productDetailsState.collect { state ->
                    when(state){
                        is ProductDetailsViewModel.ProductDetailsState.Success -> {
                            Log.d(TAG, "renderProductListData: Success")
                            binding.model = state.productDetails
                        }
                        is ProductDetailsViewModel.ProductDetailsState.Error -> {
                            Log.d(TAG, "renderProductListData: Error ${state.message}")
                        }
                        is ProductDetailsViewModel.ProductDetailsState.Loading -> {
                            Log.d(TAG, "renderProductListData: Loading")
                        }
                        else -> {
                            Log.d(TAG, "renderProductListData: else ")
                        }
                    }
                }
            }
    }

}