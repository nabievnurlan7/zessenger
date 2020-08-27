package com.nurlandroid.kotapp.feature.chat

data class Message(
        var message: String = "",
        var sender: User,
        var createdAt: Long = 0
)

data class User(
        var nickname: String = "",
        var profileUrl: String = ""
)