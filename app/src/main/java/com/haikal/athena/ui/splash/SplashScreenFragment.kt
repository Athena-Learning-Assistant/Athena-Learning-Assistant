package com.haikal.athena.ui.splash

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haikal.athena.R
import com.haikal.athena.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {
    private lateinit var binding : FragmentSplashScreenBinding
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        val animator = ObjectAnimator.ofFloat(binding.logoAthena, "alpha", 0f, 1f)
        animator.duration = 2000 // Animation duration in milliseconds
        animator.interpolator = DecelerateInterpolator()

        animator.start()
//        bounceFabAnimation()
        Handler().postDelayed({
            val navController = findNavController()
            if (onBoardingFinished()) {
                navController.navigate(R.id.action_splashScreenFragment_to_loginActivity)
            } else {
                navController.navigate(R.id.action_splashScreenFragment_to_viewPagerFragment22)
            }
//            startActivity(Intent(this, MainActivity::class.java))
        }, SPLASH_TIME_OUT)

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

}