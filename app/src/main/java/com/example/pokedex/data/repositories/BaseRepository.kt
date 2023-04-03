package com.example.pokedex.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <R> apiCall(api: suspend () -> Call<R>) = callbackFlow<ResultWrapper<R>> {
        withContext(Dispatchers.IO) {
            try {
                val httpResponse = api.invoke()

                httpResponse.enqueue(object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {

                        val resultResponse = when(response.code()) {
                            200 -> ResultWrapper.Success(response.body())
                            else -> ResultWrapper.ErrorGeneric(response.errorBody()!!.string())
                        }
                        trySend(resultResponse)
                    }

                    override fun onFailure(call: Call<R>, t: Throwable) {
                        Log.e("Error en response", t.toString())
                        trySend(ResultWrapper.ErrorGeneric(""))
                    }
                })
            } catch (throwable: Throwable) {
                trySend(ResultWrapper.ErrorGeneric(""))
            }
        }
        awaitClose {  }
    }
}