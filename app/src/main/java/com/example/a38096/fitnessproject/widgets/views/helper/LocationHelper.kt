package com.example.a38096.fitnessproject.widgets.views.helper

import android.location.Location
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object LocationHelper {
	private const val WGS84_A = 6378137.0 // WGS 84 semi-major axis constant in meters
	private const val WGS84_E2 = 0.00669437999014 // square of WGS 84 eccentricity
	fun convertWSG84toECEF(location: Location): FloatArray {
		val radLat = Math.toRadians(location.latitude)
		val radLon = Math.toRadians(location.longitude)

		val clat = cos(radLat).toFloat()
		val slat = sin(radLat).toFloat()
		val clon = cos(radLon).toFloat()
		val slon = sin(radLon).toFloat()

		val N = (WGS84_A / sqrt(1.0 - WGS84_E2 * slat * slat)).toFloat()

		val x = ((N + location.altitude) * clat * clon).toFloat()
		val y = ((N + location.altitude) * clat * slon).toFloat()
		val z = ((N * (1.0 - WGS84_E2) + location.altitude) * slat).toFloat()

		return floatArrayOf(x, y, z)
	}

	fun convertECEFtoENU(currentLocation: Location,
						 ecefCurrentLocation: FloatArray,
						 ecefPOI: FloatArray): FloatArray {

		val radLat = Math.toRadians(currentLocation.latitude)
		val radLon = Math.toRadians(currentLocation.longitude)

		val clat = cos(radLat).toFloat()
		val slat = sin(radLat).toFloat()
		val clon = cos(radLon).toFloat()
		val slon = sin(radLon).toFloat()

		val dx = ecefCurrentLocation[0] - ecefPOI[0]
		val dy = ecefCurrentLocation[1] - ecefPOI[1]
		val dz = ecefCurrentLocation[2] - ecefPOI[2]

		val east = -slon * dx + clon * dy

		val north = -slat * clon * dx - slat * slon * dy + clat * dz

		val up = clat * clon * dx + clat * slon * dy + slat * dz

		return floatArrayOf(east, north, up, 1f)
	}
}