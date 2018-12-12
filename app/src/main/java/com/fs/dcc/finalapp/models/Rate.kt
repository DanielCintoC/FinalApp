package com.fs.dcc.finalapp.models

import java.util.*

/**
 * Created by danielcintoconde on 11/12/18.
 */
data class Rate(val userId: String = "",
                val text: String = "",
                val rate: Float = 0f,
                val createdAt: Date = Date(),
                val profileImageURL: String = "")