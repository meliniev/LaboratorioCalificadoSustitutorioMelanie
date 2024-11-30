package com.nieves.melanie.laboratoriocalificadosustitutorio.network

import com.nieves.melanie.laboratoriocalificadosustitutorio.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
