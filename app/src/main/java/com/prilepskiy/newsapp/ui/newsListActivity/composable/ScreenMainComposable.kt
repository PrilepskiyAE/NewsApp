package com.prilepskiy.newsapp.ui.newsListActivity.composable

import android.print.PrintAttributes.Margins
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prilepskiy.newsapp.Routes
import com.prilepskiy.newsapp.core.swapList
import com.prilepskiy.newsapp.domain.model.NewsModel

@Composable
fun ScreenMain(list: List<NewsModel>, selectedItem: (String,String)->Unit, selectedUrl: () -> Pair<String,String>,searchNews: (String)->Unit) {
    val navController = rememberNavController()
    val newsList = remember { mutableStateListOf<NewsModel>() }
    newsList.swapList(list)
    NavHost(navController = navController, startDestination = Routes.DisplayNews.route){
        composable(Routes.DisplayNews.route) {
            Column() {
                TopAppBarNewsList(searchNews)
                DisplayNews(navController = navController, newsList, selectedItem)
            }

        }

        composable(Routes.NewsContent.route){

            Log.d("TAG", "ScreenMain:${selectedItem} ")
            val url:Pair<String,String> = selectedUrl()
            NewsContent(
                url = url.first,url.second
            )
        }
    }

}