package com.example.football.data.repository

import com.example.football.data.remotemodel.fixtures.FixturesResponse
import com.example.football.data.service.FootballServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FootballRepositoryImpl : FootballDataSource {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.api-football.com/demo/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val footbalServiceApi = retrofit.create(FootballServiceApi::class.java)

    override fun getFixturesByDate(date: String, callback: FootballDataSource.GetFixturesCallback) {
        footbalServiceApi.getFixturesByDate(date).enqueue(object : Callback<FixturesResponse> {
            override fun onFailure(call: Call<FixturesResponse>, t: Throwable) {
                callback.onError(t)
            }

            override fun onResponse(call: Call<FixturesResponse>, response: Response<FixturesResponse>) {
                if (response.isSuccessful) {

                    val fixturesMap = response.body()?.fixturesResponseBody?.fixtures

                    val fixturesList = fixturesMap?.map { it.value } ?: emptyList()

                    callback.onDataLoaded(fixturesList)
                }
            }
        })
    }
}