package com.haikal.athena.ui.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.haikal.athena.R
import com.haikal.athena.databinding.FragmentScreen4Binding

class Screen4Fragment : Fragment() {
    private lateinit var binding: FragmentScreen4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScreen4Binding.inflate(inflater, container, false)

        binding.btnBack.setOnClickListener {
            val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPager)
            viewPager.currentItem = 2 // Kembali ke Screen1Fragment (indeks 0)
        }

        binding.btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment2_to_loginActivity)
            onBoardingFinished()
        }

        return binding.root
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}