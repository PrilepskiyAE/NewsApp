package com.prilepskiy.newsapp.ui.newsListActivity.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.prilepskiy.newsapp.Routes
import com.prilepskiy.newsapp.core.swapList
import com.prilepskiy.newsapp.domain.model.NewsModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage



@Composable
fun DisplayNews(navController: NavHostController, list: List<NewsModel>,selectedItem:(String,String)->Unit) {

    val newsList = remember {mutableStateListOf<NewsModel>() }
    newsList.swapList(list)
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items =newsList
        ) { newsModel ->
            NewsListItem(news = newsModel) {
                selectedItem(it.url?:"",it.title?:"")
                navController.navigate(Routes.NewsContent.route)
            }
        }
    }

}
@Composable
fun NewsListItem(news: NewsModel, selectedItem: (NewsModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(news) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewsImage(news)

            Text(text = news.title.toString(), style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = news.description.toString(),
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

        }

    }

}
@Composable
fun NewsImage(news: NewsModel) {

    GlideImage(
        imageModel = {news.urlToImage} ,
        imageOptions = ImageOptions(
            contentDescription = null,
            contentScale = ContentScale.Crop,
            ), modifier = Modifier.height(200.dp).wrapContentWidth())
}