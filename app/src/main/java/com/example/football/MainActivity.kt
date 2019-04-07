package com.example.football

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.football.data.remotemodel.fixtures.Fixture
import com.example.football.data.repository.FootballDataSource
import com.example.football.data.repository.FootballRepositoryImpl
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var date: String
    private lateinit var url: String
    private var leftclubNameDatabase: ArrayList<String> = ArrayList()
    private var lefticonDatabase: ArrayList<String> = ArrayList()
    private var righttclubNameDatabase: ArrayList<String> = ArrayList()
    private var righticonDatabase: ArrayList<String> = ArrayList()
    private var leftScoreDatabase: ArrayList<String> = ArrayList()
    private var rightScoreDatabase: ArrayList<String> = ArrayList()
    private val TAG: String = "MainActivity"
    lateinit var netobj: Networking


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "OnCreate: started.")
        //navView.itemIconTintList=null
        navigationView = findViewById(R.id.navView)
        navigationView.itemIconTintList = null
        drawerLayout = findViewById(R.id.drawer_layout)

        navigationView.setNavigationItemSelectedListener { menuItem ->

            menuItem.isChecked

            drawerLayout.closeDrawers()

            true
        }
        var networking = Networking()

        date = "2018-02-24"
        networking.pickDateUrl(date)
        url = networking.getUrl()
        letsDoSomeNetworing()

        val footballRepositoryImpl = FootballRepositoryImpl()
        footballRepositoryImpl.getFixturesByDate("2018-02-24", object : FootballDataSource.GetFixturesCallback {
            override fun onDataLoaded(list: List<Fixture>) {

            }

            override fun onError(throwable: Throwable) {

            }
        })
    }

    private fun letsDoSomeNetworing() {

        var client = AsyncHttpClient()


        client.get(url, object : JsonHttpResponseHandler() {


            // w onsuccess wywołujemy metode getClubNameScoreIdWithJson w której przypisujemy do tablic dane: nazwa druzyn, wynik meczu, id druzyn
            // id druzyn jest potrzebne zeby potem wysłać requesta z którego mozemy odczytac link do logo tej druzyny.
            // dlatego druga metoda to letsDoNetworkingForLeftImage.


            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {
                // called when response HTTP status is "200 OK"
                Log.d(TAG, "JSON11111111: " + response!!.toString())
                netobj = Networking.getClubNameScoreIdWithJson(response)

                letsDoNetworkingForLeftImage()

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, e: Throwable, response: JSONObject?) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(TAG, "Request fail! Status code: $statusCode")
                Log.d(TAG, "Fail response: " + response!!)
                Log.e("ERROR", e.toString())
                Toast.makeText(this@MainActivity, "Request Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun letsDoNetworkingForLeftImage() {

        Log.d(TAG, "url = " + url)
        var client = AsyncHttpClient()
        var count: Int = 0
        for (index in 0 until netobj.getLeaugeId().size) {


            netobj.pickLeagueUrl(netobj.getLeaugeId(index))
            url = netobj.getUrl()

            client.get(url, object : JsonHttpResponseHandler() {

                override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {


                    Log.d(TAG, "RESPONSE = " + response.toString())
                    netobj = Networking.getImagesWithJson(response!!)



                    count++
                    if (count == netobj.getLeaugeId().size) {

                        Log.d(TAG, "-------------------------------------")

                        Log.d(TAG, "na pozycji 0 " + netobj.getLeftIcon(0))
                        Log.d(TAG, "na pozycji 1 " + netobj.getLeftIcon(1))
                        Log.d(TAG, "na pozycji 2 " + netobj.getLeftIcon(2))
                        Log.d(TAG, "na pozycji 3 " + netobj.getLeftIcon(3))
                        Log.d(TAG, "na pozycji 4 " + netobj.getLeftIcon(4))
                        Log.d(TAG, "na pozycji 5 " + netobj.getLeftIcon(5))
                        Log.d(TAG, "na pozycji 6 " + netobj.getLeftIcon(6))
                        Log.d(TAG, "na pozycji 7 " + netobj.getLeftIcon(7))
                        Log.d(TAG, "na pozycji 8 " + netobj.getLeftIcon(8))
                        Log.d(TAG, "na pozycji 9 " + netobj.getLeftIcon(9))
                        Log.d(TAG, "na pozycji 10 " + netobj.getLeftIcon(10))

                    }


                }

                override fun onFailure(statusCode: Int, headers: Array<Header>?, e: Throwable, response: JSONObject?) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.d(TAG, "Request fail! Status code: $statusCode")
                    Log.d(TAG, "Fail response: " + response!!)
                    Log.e("ERROR", e.toString())
                    Toast.makeText(this@MainActivity, "Request Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

    private fun letsDoNetworkingForRightImage() {

        Log.d(TAG, "url = " + url)
        var client = AsyncHttpClient()

        client.get(url, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {

                if (response != null) {
                    netobj = Networking.getImagesWithJson(response)
                    initArrays(netobj)
                }


            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, e: Throwable, response: JSONObject?) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(TAG, "Request fail! Status code: $statusCode")
                Log.d(TAG, "Fail response: " + response!!)
                Log.e("ERROR", e.toString())
                Toast.makeText(this@MainActivity, "Request Failed", Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun initArrays(netobj: Networking) {

        for (index in 0 until netobj.getLeftClubName().size) {

            leftclubNameDatabase.add(netobj.getLeftClubName(index))
            righttclubNameDatabase.add(netobj.getRightClubName(index))
            lefticonDatabase.add(netobj.getLeftIcon(index))
            righticonDatabase.add(netobj.getRightIcon(index))
            leftScoreDatabase.add(netobj.getLeftScore(index))
            rightScoreDatabase.add(netobj.getRightScore(index))

        }






        initRecyclerView()
    }

    @SuppressLint("ResourceType")
    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(
            leftclubNameDatabase,
            righttclubNameDatabase,
            lefticonDatabase,
            righticonDatabase,
            leftScoreDatabase,
            rightScoreDatabase
        )



        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


}

