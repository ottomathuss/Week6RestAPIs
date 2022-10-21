package edu.du.week6restapis

import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface MovieService {
    @POST( "movies")
    suspend fun createMovie(@Body requestBody: RequestBody): retrofit2.Response<ResponseBody>

    @GET("movies")
    suspend fun getMovies(): retrofit2.Response<ResponseBody>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: String): retrofit2.Response<ResponseBody>

    @PUT("movie/{id}")
    suspend fun updateBike(@Path("id") id:String, @Body requestBody: RequestBody): retrofit2.Response<ResponseBody>

    @DELETE("movies/{id}")
    suspend fun deleteMovie(@Path("id") id: String): retrofit2.Response<ResponseBody>
}


