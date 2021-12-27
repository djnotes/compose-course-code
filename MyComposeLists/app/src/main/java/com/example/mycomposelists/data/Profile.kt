package com.example.mycomposelists.data

import java.util.*

data class Profile(val name: String, val picture: Int, val id: String = UUID.randomUUID().toString())