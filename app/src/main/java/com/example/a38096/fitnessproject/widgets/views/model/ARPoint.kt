package com.example.a38096.fitnessproject.widgets.views.model

import android.location.Location

class ARPoint(val name: String, lat: Double, lon: Double, altitude: Double) {
	val location: Location = Location("ARPoint")

	init {
		location.latitude = lat
		location.longitude = lon
		location.altitude = altitude
	}
}