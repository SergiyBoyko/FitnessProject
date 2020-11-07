package com.example.a38096.fitnessproject.api

import com.example.a38096.fitnessproject.model.entities.Club
import com.example.a38096.fitnessproject.model.entities.User
import com.example.a38096.fitnessproject.model.entities.Workout
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

class FitnessApiImpl : FitnessApi {
	override fun login(loginPasswordBase64: String?): Observable<User> {
		return Observable.create {
			it.onNext(User("mock_user", "admin@fitness.com", "Serhii", "Boiko", "M"))
		}
	}

	override fun createWorkout(uuid: String?,
							   type: String?,
							   calories: Int,
							   distance: Double,
							   duration: Int,
							   workoutDate: Long,
							   loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}

	override fun registerUser(firstName: String?, lastName: String?, email: String?, password: String?, gender: String?): Observable<User> {
		return Observable.create {
			it.onNext(User("mock_user", email, firstName, lastName, gender))
			it.onComplete()
		}
	}

	override fun updateUser(uuid: String?,
							firstName: String?,
							lastName: String?,
							gender: String?,
							loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}

	override fun getFavorites(uuid: String?, loginPasswordBase64: String?): Observable<MutableList<Club>> {
		return Observable.create { source ->
			val mutableList = MutableList(3) {
				val i = it + 1
				Club(
					"uuid$i",
					"club$i",
					50.452430 + i * 0.000001,
					30.501651 + i * 0.000001,
					i % 2 == 0
				)
			}
			source.onNext(mutableList)
			source.onComplete()
		}
	}

	override fun deleteWorkout(uuid: String?, workoutId: Int, loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}

	override fun updateWorkout(uuid: String?,
							   workoutId: Long,
							   type: String?,
							   calories: Int,
							   distance: Double,
							   duration: Int,
							   workoutDate: Long,
							   loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}

	override fun addToFavorites(exerciserUuid: String?, uuid: String?, loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}

	override fun getWorkouts(uuid: String?, loginPasswordBase64: String?): Observable<MutableList<Workout>> {
		return Observable.create { source ->
			val mutableList = MutableList(3) {
				val i = it + 1
				Workout(it.toLong(), "run", 50 + i, 5.0 * i, 100, Date().time)
			}
			source.onNext(mutableList)
			source.onComplete()
		}
	}

	override fun removeFromFavorites(exerciserUuid: String?, uuid: String?, loginPasswordBase64: String?): Completable {
		return Completable.complete()
	}
}