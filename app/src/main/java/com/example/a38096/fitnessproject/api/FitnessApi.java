package com.example.a38096.fitnessproject.api;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Serhii Boiko on 01.05.2018.
 */
public interface FitnessApi {

    @POST("/fitness/signUp")
    Observable<ResponseBody> registerUser(@Query("firstName") int firstName,
                                          @Query("lastName") int lastName,
                                          @Query("gender") int gender,
                                          @Query("password") int password,
                                          @Query("email") int email);

    @PUT("/fitness/exerciser/{token}")
    Observable<ResponseBody> updateUser(@Path("token") int token,
                                        @Query("firstName") int firstName,
                                        @Query("lastName") int lastName,
                                        @Query("gender") int gender);

    @POST("/fitness/exerciser/{token}/workout")
    Observable<ResponseBody> createWorkout(@Path("token") int token,
                                           @Query("type") int type,
                                           @Query("calories") int calories,
                                           @Query("distance") int distance,
                                           @Query("duration") int duration,
                                           @Query("workoutDate") int workoutDate);

    @PUT("/fitness/exerciser/{token}/workout/{id}")
    Observable<ResponseBody> updateWorkout(@Path("token") int token,
                                           @Path("id") int id,
                                           @Query("type") int type,
                                           @Query("calories") int calories,
                                           @Query("distance") int distance,
                                           @Query("duration") int duration,
                                           @Query("workoutDate") int workoutDate);

    @DELETE("/fitness/exerciser/{token}/workout/{id}")
    Observable<ResponseBody> deleteWorkout(@Path("token") int token,
                                           @Path("id") int id,
                                           @Query("type") int type,
                                           @Query("calories") int calories,
                                           @Query("distance") int distance,
                                           @Query("duration") int duration,
                                           @Query("workoutDate") int workoutDate);


}
