package com.example.football.data.remotemodel.fixtures

import com.google.gson.annotations.SerializedName

data class FixturesResponse(
    @SerializedName("api")
    val fixturesResponseBody: FixturesResponseBody
)
