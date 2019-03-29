package com.example.football

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class Networking {

    private lateinit var url: String
     var clubNameDatabase: ArrayList<String> = ArrayList()
     var iconDatabase2: ArrayList<String> = ArrayList()


    companion object {
        private val TAG: String="Networking"
        fun networkingWithJson(jsonObject: JSONObject): Networking {

            var netobj = Networking()

            try {
                netobj.clubNameDatabase.add(jsonObject.getJSONObject("api").getJSONObject("fixtures").getJSONObject("9903").getString("homeTeam"))
            }
            catch (e: JSONException) {
                e.printStackTrace()
                Log.d(TAG, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + netobj.clubNameDatabase[0])
            }
            return netobj
        }
    }

    fun pickDateUrl(date: String){

        url= "https://www.api-football.com/demo/api/fixtures/date/$date"
    }

    fun getUrl():String{

        return url
    }



}