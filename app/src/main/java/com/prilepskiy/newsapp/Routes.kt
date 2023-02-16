package com.prilepskiy.newsapp

sealed class Routes(val route: String){
    object DisplayNews : Routes("DisplayNews")
    object NewsContent : Routes("NewsContent")
}
