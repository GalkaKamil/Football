package com.example.football

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val clubNameDatabase:ArrayList<String>, private val iconDatabase: ArrayList<String>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
private val TAG: String = "MyAdapter"


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        internal var teamName: TextView
        internal var imagename: CircleImageView



        init {

            teamName = itemView.findViewById(R.id.teamName)
            imagename = itemView.findViewById(R.id.picture)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d(TAG,"onCreateViewHolder: started.")
       val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)

       return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG,"getItemCount: started.")
        return clubNameDatabase.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG,"onBindViewHolder: started.")
        holder.teamName.text=clubNameDatabase[position]
        Glide.with(holder.imagename.context).asBitmap().load(iconDatabase[position]).into(holder.imagename)
    }


}