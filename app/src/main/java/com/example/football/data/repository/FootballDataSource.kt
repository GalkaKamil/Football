package com.example.football.data.repository

import com.example.football.data.remotemodel.fixtures.Fixture

interface FootballDataSource {

    interface GetFixturesCallback {
        fun onDataLoaded(list: List<Fixture>)

        fun onError(throwable: Throwable)
    }

//    interface GetLeagueCallback {
//
//    }

    fun getFixturesByDate(date: String, callback: GetFixturesCallback)

//    fun getLeague(leagueId: String, callback: GetLeagueCallback)
}