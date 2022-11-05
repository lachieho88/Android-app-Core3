package au.edu.swin.sdmd.core3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var country: String? = null
    private var icoCode: String? = null
    private var gold: String? = null
    private var silver: String? = null
    private var bronze: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var sharedPreferences = requireActivity().getSharedPreferences("medals",Context.MODE_PRIVATE)
        sharedPreferences?.let {
            country = it.getString("country","country")
            icoCode = it.getString("icoCode","icoCode")
            gold = it.getString("gold","gold")
            silver = it.getString("silver","silver")
            bronze = it.getString("bronze","bronze")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottomsheet_fragment,container,false)
        val countryText = view.findViewById<TextView>(R.id.country)
        countryText.text = "${this.country}"
        val icoCodeText = view.findViewById<TextView>(R.id.IOC_code)
        icoCodeText.text = "${this.icoCode}"
        val goldText = view.findViewById<TextView>(R.id.gold_medals)
        goldText.text = "${this.gold} gold medals"
        val silverText = view.findViewById<TextView>(R.id.silver_medals)
        silverText.text = "${this.silver} silver medals"
        val bronzeText = view.findViewById<TextView>(R.id.bronze_medals)
        bronzeText.text = "${this.bronze} bronze medals"
        return view
    }

}
