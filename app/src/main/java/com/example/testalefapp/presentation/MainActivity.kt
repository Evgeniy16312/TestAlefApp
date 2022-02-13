package com.example.testalefapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.testalefapp.R
import com.example.testalefapp.presentation.gallery.GalleryFragment
import com.example.testalefapp.presentation.image.ImageFragment
import com.example.testalefapp.presentation.image.PIC_KEY
import kotlinx.android.synthetic.main.fragment_image.*


class MainActivity : AppCompatActivity(),
    GalleryFragment.PictureListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            showPicturesGrid()

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    private fun showPicturesGrid() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GalleryFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onPictureClicked(pictureUrl: String, imageView: View) {
        val args = bundleOf(Pair(PIC_KEY, pictureUrl))
        ViewCompat.setTransitionName(imageView, "something_unique")
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_container, ImageFragment::class.java, args)
            .addSharedElement(imageView, "something_unique")
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}