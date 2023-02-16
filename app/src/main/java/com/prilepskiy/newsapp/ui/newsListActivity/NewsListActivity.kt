package com.prilepskiy.newsapp.ui.newsListActivity


import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import com.prilepskiy.newsapp.domain.model.NewsModel
import com.prilepskiy.newsapp.ui.newsListActivity.composable.DisplayNews
import com.prilepskiy.newsapp.ui.newsListActivity.composable.NewsContent
import com.prilepskiy.newsapp.ui.newsListActivity.composable.ScreenMain
import com.prilepskiy.newsapp.ui.newsListActivity.composable.WebViewContent
import com.prilepskiy.newsapp.ui.theme.NewsAppTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListActivity : ComponentActivity() {
    val viewModel: NewsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTopNews()
        lifecycleScope.launch {
            viewModel.newsList.collectLatest { newsModels ->
                Log.d("TAG", "onCreate:${newsModels?.size}  ")
                setContent {
                    if (newsModels != null) {
                        ScreenMain(newsModels,
                            selectedItem=     {
                            url, Title->
                            viewModel.saveUrlNews(url, Title)
                        }, selectedUrl = {return@ScreenMain viewModel.news.value!! },{viewModel.getNews(it)})
                    }
                }
            }
        }
    }

}






