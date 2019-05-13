package com.example.a38096.fitnessproject.api;

import com.example.a38096.fitnessproject.model.entities.User;
import com.example.a38096.fitnessproject.model.entities.Workout;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public interface FitnessApi {

    @POST("/fitness/signUp")
    Observable<User> registerUser(@Query("firstName") String firstName,
                                  @Query("lastName") String lastName,
                                  @Query("email") String email,
                                  @Query("password") String password,
                                  @Query("gender") String gender);

    @POST("/fitness/login")
    Observable<User> login(@Header("Authorization") String loginPasswordBase64);

    @PUT("/fitness/exerciser/{uuid}")
    Observable<ResponseBody> updateUser(@Path("uuid") String uuid,
                                        @Query("firstName") String firstName,
                                        @Query("lastName") String lastName,
                                        @Query("gender") String gender,
                                        @Header("Authorization") String loginPasswordBase64);

    @POST("/fitness/exerciser/{uuid}/workout")
    Observable<ResponseBody> createWorkout(@Path("uuid") String uuid,
                                           @Query("type") String type,
                                           @Query("calories") int calories,
                                           @Query("distance") double distance,
                                           @Query("duration") int duration,
                                           @Query("workoutDate") long workoutDate,
                                           @Header("Authorization") String loginPasswordBase64);

    @PUT("/fitness/exerciser/{uuid}/workout/{workoutId}")
    Observable<ResponseBody> updateWorkout(@Path("uuid") String uuid,
                                           @Path("workoutId") long workoutId,
                                           @Query("type") String type,
                                           @Query("calories") int calories,
                                           @Query("distance") double distance,
                                           @Query("duration") int duration,
                                           @Query("workoutDate") long workoutDate,
                                           @Header("Authorization") String loginPasswordBase64);

    @DELETE("/fitness/exerciser/{uuid}/workout/{workoutId}")
    Observable<ResponseBody> deleteWorkout(@Path("uuid") String uuid,
                                           @Path("workoutId") int workoutId,
                                           @Header("Authorization") String loginPasswordBase64);


    @GET("/fitness/exerciser/{uuid}/workouts")
    Observable<List<Workout>> getWorkouts(@Path("uuid") String uuid,
                                          @Header("Authorization") String loginPasswordBase64);

}
