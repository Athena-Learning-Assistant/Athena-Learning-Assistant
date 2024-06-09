package com.haikal.athena.adapter

data class Message(val text: String, val type: MessageType)

enum class MessageType {
    SENT, RECEIVED
}