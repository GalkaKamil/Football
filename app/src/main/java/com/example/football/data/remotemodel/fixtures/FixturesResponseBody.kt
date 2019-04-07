package com.example.football.data.remotemodel.fixtures

import com.google.gson.annotations.SerializedName


data class FixturesResponseBody(

    @SerializedName("results")
    val results: Int,

    @SerializedName("fixtures")
    val fixtures: Map<Int, Fixture>,

    @SerializedName("WARNING")
    val warning : String
)