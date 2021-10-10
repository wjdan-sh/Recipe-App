package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class Data(val title:String , val author:String ,val ingredients:String , val instructions:String )

interface APIInterface {
    @GET("/recipes/")
    fun doGetListResources(): Call<List<RecipeList?>>

    @POST("/recipes/")
    fun adduser(@Body userdata: Data ): Call<Data>
}