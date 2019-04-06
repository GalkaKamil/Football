package com.example.football

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList


class Networking {

    private lateinit var url: String

    private var awayTeamId: ArrayList<String> = ArrayList()
    private var homeTeamId: ArrayList<String> = ArrayList()
    private var leaugeId: ArrayList<String> = ArrayList()
    private var leftclubNameDatabase: ArrayList<String> = ArrayList()
    private var lefticonDatabase: ArrayList<String> = ArrayList()
    private var leftscoreDatabase: ArrayList<String> = ArrayList()
    private var rightclubNameDatabase: ArrayList<String> = ArrayList()
    private var righticonDatabase: ArrayList<String> = ArrayList()
    private var rightscoreDatabase: ArrayList<String> = ArrayList()




    companion object {
        private lateinit var netobj: Networking
        private val TAG: String="Networking"
        fun getClubNameScoreIdWithJson(jsonObject: JSONObject): Networking {

            netobj = Networking()



            try {
                val keys = jsonObject.getJSONObject("api").getJSONObject("fixtures").keys()
                var index: Int = 0
                var count: Int = 0

                while (keys.hasNext()) {
                    val key = keys.next() as String

                    if (jsonObject.getJSONObject("api").getJSONObject("fixtures").get(key) is JSONObject) {

                        netobj.leftclubNameDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("homeTeam"))
                        netobj.rightclubNameDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("awayTeam"))
                        netobj.leftscoreDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("goalsHomeTeam"))
                        netobj.rightscoreDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("goalsAwayTeam"))
                        netobj.homeTeamId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("homeTeam_id"))
                        netobj.awayTeamId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("awayTeam_id"))

                        if(index == 0 ){
                            netobj.leaugeId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("league_id"))
                            Log.d(TAG, "index 0 - id = "+jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("league_id"))
                            index++
                        }

                        if(netobj.getLeaugeId(count) != jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("league_id")){

                            netobj.leaugeId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("league_id"))
                            Log.d(TAG, "id "+jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("league_id"))
                            Log.d(TAG, "rozmiar "+netobj.leaugeId.size)
                            count++
                        }

                    }
                }


                Log.d(TAG, ""+netobj.leaugeId.size)

                for (index in 0 until netobj.leftclubNameDatabase.size){
                    netobj.lefticonDatabase.add("")
                }
            }
            catch (e: JSONException) {
                e.printStackTrace()
                Log.d(TAG, "Throws Exp !!! getClubNameScoreIdWithJson")
            }
            return netobj
        }

        fun getImagesWithJson(jsonObject: JSONObject): Networking {



            try {
                val keys = jsonObject.getJSONObject("api").getJSONObject("teams").keys()

                while (keys.hasNext()){

                    val key = keys.next() as String

                    if (jsonObject.getJSONObject("api").getJSONObject("teams").get(key) is JSONObject) {

                        for (index in 0 until netobj.leftclubNameDatabase.size){

                            if(jsonObject.getJSONObject("api").getJSONObject("teams").getJSONObject(key).getString("team_id")== netobj.getHomeTeamId(index))
                            {
                                netobj.lefticonDatabase.add(index,jsonObject.getJSONObject("api").getJSONObject("teams").getJSONObject(key).getString("logo"))
                                Log.d(TAG, "dodano do lefticonDatabse link : " + netobj.getLeftIcon(index) + " na pozycje $index")
                                Log.d(TAG, "dodano do leftclubNameDatabase nazwe klubu : " + netobj.leftclubNameDatabase[index] + "na pozycje $index")

                            }
                        }

                    }
                }
            }
            catch (e: JSONException) {
                e.printStackTrace()
                Log.d(TAG, "Throws Exp !!! getImagesWithJson")
            }

            return netobj
        }

    }

    fun pickDateUrl(date: String){

        url= "https://www.api-football.com/demo/api/fixtures/date/$date"
    }

    fun pickLeagueUrl(leagueId: String){

        url = "https://www.api-football.com/demo/api/teams/league/$leagueId"
    }

    fun getUrl():String{

        return url
    }

    fun getHomeTeamId(index: Int):String{

        return homeTeamId[index]
    }

    fun getHomeTeamId():ArrayList<String>{

        return homeTeamId
    }

    fun getAwayTeamId(index: Int):String{

        return awayTeamId[index]
    }

    fun getAwayTeamId():ArrayList<String>{

        return awayTeamId
    }

    fun getLeftClubName(index: Int):String{

        return leftclubNameDatabase[index]
    }

    fun getLeftClubName():ArrayList<String>{

        return leftclubNameDatabase
    }

    fun getRightClubName(index: Int):String{

        return rightclubNameDatabase[index]
    }

    fun getLeftIcon(index: Int):String{

        return lefticonDatabase[index]
    }

    fun getRightIcon(index: Int):String{

        return righticonDatabase[index]
    }

    fun getLeftScore(index: Int):String{

        return leftscoreDatabase[index]
    }

    fun getRightScore(index: Int):String{

        return rightscoreDatabase[index]
    }

    fun getLeaugeId(index: Int):String{

        return leaugeId[index]
    }

    fun getLeaugeId():ArrayList<String>{

        return leaugeId
    }







}