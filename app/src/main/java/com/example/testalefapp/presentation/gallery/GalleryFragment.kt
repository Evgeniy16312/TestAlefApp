package com.example.testalefapp.presentation.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.testalefapp.R
import com.example.testalefapp.databinding.FragmentGalleryBinding

@Suppress("UNREACHABLE_CODE")
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)
        binding.button?.setOnClickListener {
            animationNavigateToFragment()
        }
    }

    private fun animationNavigateToFragment() {
        val direction = GalleryFragmentDirections.actionGalleryFragmentToImageFragment()
        findNavController().navigate(
            direction,
            // optional additional options, example of simple animation:
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }
}