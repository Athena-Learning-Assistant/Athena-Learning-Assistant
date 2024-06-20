package com.haikal.athena.ui.features.cam

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.haikal.athena.R
import com.haikal.athena.databinding.ActivityCamBinding
import com.haikal.athena.helper.ImageClassifierHelper
import com.haikal.athena.ui.features.cam.CameraActivity.Companion.CAMERAX_RESULT
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.text.NumberFormat

class CamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCamBinding
    private lateinit var imageClassifierHelper: ImageClassifierHelper

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showToast("Permission request granted")
            } else {
                showToast("Permission request denied")
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private var prediction: String? = null
    private var score: String? = null
    private var currentImageUri: Uri? = null
    private var result: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, ResultActivity::class.java)//recheck

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.galleryButton.setOnClickListener {
            startGallery()
        }
        binding.analyzeButton.setOnClickListener {
            analyzeImage(intent)
        }
        binding.deleteButton.setOnClickListener {
            deleteImage()
        }
        binding.previewImageView.setOnClickListener {
            currentImageUri?.let { uri ->
                cropImage(uri)
            }
        }
        binding.cameraXButton.setOnClickListener {
            startCameraX()
        }

//        binding.buttonSave.setOnClickListener {
//            val intentSave = Intent(this, SaveActivity::class.java)
//            startActivity(intentSave)
//        }
//        binding.buttonArticle.setOnClickListener {
//            val intentInformation = Intent(this, InformationActivity::class.java)
//            startActivity(intentInformation)
//        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Pick a Picture", "No picture selected")
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private var cropImage =
        registerForActivityResult(CropImageContract()) { result: CropImageView.CropResult ->
            if (result.isSuccessful) {
                val cropImage =
                    BitmapFactory.decodeFile(result.getUriFilePath(applicationContext, true))
                binding.previewImageView.setImageBitmap(cropImage)
                currentImageUri = result.uriContent
            }
        }

    private fun cropImage(uri: Uri) {
        cropImage.launch(
            CropImageContractOptions(
                uri = uri, cropImageOptions = CropImageOptions(
                    guidelines = CropImageView.Guidelines.ON
                )
            )
        )
    }

    private fun showImage() {
        currentImageUri?.let {
            cropImage(it)
            Log.d("Image URI", "showImage: $it")
        }
    }

    private fun deleteImage() {
        if (currentImageUri != null) {
            currentImageUri = null
            binding.previewImageView.setImageURI(null)
            binding.previewImageView.setImageResource(R.drawable.ic_place_holder)
        } else {
            showToast(getString(R.string.no_image))
        }
    }

    private fun analyzeImage(intent: Intent) {
        if (currentImageUri == null) {
            showToast(getString(R.string.image_not_selected))
            return
        }

        imageClassifierHelper = ImageClassifierHelper(
            context = this,
            classifierListener = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(error: String) {
                    runOnUiThread {
                        Toast.makeText(this@CamActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                    runOnUiThread {
                        results?.let { it ->
                            (it.isNotEmpty() && it[0].categories.isNotEmpty())
                            println(it)
                            val sortedCategories =
                                it[0].categories.sortedByDescending { it?.score }
                            prediction = sortedCategories[0].label
                            score =
                                NumberFormat.getPercentInstance().format(sortedCategories[0].score)
                            result = sortedCategories.joinToString("\n") {
                                "${it.label} " + NumberFormat.getPercentInstance().format(it.score)
                                    .trim()
                            }
                        }
                    }
                }
            }
        )
        currentImageUri?.let { this.imageClassifierHelper.classifyStaticImage(it) }
        intent.putExtra(ResultActivity.EXTRA_PREDICTION, prediction)
        intent.putExtra(ResultActivity.EXTRA_SCORE, score)
        intent.putExtra(ResultActivity.EXTRA_RESULT, result)
        moveToResult(intent)
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERAX_RESULT) {
            currentImageUri = it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
            showImage()
        }
    }

    private fun moveToResult(intent: Intent) {
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, currentImageUri.toString())
        startActivity(intent)
    }

    private fun showToast(message: String = getString(R.string.analyze_failed)) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}