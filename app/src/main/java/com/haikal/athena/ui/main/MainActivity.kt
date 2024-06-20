package com.haikal.athena.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haikal.athena.R
import com.haikal.athena.databinding.ActivityMainBinding
import com.haikal.athena.ui.features.cam.CamActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_ai_chat,
                R.id.navigation_absent,
                R.id.navigation_profile
            )
        )
        // Remove the action bar setup
        navView.setupWithNavController(navController)

        binding.fabCam.setOnClickListener {
            intent = Intent(this, CamActivity::class.java)
            startActivity(intent)
        }
    }
}


