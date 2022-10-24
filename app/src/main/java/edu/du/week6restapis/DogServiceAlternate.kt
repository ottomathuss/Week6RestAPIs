package edu.du.week6restapis

import edu.du.week6restapis.model.Dog
import edu.du.week6restapis.model.Movie
import okhttp3.RequestBody
import retrofit2.http.*

interface DogServiceAlternate {
    @POST("dogs")
    suspend fun createDog(@Body requestBody: RequestBody): retrofit2.Response<Dog>

    @GET("dogs")
    suspend fun getDogs(): retrofit2.Response<List<Dog>>

    @GET("dogs/{id}")
    suspend fun getDog(@Path("id") id: String): retrofit2.Response<Dog>

    @PUT("dogs/{id}")
    suspend fun updateDog(@Path("id") id:String, @Body requestBody: RequestBody): retrofit2.Response<Dog>

    @DELETE("dogs/{id}")
    suspend fun deleteDog(@Path("id") id: String): retrofit2.Response<Dog>

}