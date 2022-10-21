package edu.du.week6restapis

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/ottomathuss/Week6RestAPIs/")
            .build()

        var service = retrofit.create(MovieService::class.java)

        var requestText = findViewById<EditText>(R.id.textInput)
        var responseText = findViewById<TextView>(R.id.textOutput)

        findViewById<Button>(R.id.btnGet).setOnClickListener {
            makeCall {
                if (TextUtils.isEmpty(requestText.text)) {
                    service.getMovies()
                } else {
                    service.getMovie(requestText.text.toString())
                }
            }
        }
    }

    fun makeCall(action: suspend () -> retrofit2.Response<ResponseBody>) {
        CoroutineScope(Dispatchers.IO).launch {
            var response: retrofit2.Response<ResponseBody> = action()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    findViewById<TextView>(R.id.textOutput).text = formatJson(response.body()?.toString())
                } else {
                    findViewById<TextView>(R.id.textOutput).text = response.code().toString()
                }
            }
        }
    }

    fun formatJson(text: String?): String {
        var gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(JsonParser.parseString(text))
    }

}