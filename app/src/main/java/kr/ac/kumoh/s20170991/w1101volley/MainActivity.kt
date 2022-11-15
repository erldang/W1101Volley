package kr.ac.kumoh.s20170991.w1101volley

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20170991.w1101volley.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        queue = Volley.newRequestQueue(application)
        binding.btnConnect.setOnClickListener{
            val url = "https://yts.torrentbay.to/api/v2/list_movies.json?sort=rating&limit=30"
            val request = JsonObjectRequest(Request.Method.GET,
                url,
                null,
                {
                    Toast.makeText(application, it.toString(), Toast.LENGTH_LONG).show()
                },
                {
                    Toast.makeText(application, it.toString(), Toast.LENGTH_LONG).show()
                }
            )
            Toast.makeText(application,"눌렸습니다",Toast.LENGTH_LONG).show()

            queue.add(request)
        }
    }
}