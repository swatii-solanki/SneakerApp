package com.crickbuzztest.ui.activity

import android.content.res.ColorStateList
import android.content.res.Resources.Theme
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.crickbuzztest.R
import com.crickbuzztest.databinding.ActivityMainBinding
import com.crickbuzztest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        initListener()
        handleNavigation()
    }

    private fun handleNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.ivCart.apply {
                        setBackgroundColor(resources.getColor(R.color.white))
                        imageTintList = ColorStateList.valueOf(resources.getColor(R.color.orange))
                    }
                    binding.ivHome.apply {
                        setBackgroundResource(R.drawable.bg_active)
                        imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }
                }

                R.id.cartFragment -> {
                    binding.ivHome.apply {
                        setBackgroundColor(resources.getColor(R.color.white))
                        imageTintList = ColorStateList.valueOf(resources.getColor(R.color.orange))
                    }
                    binding.ivCart.apply {
                        setBackgroundResource(R.drawable.bg_active)
                        imageTintList = ColorStateList.valueOf(Color.WHITE)
                    }
                }
            }
        }
    }


    private fun initListener() {
        binding.ivHome.setOnClickListener {
            findNavController(R.id.navFragment).navigateUp()
            findNavController(R.id.navFragment).navigate(R.id.homeFragment)
        }

        binding.ivCart.setOnClickListener {
            findNavController(R.id.navFragment).navigateUp()
            findNavController(R.id.navFragment).navigate(R.id.cartFragment)
        }
    }

}