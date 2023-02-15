package com.prilepskiy.newsapp.ui.newsListActivity


import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import com.prilepskiy.newsapp.ui.newsListActivity.composable.WebViewContent
import com.prilepskiy.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListActivity : ComponentActivity() {
    val viewModel: NewsListViewModel by viewModels()
   lateinit var url:String
    lateinit var title:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTopNews()
        lifecycleScope.launchWhenStarted {
            viewModel.newsList.collectLatest {
                Log.d("TAG", "onCreate:${it?.get(1)} ")
                if (it != null) {
                    url = it.get(0).url.toString()
                    title=it.get(0).title.toString()
                    setContent {
                        NewsContent(url, title = title)
                    }
                }
            }
        }

    }
    @Composable

    fun NewsContent(url: String,title:String) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("News | $title") },
                    backgroundColor = Color(0xff0f9d58)
                )
            },
            content = {
                it
                WebViewContent(url)
            }
        )
    }

}

