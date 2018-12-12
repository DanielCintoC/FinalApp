package com.fs.dcc.finalapp.models

import java.util.*

/**
 * Created by danielcintoconde on 11/12/18.
 */
data class Rate(val text: String,
                val rate: Float,
                val createdAt: Date,
                val profileImageURL: String = "")