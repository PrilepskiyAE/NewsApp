package com.prilepskiy.newsapp.ui.newsListActivity.composable

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
@Composable

fun NewsContent(url: String="https://ura.news/news/1052626832",title:String="") {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News | $title", maxLines = 1, style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis ) },
                backgroundColor = Color(0xff0f9d58),
            )
        },
        content = {
            it
            WebViewContent(url)
        }
    )
}
@Composable
fun WebViewContent(url:String){

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}
