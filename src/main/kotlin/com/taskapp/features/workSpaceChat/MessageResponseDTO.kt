package com.taskapp.features.workSpaceChat

import kotlinx.serialization.Serializable


@Serializable
data class MessageResponseDTO(
    val id:String,
    val userName: String,
    val sendingUser: String,
    val workSpaceId: String,
    val dateTime:String,
    val text: String
)
