package com.example.a38096.fitnessproject.model.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Serhii Boiko on 12.05.2018.
 */
class User(
	@SerializedName("uuid") var uuid: String? = null,
	@SerializedName("email") var email: String? = null,
	@SerializedName("firstName") var firstName: String? = null,
	@SerializedName("lastName") var lastName: String? = null,
	@SerializedName("gender") var gender: String? = null
)