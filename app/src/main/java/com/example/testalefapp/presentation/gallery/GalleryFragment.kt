package com.example.testalefapp.presentation.gallery

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testalefapp.R
import com.example.testalefapp.data.LoadStatus
import com.example.testalefapp.databinding.FragmentGalleryBinding
import com.example.testalefapp.presentation.gallery.viewmodel.GalleryViewModel
import com.example.testalefapp.presentation.image.adapter.PictureAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.fragment_image.*

@Suppress("UNREACHABLE_CODE")
class GalleryFragment : Fragment(R.layout.fragment_gallery),
    PictureAdapter.OnPictureClickListener, Observer<LoadStatus>,
    SwipeRefreshLayout.OnRefreshListener {

    private var clickListener: PictureListener? = null
    private val pictureAdapter: PictureAdapter by lazy { PictureAdapter(this) }
    private val galleryViewModel: GalleryViewModel by activityViewModels()
    private val picturesObserver = Observer<List<String>> {
        pictureAdapter.submitList(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            galleryViewModel.loadPictures()
    }

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        putPics()
        setupObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PictureListener)
            clickListener = context
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    private fun setupObservers() {
        galleryViewModel.liveStatus.observe(viewLifecycleOwner, this)
        galleryViewModel.livePictures.observe(viewLifecycleOwner, picturesObserver)
    }

    private fun putPics() {
        rv_gallery.adapter = pictureAdapter
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onPictureClicked(position: Int, imageView: View) {
        val pictureUrl = pictureAdapter.getItem(position)
        clickListener?.onPictureClicked(pictureUrl, imageView)
    }

    override fun onChanged(loadStatus: LoadStatus) {
        when (loadStatus) {
            LoadStatus.LOADING -> swipe_refresh.isRefreshing = true
            LoadStatus.SUCCESS -> swipe_refresh.isRefreshing = false
            LoadStatus.ERROR -> {
                swipe_refresh.isRefreshing = false
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRefresh() {
        galleryViewModel.loadPictures()
    }

    interface PictureListener {
        fun onPictureClicked(pictureUrl: String, imageView: View)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}