package com.haikal.athena.ui.features.qa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.athena.R
import com.haikal.athena.adapter.Message
import com.haikal.athena.adapter.MessageAdapter
import com.haikal.athena.adapter.MessageType
import com.haikal.athena.databinding.ActivityQaBinding

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaBinding
    private val messages = mutableListOf<Message>()
    private lateinit var messageAdapter: MessageAdapter

    // Responses dummy with nouns
    private val dummyResponses = listOf(
        "apple", "book", "cat", "dog", "elephant", "flower", "guitar", "house", "ice", "jacket",
        "kite", "lemon", "mountain", "notebook", "ocean", "pencil", "queen", "river", "sun", "tree",
        "umbrella", "violin", "window", "xylophone", "yacht", "zebra", "airplane", "bicycle", "camera",
        "drum", "eagle", "forest", "garden", "hat", "island", "jewel", "kangaroo", "lamp", "moon",
        "nest", "orange", "pizza", "quilt", "robot", "star", "tiger", "unicorn", "vase", "whale"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupRecyclerView()
        setupSendButton()
    }

    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messages)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@QAActivity)
            adapter = messageAdapter
        }
    }

    private fun setupSendButton() {
        binding.sendButton.setOnClickListener {
            val messageText = binding.messageInput.text.toString().trim()
            if (!TextUtils.isEmpty(messageText)) {
                addMessage(Message(messageText, MessageType.SENT))
                // Generate response from the bot
                val response = generateResponse()
                addMessage(Message(response, MessageType.RECEIVED))
                binding.messageInput.text?.clear()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_qa, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_upload_document -> {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "application/pdf"
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                startActivityForResult(intent, REQUEST_PICK_PDF)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_PDF && resultCode == Activity.RESULT_OK) {
            // Handle the selected PDF file
            val selectedPdfUri = data?.data
            if (selectedPdfUri != null) {
                // You can use the selectedPdfUri to do something with the selected PDF file
                Toast.makeText(this, "PDF berhasil diunggah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addMessage(message: Message) {
        messages.add(message)
        messageAdapter.notifyItemInserted(messages.size - 1)
        binding.recyclerView.scrollToPosition(messages.size - 1)
    }

    private fun generateResponse(): String {
        val randomIndex1 = dummyResponses.indices.random()
        val randomIndex2 = dummyResponses.indices.random()
        return "${dummyResponses[randomIndex1]}, ${dummyResponses[randomIndex2]}"
    }

    companion object {
        private const val REQUEST_PICK_PDF = 123
    }
}

