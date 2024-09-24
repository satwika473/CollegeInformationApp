package com.example.nec_info


import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("/satwika473/csv_retreval/main/II-I%20(2)%20(2).json")  // Your API endpoint
    fun getStudents(): Call<List<Student>>
}