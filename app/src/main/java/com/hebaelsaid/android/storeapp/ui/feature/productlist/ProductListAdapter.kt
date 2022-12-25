package com.hebaelsaid.android.storeapp.ui.feature.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hebaelsaid.android.storeapp.data.model.ProductListResponseModel
import com.hebaelsaid.android.storeapp.databinding.ProductListItemBinding

private const val TAG = "ProductListAdapter"
class ProductListAdapter(private val onItemClickListener: ProductListViewHolder.OnItemClickListener) :
    ListAdapter<ProductListResponseModel.ProductListResponseModelItem, ProductListAdapter.ProductListViewHolder>(
        ProductsModelDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClickListener)
    }

    class ProductsModelDiffCallback :
        DiffUtil.ItemCallback<ProductListResponseModel.ProductListResponseModelItem>() {
        override fun areItemsTheSame(
            oldItem: ProductListResponseModel.ProductListResponseModelItem,
            newItem: ProductListResponseModel.ProductListResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ProductListResponseModel.ProductListResponseModelItem,
            newItem: ProductListResponseModel.ProductListResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onViewRecycled(holder: ProductListViewHolder) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    class ProductListViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup): ProductListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductListItemBinding.inflate(layoutInflater, parent, false)
                return ProductListViewHolder(binding)
            }
        }

        fun bind(
            obj: ProductListResponseModel.ProductListResponseModelItem,
            onItemClickListener: OnItemClickListener
        ) {
            binding.model = obj
            binding.executePendingBindings()
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(obj.id)
            }
        }

        fun recycle() {
            itemView.setOnClickListener(null)
        }

        interface OnItemClickListener {
            fun onItemClick(productId: Int?)
        }
    }

}
