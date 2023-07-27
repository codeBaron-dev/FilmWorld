package com.codebaron.sharedlogc.remote.errormanager

import java.io.Serializable

data class ErrorModel(
    val message: String? = null,
    val errors: String? = null
): Serializable