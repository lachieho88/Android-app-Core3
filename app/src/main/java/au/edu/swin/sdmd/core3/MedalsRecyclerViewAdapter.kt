package au.edu.swin.sdmd.core3

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MedalsRecyclerViewAdapter(
     private var values: List<Medals>,
     private var top10list : List<Medals>
) : RecyclerView.Adapter<MedalsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_row,parent,false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)

    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        val layout_row = v.findViewById<LinearLayout>(R.id.layout_row)
        val country = v.findViewById<TextView>(R.id.Country)
        val ICO_code = v.findViewById<TextView>(R.id.IOC_code)
        val num_of_medals = v.findViewById<TextView>(R.id.Number_of_medals)
        fun bind(item: Medals){
            country.text = item.country
            ICO_code.text = item.ICO_code
            num_of_medals.text = item.total_medals.toString()

            if (item in top10list)
            {
                layout_row.setBackgroundColor(Color.GRAY)
            }
            else{
                layout_row.setBackgroundColor(Color.WHITE)

            }


        }
    }

}