package com.prilepskiy.newsapp.core


import androidx.compose.runtime.snapshots.SnapshotStateList
import retrofit2.Response

suspend fun <R> makeApiCall(call: suspend () -> ActionResult<R>) = try {
    call()
} catch (e: Exception) {
    ActionResult.Error(e)
}
fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}
fun <Model> analyzeResponse(
    response: Response<Model>
): ActionResult<Model> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            responseBody?.let {
                ActionResult.Success(it)
            } ?: ActionResult.Error(CallException(response.code(), response.message()))
        }
        else -> {
            ActionResult.Error(CallException(response.code(), response.message()))
        }
    }
}



