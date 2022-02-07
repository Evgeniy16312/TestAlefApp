package com.example.testalefapp.presentation.image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.testalefapp.R
import com.example.testalefapp.databinding.FragmentImageBinding

class ImageFragment : Fragment(R.layout.fragment_image) {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageBinding.bind(view)
    }
}