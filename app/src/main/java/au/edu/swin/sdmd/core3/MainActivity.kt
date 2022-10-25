package au.edu.swin.sdmd.core3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var list: List <Medals>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val medallist = findViewById<RecyclerView>(R.id.medallist)
        //creating a list of medals
         list = intiData()
        //creating a sortedList which returns the top 10 medallists
        val sortedList = sortlist(list.toMutableList())

        //this will pass the list and the sorted list into the Adapter
        medallist.adapter = MedalsRecyclerViewAdapter(this,list,sortedList)
        // this creates a vertical layout Manager
        medallist.layoutManager = LinearLayoutManager(this)

    }
    fun showDetail(item: Medals){
        val i = Intent(this,DetailActivity::class.java)
        i.putExtra("medals",item)
        startActivity(i)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Handle item selection
        Log.i("RESULT", "hello")
        //we get access to sharedPreferences
        var sharedPreferences = getSharedPreferences("medalsPosition",Context.MODE_PRIVATE)
        //we get the metals position
        var medalsPosition:Int = sharedPreferences.getInt("medalsPosition",-1)
        //We call the showDetail function to move to the other screen and
        // we can save the position
        showDetail(list[medalsPosition])
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
        return medals.takeLast(10).toMutableList()


    }
}