package com.example.football

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class MyAdapter(private val leftclubNameDatabase: ArrayList<String>,private val rightclubNameDatabase: ArrayList<String>,
                private val lefticonDatabase: ArrayList<String>,private val righticonDatabase: ArrayList<String>,
                private val leftScoreDatabase: ArrayList<String>, private val rightScoreDatabase: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val TAG: String = "MyAdapter"


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        internal var leftteamName: TextView
        internal var rightteamName: TextView
        internal var leftImage: CircleImageView
        internal var rightImage: CircleImageView
        internal var leftScore: TextView
        internal var rightScore: TextView



        init {

            leftteamName = itemView.findViewById(R.id.teamName)
            leftImage = itemView.findViewById(R.id.leftIcon)
            rightteamName = itemView.findViewById(R.id.teamNameRight)
            rightImage = itemView.findViewById(R.id.right_icon)
            leftScore = itemView.findViewById(R.id.scoreLeft)
            rightScore = itemView.findViewById(R.id.scoreRight)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(TAG, "onCreateViewHolder: started.")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: started.")
        return leftclubNameDatabase.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: started.")
        holder.leftteamName.text = leftclubNameDatabase[position]
        holder.rightteamName.text = rightclubNameDatabase[position]
        holder.leftScore.text = leftScoreDatabase[position]
        holder.rightScore.text = rightScoreDatabase[position]
        Glide.with(holder.leftImage.context).asBitmap().load(lefticonDatabase[position]).into(holder.leftImage)
        Glide.with(holder.rightImage.context).asBitmap().load(righticonDatabase[position]).into(holder.rightImage)
    }
}