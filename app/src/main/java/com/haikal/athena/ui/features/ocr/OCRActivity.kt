package com.haikal.athena.ui.features.ocr

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.haikal.athena.R
import com.haikal.athena.databinding.ActivityOcrBinding

class OCRActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOcrBinding
    private val viewModel: OCRViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOcrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnDelete.setOnClickListener {
            Toast.makeText(this, "Delete button clicked", Toast.LENGTH_SHORT).show()
        }

        binding.uploadButton.setOnClickListener {
            selectPdfFile()
        }

        binding.downloadButton.setOnClickListener {
            Toast.makeText(this, "Download button clicked", Toast.LENGTH_SHORT).show()
        }

        viewModel.pdfUri.observe(this, Observer { uri ->
            uri?.let {
                displaySelectedPdf(it)
            }
        })

        viewModel.conversionSuccess.observe(this, Observer { success ->
            if (success) {
                showConversionSuccess()
            }
        })
    }

    private val pdfLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                viewModel.setPdfUri(uri)
            }
        }
    }

    private fun selectPdfFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        pdfLauncher.launch(intent)
    }

    private fun displaySelectedPdf(uri: Uri) {
        binding.pdfImageView.setImageResource(R.drawable.baseline_pdf_ready)
        binding.uploadedFileTextView.text = "Uploaded File"
        binding.pdfFileInfoTextView.text = uri.lastPathSegment
    }

    private fun showConversionSuccess() {
        binding.docImageView.visibility = android.view.View.VISIBLE
        binding.convertedFileTextView.visibility = android.view.View.VISIBLE
        binding.docFileInfoTextView.visibility = android.view.View.VISIBLE
        binding.conversionSuccessTextView.visibility = android.view.View.VISIBLE
        binding.downloadButton.visibility = android.view.View.VISIBLE
    }
}