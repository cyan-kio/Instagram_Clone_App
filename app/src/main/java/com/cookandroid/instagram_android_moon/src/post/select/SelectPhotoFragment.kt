package com.cookandroid.instagram_android_moon.src.post.select

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.cookandroid.instagram_android_moon.R
import com.cookandroid.instagram_android_moon.config.BaseFragment
import com.cookandroid.instagram_android_moon.databinding.FragmentSelectPhotoBinding
import com.cookandroid.instagram_android_moon.src.post.PostActivity
import com.cookandroid.instagram_android_moon.src.post.adapter.ImagePickerAdapter
import com.cookandroid.instagram_android_moon.src.post.model.ImagePickerViewModel
import com.cookandroid.instagram_android_moon.src.post.posting.PostingFragment

class SelectPhotoFragment : BaseFragment<FragmentSelectPhotoBinding>(FragmentSelectPhotoBinding::bind, R.layout.fragment_select_photo) {
    private val viewModel: ImagePickerViewModel by viewModels()
    private val MY_READ_PERMISSTION_CODE = 101
    private lateinit var postingFragment :PostingFragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_READ_PERMISSTION_CODE)
        } else {
            loadImages()
        }

        postingFragment = PostingFragment()

        binding.btnToolbarNewPostTopComplete.setOnClickListener {
            activity?.supportFragmentManager?.apply{
                setFragmentResult(
                    "URI_LIST_CHECKED",
                    bundleOf("uriList" to viewModel.getCheckedImageUriList())
                )
                beginTransaction()
                    .replace(R.id.container_post, postingFragment)
                    .addToBackStack(null)
                    .commit()
                viewModel.imageItemList.value!!.clear()
            }
        }

    }

    private fun loadImages() {
        val adapter = ImagePickerAdapter(requireContext(), viewModel)
        binding.recyclerNewPost.apply {
            layoutManager= GridLayoutManager(requireContext(), 4)
            this.adapter = adapter
        }
        viewModel.fetchImageItemList(requireContext())
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: ImagePickerAdapter) {
        viewModel.imageItemList.observe(viewLifecycleOwner) { imageItemList ->
            adapter.submitList(imageItemList)
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_READ_PERMISSTION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showCustomToast("Read external permission granted")
            } else {
                showCustomToast("Read external storage permission denied")
            }
        }
    }
}

