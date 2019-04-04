package com.example.football

import android.util.Log
import org.json.JSONException
import org.json.JSONObject






class Networking {

    private lateinit var url: String

    private var awayTeamId: ArrayList<String> = ArrayList()
    private var homeTeamId: ArrayList<String> = ArrayList()
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

                while (keys.hasNext()) {
                    val key = keys.next() as String

                    if (jsonObject.getJSONObject("api").getJSONObject("fixtures").get(key) is JSONObject) {

                        netobj.leftclubNameDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("homeTeam"))
                        netobj.rightclubNameDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("awayTeam"))
                        netobj.leftscoreDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("goalsHomeTeam"))
                        netobj.rightscoreDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("goalsAwayTeam"))
                        netobj.homeTeamId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("homeTeam_id"))
                        netobj.awayTeamId.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject(key).getString("awayTeam_id"))
                    }
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


                    val key = keys.next() as String
                    if (jsonObject.getJSONObject("api").getJSONObject("teams").get(key) is JSONObject) {

                        netobj.lefticonDatabase.add(jsonObject.getJSONObject("api").getJSONObject("teams").getJSONObject(key).getString("logo"))

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

    fun pickTeamUrl(team: String){

        url = "https://www.api-football.com/demo/api/teams/team/$team"
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





}