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
        "Let's learn something new together.",
        "Indonesia declared its independence on August 17, 1945.",
        "The proclamation of Indonesian independence was made at 56 Jalan Pegangsaan Timur, Jakarta.",
        "The proclamators of Indonesian independence are Soekarno and Mohammad Hatta."
    )

    // Mapping of specific questions to responses
    private val specificResponses = mapOf(
        "what is your name" to "My name is Athena.",
        "how old are you" to "I don't have an age, but I'm here to help.",
        "where do you live" to "I exist in the digital world.",
        "what can you do" to "I can assist you with information and tasks.",
        "are you a human" to "No, I'm an AI.",
        "tell me a joke" to "Why did the computer keep cold? It left its Windows open.",
        "can you sing" to "I'm not programmed for singing, but I can try!",
        "what is the weather like" to "The weather is sunny today.",
        "what can i ask you" to "You can ask me questions or chat with me.",
        "tell me a fact" to "Did you know that bees communicate by dancing?",
        "are you always available" to "I'm here to assist you 24/7.",
        "what is the meaning of life" to "The answer to everything is 42.",
        "what is your favorite color" to "My favorite color is #00FF00.",
        "who made you" to "I am powered by ChatGPT.",
        "are you learning" to "I am learning from every conversation.",
        "do you like cats or dogs" to "Do you prefer cats or dogs?",
        "what do you like to do" to "I like helping people.",
        "can you find information for me" to "Let me find that information for you.",
        "how to fix this issue" to "Have you tried turning it off and on again?",
        "do you like chatting" to "I'm glad we're chatting.",
        "what do you think about technology" to "Technology is amazing, isn't it?",
        "can you help with studies" to "I can help you with educational topics.",
        "what is ai" to "Would you like to know more about AI?",
        "what is your hobby" to "Tell me about your favorite hobby.",
        "can we learn together" to "Let's learn something new together.",
        "hello" to "Hello. Can I help you?",
        "can you give me a description about where is indonesian country" to "Indonesia is a country in Southeast Asia, located between the Indian and Pacific oceans. It consists of more than seventeen thousand islands.",
        "thanks for your information" to "It's my job to help you, any question again?",
        "when did indonesia gain independence" to "Indonesia declared its independence on August 17, 1945.",
        "where was the proclamation made" to "The proclamation of Indonesian independence was made at 56 Jalan Pegangsaan Timur, Jakarta.",
        "who are the proclamators of indonesian independence" to "The proclamators of Indonesian independence are Soekarno and Mohammad Hatta."
    )

    init {
        _messages.value = mutableListOf()
    }

    fun addMessage(message: Message) {
        val currentMessages = _messages.value
        currentMessages?.add(message)
        _messages.value = currentMessages!!
    }

    fun generateResponse(userMessage: String): String {
        val normalizedMessage = userMessage.trim().lowercase()
        return specificResponses[normalizedMessage] ?: getDummyResponse()
    }

    private fun getDummyResponse(): String {
        // Get a random response from dummyResponses
        val randomIndex = (dummyResponses.indices).random()
        return dummyResponses[randomIndex]
    }

    fun clearMessages() {
        _messages.value?.clear()
        _messages.postValue(_messages.value)
    }
}