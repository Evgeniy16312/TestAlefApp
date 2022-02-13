package com.example.testalefapp.presentation.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.example.testalefapp.R
import com.example.testalefapp.databinding.FragmentImageBinding
import com.example.testalefapp.presentation.gallery.GalleryFragmentDirections
import kotlinx.android.synthetic.main.fragment_image.*


const val PIC_KEY = "let's pretend it's a key"

class ImageFragment : Fragment(R.layout.fragment_image) {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    private lateinit var picture: String

    override fun onCreate(savedInstanceState: Bundle?) {
        picture = requireArguments().getString(PIC_KEY, "defaultValue")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageBinding.bind(view)
        putPics()
        binding.close.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun putPics() {
        Glide
            .with(requireContext())
            .load(picture)
            .centerInside()
            .error(R.drawable.ic_baseline_error_24)
            .into(iv_image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}