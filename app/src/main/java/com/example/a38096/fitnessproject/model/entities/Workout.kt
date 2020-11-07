package com.example.a38096.fitnessproject.model.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Serhii Boiko on 08.05.2018.
 */
class Workout(
	@SerializedName("id") var workoutId: Long? = null,
	@SerializedName("type") var type: String? = null,
	@SerializedName("calories") var calories: Int? = null,
	@SerializedName("distance") var distance: Double? = null,
	@SerializedName("duration") var duration: Int? = null,
	@SerializedName("workoutDate") var workoutDate: Long? = null
)