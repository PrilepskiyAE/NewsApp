package com.prilepskiy.newsapp.ui.newsListActivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prilepskiy.newsapp.core.ActionResult
import com.prilepskiy.newsapp.domain.interactors.GetNewsUseCase
import com.prilepskiy.newsapp.domain.interactors.GetTopNewsUseCase
import com.prilepskiy.newsapp.domain.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel@Inject constructor(private val getNewsUseCase: GetNewsUseCase,private val getTopNewsUseCase: GetTopNewsUseCase):ViewModel() {

    private val _newsList: MutableStateFlow<List<NewsModel>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val newsList =_newsList.asStateFlow()
    private val _news: MutableStateFlow<Pair<String,String>?> by lazy {
        MutableStateFlow(
            null
        )
    }
    val news =_news.asStateFlow()

    fun getNews(search:String){
        viewModelScope.launch {
            when(val result=getNewsUseCase(search)){
                is ActionResult.Success ->{
                    _newsList.emit(result.data)
                }
                is ActionResult.Error ->{}
            }
        }
    }
    fun clearNews(){
        viewModelScope.launch {
            _newsList.emit(listOf())
        }
    }

    fun saveUrlNews(url:String,title:String){
        viewModelScope.launch {
            _news.emit(Pair<String,String>(url,title))
        }
    }
    fun getTopNews(){
        viewModelScope.launch {
            when(val result=getTopNewsUseCase("ru")){
                is ActionResult.Success ->{
                    _newsList.emit(result.data)
                }
                is ActionResult.Error ->{
                    Log.d("TAG", "getTopNews: ${result.errors} ")
                }
            }
        }
    }

}