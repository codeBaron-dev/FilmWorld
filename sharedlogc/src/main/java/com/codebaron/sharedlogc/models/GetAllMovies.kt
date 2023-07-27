package com.codebaron.sharedlogc.models

import com.codebaron.sharedlogc.models.Result

data class GetAllMovies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)