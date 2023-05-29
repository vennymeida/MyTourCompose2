package com.dicoding.mytourcompose.model

data class Tour(
    val tourId : Int,
    val tourName : String,
    val alamat : String,
    val deskripsi : String,
    val image : Int = 0,
)