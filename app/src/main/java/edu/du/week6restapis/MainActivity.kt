package edu.du.week6restapis

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import edu.du.week6restapis.model.Dog
import retrofit2.converter.gson.GsonConverterFactory
import edu.du.week6restapis.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

<<<<<<< HEAD
        var model_name= getString(R.string.endpoint)
=======
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/ottomathuss/Week6RestAPIs/")
            .build()
>>>>>>> 1a76244f1cb174bcc1d64168906b3b4e57aaa3b5

        // movies information
        var retrofit = Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/ottomathuss/Week6RestAPIs/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var service = retrofit.create(MovieService::class.java)
        var serviceAlternate = retrofit.create(MovieServiceAlternate::class.java)

        // dogs information
        var retrofit_dogs = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/jmmolis/Week6-API/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var serviceAlternateDogs = retrofit_dogs.create(DogServiceAlternate::class.java)

        var requestText = findViewById<EditText>(R.id.textInput)
        var responseText = findViewById<TextView>(R.id.textOutput)

        findViewById<Button>(R.id.btnGet).setOnClickListener {

            if (TextUtils.isEmpty(requestText.text)) {
                if (model_name=="movies") {
                    makeCallAlternateArray {
                        serviceAlternate.getMovies()
                    }

                } else {
                    makeCallAlternateArrayDog {
                        serviceAlternateDogs.getDogs()
                    }

                }
            } else {
                if (model_name=="movies") {
                    makeCallAlternate {
                        serviceAlternate.getMovie(requestText.text.toString())
                    }
                } else {
                    makeCallAlternateDog {
                        serviceAlternateDogs.getDog(requestText.text.toString())
                    }
                }
            }
            /*
            makeCall {
                if (TextUtils.isEmpty(requestText.text)) {
                    service.getMovies()
                } else {
                    service.getMovie(requestText.text.toString())
                }
            }
            */
        }

        findViewById<Button>(R.id.btnPost).setOnClickListener {
            var jsonObject = JSONObject()
            if (model_name=="movies") {
                jsonObject.put("id", "4")
                jsonObject.put("title", "My Cousin Vinnie")
                jsonObject.put("director", "Jonathan Lynn")
                jsonObject.put("year", "1992")
                makeCallAlternate {
                    serviceAlternate.createMovie(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                }
            } else {
                jsonObject.put("id", "3")
                jsonObject.put("name", "Mortimer")
                jsonObject.put("breed", "Mix")
                jsonObject.put("weight", "15")
                jsonObject.put("shelter", "Home")
                jsonObject.put("adoptable", false)
                makeCallAlternateDog {
                    serviceAlternateDogs.createDog(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                }
            }
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener{
            if (model_name=="movies") {
                makeCallAlternate {
                    serviceAlternate.deleteMovie(requestText.text.toString())
                }
            } else {
                makeCallAlternateDog {
                    serviceAlternateDogs.deleteDog(requestText.text.toString())
                }
            }
        }

        findViewById<Button>(R.id.btnPut).setOnClickListener {
            var jsonObject = JSONObject()
            if (model_name=="movies") {
                jsonObject.put("title", "Vertigo")
                jsonObject.put("director", "Alfred Hitchcock")
                jsonObject.put("year", "1958")
                makeCallAlternate {
                    serviceAlternate.updateMovie(requestText.text.toString(), jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                }
            } else {
                jsonObject.put("name", "Maximus")
                jsonObject.put("breed", "Chihuahua")
                jsonObject.put("weight", "14")
                makeCallAlternateDog {
                    serviceAlternateDogs.updateDog(requestText.text.toString(), jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull()))
                }
            }
        }
    }

    private fun makeCall(action: suspend () -> retrofit2.Response<ResponseBody>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var response: retrofit2.Response<ResponseBody> = action()
                var gson = GsonBuilder().setPrettyPrinting().create()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findViewById<TextView>(R.id.textOutput).text = gson.toJson(JsonParser.parseString(response.body()?.string()))
                    } else {
                        findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                    }
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    private fun makeCallAlternate(action: suspend () -> retrofit2.Response<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var gson = GsonBuilder().setPrettyPrinting().create()

                var response: retrofit2.Response<Movie> = action()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findViewById<TextView>(R.id.textOutput).text = gson.toJson(response.body())

                    } else {
                        findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                    }
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    private fun makeCallAlternateDog(action: suspend () -> retrofit2.Response<Dog>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var gson = GsonBuilder().setPrettyPrinting().create()

                var response: retrofit2.Response<Dog> = action()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findViewById<TextView>(R.id.textOutput).text = gson.toJson(response.body())

                    } else {
                        findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                    }
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    private fun makeCallAlternateArray(action: suspend () -> retrofit2.Response<List<Movie>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var response: retrofit2.Response<List<Movie>> = action()
                var gson = GsonBuilder().setPrettyPrinting().create()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findViewById<TextView>(R.id.textOutput).text = gson.toJson(response.body())
                    } else {
                        findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                    }
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    private fun makeCallAlternateArrayDog(action: suspend () -> retrofit2.Response<List<Dog>>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                var response: retrofit2.Response<List<Dog>> = action()
                var gson = GsonBuilder().setPrettyPrinting().create()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        findViewById<TextView>(R.id.textOutput).text = gson.toJson(response.body())
                    } else {
                        findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                    }
                }
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

}
