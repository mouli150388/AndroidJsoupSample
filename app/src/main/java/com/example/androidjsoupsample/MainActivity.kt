package com.example.androidjsoupsample

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class MainActivity : AppCompatActivity() {
    lateinit var txt_response:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txt_response=findViewById<TextView>(R.id.txt_response)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        //ParseURL().execute("https://firebase.google.com/")




    }
    fun volleyConnect(v: View)
    {
        val stringRequest= StringRequest("https://firebase.google.com/", Response.Listener {
            txt_response.setText("$it")

        }, Response.ErrorListener {

        })

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
    }
fun JsonConnect(v: View){
    val buffer = StringBuffer()
    val doc: Document = Jsoup.connect("https://firebase.google.com/").get()
    Log.d("JSwa", ("https://firebase.google.com/"))
    // Get document (HTML page) title
    val title: String = doc.title()
    Log.d("JSwA", "Title [$title]")
    buffer.append("Title: $title\r\n")


    // Get meta info
    val metaElems: Elements = doc.select("meta")
    buffer.append("META DATA\r\n")
    for (metaElem in metaElems) {
        val name = metaElem.attr("name")
        val content = metaElem.attr("content")
        buffer.append("name [$name] - content [$content] \r\n")
    }

    val topicList: Elements = doc.select("h2.topic")
    buffer.append("Topic list\r\n")
    for (topic in topicList) {
        val data = topic.text()

        buffer.append("Data [$data] \r\n")
    }
    txt_response.setText(buffer.toString())
}
}