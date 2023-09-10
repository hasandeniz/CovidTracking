package com.hasandeniz.covidtracking.data.util

import com.hasandeniz.covidtracking.util.Resource
import retrofit2.HttpException
import retrofit2.Response


suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): Resource<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Resource.Success(body)
        } else {
            Resource.Error(errorMessage = response.message())
        }
    } catch (e: HttpException) {
        Resource.Error(errorMessage = e.message())
    } catch (e: Exception) {
        Resource.Error(errorMessage = e.localizedMessage ?: "Error")
    }
}