package edu.du.week6restapis

import android.provider.Settings.Global.getString
import android.widget.EditText
import edu.du.week6restapis.model.Movie
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface MovieServiceAlternate {
    @POST("movies")
    suspend fun createMovie(@Body requestBody: RequestBody): retrofit2.Response<Movie>

    @GET("movies")
    suspend fun getMovies(): retrofit2.Response<List<Movie>>

    @GET("movies/{id}")
    suspend fun getMovie(@Path("id") id: String): retrofit2.Response<Movie>

    @PUT("movies/{id}")
    suspend fun updateMovie(@Path("id") id:String, @Body requestBody: RequestBody): retrofit2.Response<Movie>

    @DELETE("movies/{id}")
    suspend fun deleteMovie(@Path("id") id: String): retrofit2.Response<Movie>

}