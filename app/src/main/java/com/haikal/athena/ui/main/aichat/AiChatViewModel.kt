package com.haikal.athena.ui.main.aichat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haikal.athena.adapter.Message

class AiChatViewModel : ViewModel() {

    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>> = _messages

    // Responses dummy
    private val dummyResponses = listOf(
        "My name is Athena.",
        "I don't have an age, but I'm here to help.",
        "I exist in the digital world.",
        "I can assist you with information and tasks.",
        "No, I'm an AI.",
        "Why did the computer keep cold? It left its Windows open.",
        "I'm not programmed for singing, but I can try!",
        "The weather is sunny today.",
        "You can ask me questions or chat with me.",
        "Did you know that bees communicate by dancing?",
        "I'm here to assist you 24/7.",
        "The answer to everything is 42.",
        "My favorite color is #00FF00.",
        "I am powered by ChatGPT.",
        "I am learning from every conversation.",
        "Do you prefer cats or dogs?",
        "I like helping people.",
        "Let me find that information for you.",
        "Have you tried turning it off and on again?",
        "I'm glad we're chatting.",
        "Technology is amazing, isn't it?",
        "I can help you with educational topics.",
        "Would you like to know more about AI?",
        "Tell me about your favorite hobby.",
        "Let's learn something new together."
    )

    init {
        _messages.value = mutableListOf()
    }

    fun addMessage(message: Message) {
        val currentMessages = _messages.value
        currentMessages?.add(message)
        _messages.value = currentMessages!!
    }

    fun generateResponse(): String {
        // Get a random response from dummyResponses
        val randomIndex = (dummyResponses.indices).random()
        return dummyResponses[randomIndex]
    }

    fun clearMessages() {
        _messages.value?.clear()
        _messages.postValue(_messages.value)
    }
}