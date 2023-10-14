package com.example.dogbreedapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogImgResponse(
    val breeds: List<Breed>? = listOf(),
    val height: Int? = 0,
    val id: String? = "",
    val url: String? = "",
    val width: Int? = 0
): Parcelable

@Parcelize
data class Breed(
    val bred_for: String? = "",
    val breed_group: String? = "",
    val height: Height,
    val id: Int? = 0,
    val life_span: String? = "",
    val name: String? = "",
    val origin: String? = "",
    val reference_image_id: String? = "",
    val temperament: String? = "",
    val weight: Weight
): Parcelable