package com.krishna.task1app.models

data class MoviesModelItem(
    val added: String,
    val category_id: String,
    val category_ids: List<Int>,
    val container_extension: String,
    val custom_sid: String,
    val direct_source: String,
    val name: String,
    val num: Int,
    val rating: Double,
    val rating_5based: Double,
    val stream_icon: String,
    val stream_id: Int,
    val stream_type: String,
    val title: String,
    val year: String
)