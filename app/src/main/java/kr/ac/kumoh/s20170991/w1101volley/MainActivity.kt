package kr.ac.kumoh.s20170991.w1101volley


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20170991.w1101volley.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue: RequestQueue
    private val movies = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        queue = Volley.newRequestQueue(application)
        binding.btnConnect.setOnClickListener{
            val url = "https://yts.torrentbay.to/api/v2/list_movies.json?sort=rating&limit=30"
            val request = JsonObjectRequest(Request.Method.GET, url,
                null,
                {
                    movies.clear()
                    parseJson(it)
                    //Toast.makeText(application, movies.toString()
                    //    .replace(",","\n")
                    //    .replace("[\\[\\]]".toRegex(),""), Toast.LENGTH_LONG).show()

                    binding.listMovies.adapter = ArrayAdapter<String>(
                        this,android.R.layout.simple_list_item_1,movies
                    )
                },
                {
                    Toast.makeText(application, it.toString(), Toast.LENGTH_LONG).show()
                }
            )
            //Toast.makeText(application,"눌렸습니다",Toast.LENGTH_LONG).show()

            queue.add(request)
        }
    }
    private fun parseJson(obj: JSONObject){
        val data = obj.getJSONObject("data")
        val items = data.getJSONArray("mvoies")
        for (i in 0 until items.length() ){
            val item = items[i] as JSONObject
            val title = item.getString("title_long")
            movies.add(title)
        }
    }
}