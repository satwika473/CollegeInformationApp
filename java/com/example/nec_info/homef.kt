package com.example.nec_info

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homef.newInstance] factory method to
 * create an instance of this fragment.
 */
class homef : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var webView: WebView
    lateinit var bus:Button
    lateinit var stud:Button
    lateinit var facu:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_homef, container, false)
        val  video:String="<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ECcu46cS-E0?si=xk31o70U22puDUnF\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
          //   webView = rootView.findViewById(R.id.web_view)
        bus = rootView.findViewById(R.id.bus)
        stud = rootView.findViewById(R.id.stu)
        facu = rootView.findViewById(R.id.fac)
       // webView.loadData(video,"text/html","utf-8")
        //webView.getSettings().javaScriptEnabled=true
        // webView.webChromeClient= WebChromeClient()
        bus.setOnClickListener {
            val intent = Intent(context, Bus::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for stud ImageView
        stud.setOnClickListener {
            val intent = Intent(context, StudentActivityActivity::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for facu ImageView
        facu.setOnClickListener {
            val intent = Intent(context, Faculty::class.java)
            startActivity(intent)
        }
        return rootView

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homef.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homef().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}