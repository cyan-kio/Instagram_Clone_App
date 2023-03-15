package com.cookandroid.instagram_android_moon.src.post.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerNewPostGridBinding
import com.cookandroid.instagram_android_moon.src.post.model.image.ImageItem
import com.cookandroid.instagram_android_moon.src.post.model.image.ImagePickerViewModel

class ImagePickerAdapter(
    val context: Context,
    private val parentViewModel: ImagePickerViewModel
) : ListAdapter<ImageItem, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    inner class ViewHolder(val binding: ItemRecyclerNewPostGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageItem: ImageItem) {
            Glide.with(context).load(imageItem.uri).into(binding.ivItemNewPostImage)
            binding.root.setOnClickListener {
                binding.ckbxItemNewPostCheckbox.isChecked = !binding.ckbxItemNewPostCheckbox.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemRecyclerNewPostGridBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = ViewHolder(view)
        subscribeUi(view, holder)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imageItem = getItem(position)
        (holder as ViewHolder).bind(imageItem)
    }

    private fun subscribeUi(binding: ItemRecyclerNewPostGridBinding, holder: ViewHolder) {
        binding.ckbxItemNewPostCheckbox.setOnCheckedChangeListener { _, isChecked ->
            parentViewModel.imageItemList.value?.let {
                val position = holder.adapterPosition
                it[position].isChecked = isChecked
            }
        }
    }


}
private class ImageDiffCallback: DiffUtil.ItemCallback<ImageItem>() {
    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
        return oldItem == newItem
    }
}