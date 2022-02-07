package com.example.testalefapp.presentation.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testalefapp.R
import com.example.testalefapp.databinding.FragmentGalleryBinding
import com.example.testalefapp.databinding.FragmentImageBinding

class ImageFragment : Fragment(R.layout.fragment_image) {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }
}