package au.edu.swin.sdmd.core3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val medals = intent.getParcelableExtra<Medals>("medals")
        val tvFact = findViewById<TextView>(R.id.fact)


        medals?.let {
            tvFact.text = "The last country clicked was ${it.country} (${it.ICO_code})"

        }





    }
}