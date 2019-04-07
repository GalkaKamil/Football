package com.example.football

import com.google.gson.annotations.SerializedName

data class Fixturess(
    @SerializedName("awayTeam")
    val awayTeam: String,
    @SerializedName("awayTeam_id")
    val awayTeamId: String,
    @SerializedName("elapsed")
    val elapsed: String,
    @SerializedName("event_date")
    val eventDate: String,
    @SerializedName("event_timestamp")
    val eventTimestamp: String,
    @SerializedName("final_score")
    val finalScore: String,
    @SerializedName("firstHalfStart")
    val firstHalfStart: String,
    @SerializedName("fixture_id")
    val fixtureId: String,
    @SerializedName("goalsAwayTeam")
    val goalsAwayTeam: String,
    @SerializedName("goalsHomeTeam")
    val goalsHomeTeam: String,
    @SerializedName("halftime_score")
    val halftimeScore: String,
    @SerializedName("homeTeam")
    val homeTeam: String,
    @SerializedName("homeTeam_id")
    val homeTeamId: String,
    @SerializedName("league_id")
    val leagueId: String,
    @SerializedName("penalty")
    val penalty: Any,
    @SerializedName("round")
    val round: String,
    @SerializedName("secondHalfStart")
    val secondHalfStart: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("statusShort")
    val statusShort: String
)