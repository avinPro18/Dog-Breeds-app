package com.example.dogbreedapp.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.dogbreedapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import com.example.dogbreedapp.models.Result

fun <T> Context.toResultFlow(apiCall: suspend () -> Response<T>?): Flow<Result<T>> {

    return flow {
        emit(Result.Loading<T>(true))
        checkInternetConnection { internetAvailable->
            if(internetAvailable){
                val call = apiCall()
                call?.let {
                    try {
                        if(call.code() == 200){
                            call.body()?.let {
                                emit(Result.Success<T>(it))
                                emit(Result.Loading<T>(false))
                            }
                        }else{
                            call.errorBody()?.let {
                                val error = it.string()
                                it.close()
                                emit(Result.Failure<T>(error))
                                emit(Result.Loading<T>(false))
                            }
                            emit(Result.Loading<T>(false))
                        }
                    }catch (e: Exception){
                        emit(Result.Failure<T>(e.toString()))
                        emit(Result.Loading<T>(false))
                    }
                }
            }else{
                emit(Result.Failure<T>(getString(R.string.no_internet_connection)))
                emit(Result.Loading<T>(false))
            }

        }
    }

}

suspend fun Context.checkInternetConnection(internetAvailable: suspend (Boolean) -> Unit = {}){
    internetAvailable(InternetCheck.checkConnectivity(this))
}

fun Activity.logE(msg: String) = Log.e(this.javaClass.simpleName, msg)

fun Activity.showToastMsg(msg: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, msg, duration).show()

fun Activity.hideSoftKeyboard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}