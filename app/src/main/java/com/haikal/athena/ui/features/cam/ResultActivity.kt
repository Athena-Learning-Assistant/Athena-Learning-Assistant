package com.haikal.athena.ui.features.cam

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.haikal.athena.R
import com.haikal.athena.data.local.entity.Save
import com.haikal.athena.databinding.ActivityResultBinding
import com.haikal.athena.ui.ViewModelFactory
import com.haikal.athena.ui.auth.login.LoginViewModel

class ResultActivity : AppCompatActivity() {
    private var binding: ActivityResultBinding? = null
    private val save = Save()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val resultModel: ResultViewModel by viewModels { ViewModelFactory.getInstance(this) }

        val prediction = intent.getStringExtra(EXTRA_PREDICTION)
        val score = intent.getStringExtra(EXTRA_SCORE)
        val result = intent.getStringExtra(EXTRA_RESULT)
        val data = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(data)

        binding!!.btnBack.setOnClickListener {
            onBackPressed()
        }

        save.image = data
        save.prediction = prediction
        save.score = score

        runOnUiThread {
            imageUri?.let {
                Log.d("Image URI", "showImage: $it")
                binding?.resultImage?.setImageURI(it)
                binding?.resultText?.text = result
            }
        }
        Log.d(ContentValues.TAG, "Image URI: $result")

        binding?.saveButton?.setOnClickListener {
            resultModel.insert(save)
            Toast.makeText(this, getString(R.string.save_success), Toast.LENGTH_SHORT).show()
            binding?.saveButton?.isEnabled = false
            binding?.saveButton?.setImageResource(R.drawable.baseline_bookmark_added_24)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_PREDICTION = "extra_prediction"
        const val EXTRA_SCORE = "extra_score"
    }
}