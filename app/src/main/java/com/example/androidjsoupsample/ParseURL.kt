package com.example.androidjsoupsample

import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


class ParseURL (): AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String?): String? {

        val buffer = StringBuffer()
        try {
            Log.d("JSwa", ("Connecting to [" + params.get(0)).toString() + "]")
            val doc: Document = Jsoup.connect(params.get(0)).get()
            Log.d("JSwa", ("Connected to [" + params.get(0)).toString() + "]")
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

            Log.e("Response","Response data ${buffer.toString()}")
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return ""
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

    }
}