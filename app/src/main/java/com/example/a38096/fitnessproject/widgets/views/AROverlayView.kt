package com.example.a38096.fitnessproject.widgets.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.location.Location
import android.opengl.Matrix
import android.util.AttributeSet
import android.view.View
import com.example.a38096.fitnessproject.widgets.views.helper.LocationHelper
import com.example.a38096.fitnessproject.widgets.views.model.ARPoint

class AROverlayView @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context) {
	private var rotatedProjectionMatrix = FloatArray(16)
	private var currentLocation: Location? = null
	private val arPoints: MutableList<ARPoint> = mutableListOf(
		ARPoint("Дом", 50.455309, 30.502522, 251.0),
		ARPoint("Сільпо", 50.456145, 30.497271, 251.0),
		ARPoint("Зал", 50.452430, 30.501651, 251.0),
		ARPoint("Біла", 50.455587, 30.506687, 251.0)
	)

	fun updateRotatedProjectionMatrix(rotatedProjectionMatrix: FloatArray) {
		this.rotatedProjectionMatrix = rotatedProjectionMatrix
		this.invalidate()
	}

	fun updateCurrentLocation(currentLocation: Location?) {
		this.currentLocation = currentLocation
		this.invalidate()
	}

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		val currentLocationStatic = currentLocation ?: return
		val radius = 30
		val paint = Paint(Paint.ANTI_ALIAS_FLAG)
		paint.style = Paint.Style.FILL
		paint.color = Color.WHITE
		paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
		paint.textSize = 60f
		for (i in arPoints.indices) {
			val targetLocation = arPoints[i].location
			val distance = currentLocationStatic.distanceTo(targetLocation)
			val currentLocationInECEF = LocationHelper.convertWSG84toECEF(currentLocationStatic)
			val pointInECEF = LocationHelper.convertWSG84toECEF(targetLocation)
			val pointInENU = LocationHelper.convertECEFtoENU(currentLocationStatic, currentLocationInECEF, pointInECEF)
			val cameraCoordinateVector = FloatArray(4)
			Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0)

			// cameraCoordinateVector[2] is z, that always less than 0 to display on right position
			// if z > 0, the point will display on the opposite
			if (cameraCoordinateVector[2] < 0) {
				val x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * width
				val y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * canvas.height
				canvas.drawCircle(x, y, radius.toFloat(), paint)
				val name = arPoints[i].name
				canvas.drawText(name + "(" + distance + "m)", x - 30 * name.length / 2, y - 80, paint)
			}
		}
	}
}