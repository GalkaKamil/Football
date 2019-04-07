package com.example.football.data.service

import com.example.football.data.remotemodel.fixtures.FixturesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballServiceApi {

    @GET("fixtures/date/{date}")
    fun getFixturesByDate(@Path("date") date: String): Call<FixturesResponse>

//    @GET("https://www.api-football.com/demo/api/teams/league/{leagueId}")
//    fun getLeague(@Path("leagueId") leagueId: String): Call<LeagueResponse>
}