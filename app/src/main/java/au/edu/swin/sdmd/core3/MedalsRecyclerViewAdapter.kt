package au.edu.swin.sdmd.core3

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

class MedalsRecyclerViewAdapter(
    private var context: Context,
     private var values: List<Medals>,
     private var top10list : List<Medals>,
) : RecyclerView.Adapter<MedalsRecyclerViewAdapter.ViewHolder>() {

    //This function sets the views to display the items.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_row,parent,false) as View
        return ViewHolder(view)
    }


    // It returns the count of items present in the list.
    override fun getItemCount(): Int = values.size

    //This function is used to bind the list items to
    // our widgets such as TextView, ImageView, etc.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item,position)

    }

    inner class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        val layout_row = v.findViewById<LinearLayout>(R.id.layout_row)
        val country = v.findViewById<TextView>(R.id.country)
        val ICO_code = v.findViewById<TextView>(R.id.IOC_code)
        val num_of_medals = v.findViewById<TextView>(R.id.num_of_medals)

        // binds the list items to a view
        fun bind(item: Medals, position: Int){
            country.text = item.country
            ICO_code.text = item.ICO_code
            num_of_medals.text = item.total_medals.toString()

            //when an item is clicked, a Toast or Snackbar is required to denote the click,
            //and must contain details relating to the row
            layout_row.setOnClickListener {
                //saving the medals and it's properties to sharedPreferences
                var sharedPref = context.getSharedPreferences("medals",Context.MODE_PRIVATE)
                sharedPref.edit().apply{
                    putString("country",item.country)
                    putString("icoCode",item.ICO_code)
                    putString("gold",item.gold.toString())
                    putString("silver",item.silver.toString())
                    putString("bronze",item.bronze.toString())
                    apply()
                }
                //For Extension submissions, when an item is clicked,
                // a BottomSheetDialogFragment (also known as a Modal Bottom Sheet) is required to denote the click,
                // and must contain details relating to the row
                val modalBottomSheet = BottomSheetFragment()
                val supportFragmentManager = (context as MainActivity).supportFragmentManager
                modalBottomSheet.show(supportFragmentManager,"ModalBottomSheet")

                //This will save the medal position to sharedPreferences
                var sharedPreferences = context.getSharedPreferences("medalsPosition",Context.MODE_PRIVATE)
                sharedPreferences.edit().apply{
                    putInt("medalsPosition",position)
                    apply()
                }
            }
            if (item in top10list)
            {
                layout_row.setBackgroundColor(Color.GRAY)
            }
            else
            {
                layout_row.setBackgroundColor(Color.WHITE)
            }
        }
    }

}