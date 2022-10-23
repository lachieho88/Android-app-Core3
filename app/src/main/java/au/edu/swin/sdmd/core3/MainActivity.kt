package au.edu.swin.sdmd.core3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val medallist = findViewById<RecyclerView>(R.id.medallist)

        val list = intiData()
        val sortedList = sortlist(list.toMutableList())

        medallist.adapter = MedalsRecyclerViewAdapter(list,sortedList)
        medallist.layoutManager = LinearLayoutManager(this)



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle item selection
        Log.i("RESULT", "hello")
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun intiData(): List<Medals> {
        val list = mutableListOf<Medals>()
        resources.openRawResource(R.raw.medallists).bufferedReader()
            .forEachLine {
                val temp = it.split(",")
                if (temp[0] != "Country") {
                    list.add(
                        Medals(
                            temp[0],
                            temp[1],
                            temp[2].toInt(),
                            temp[3].toInt(),
                            temp[4].toInt(),
                            temp[5].toInt(),
                        )
                    )
                }
            }

        list.forEach {
            println("${it.country} -- ${it.ICO_code} -- ${it.time_competed} --${it.gold} -- ${it.silver} -- ${it.bronze} -- ${it.total_medals}")
        }

        return list

    }
    //Highlight top 10 medal winning countries
    fun sortlist(medals: MutableList<Medals>): MutableList<Medals> {
        medals.sortBy {
            it.total_medals
        }
        Log.i("medals", "${medals}")
        return medals.takeLast(10).toMutableList()

    }
}