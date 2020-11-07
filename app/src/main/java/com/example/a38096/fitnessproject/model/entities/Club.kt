package com.example.a38096.fitnessproject.model.entities

import com.google.gson.annotations.SerializedName

class Club(
	@SerializedName("uuid") var uuid: String? = null,
	@SerializedName("name") var name: String? = null,
	@SerializedName("latitude") var latitude: Double? = null,
	@SerializedName("longitude") var longitude: Double? = null,
	var isFavorite: Boolean = false
)