package com.prilepskiy.newsapp.ui.newsListActivity.composable

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun TopAppBarNewsList(searchNews: (String)->Unit){
    var searchValues by remember { mutableStateOf("") }
        TopAppBar(modifier = Modifier.height(100.dp),

            elevation = 4.dp,
            title= {searchValues= OutlinedTextFieldComposable() },
            backgroundColor =  Color(0xff0f9d58),
            actions = {
                IconButton(onClick = { searchNews(searchValues)  }) {
                    Icon(Icons.Filled.Search, null,
                        Modifier
                            .height(30.dp)
                            .width(30.dp))
                }

            })


}

@Composable
fun OutlinedTextFieldComposable():String {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        label = { Text("search news") }

    )
    return text
}