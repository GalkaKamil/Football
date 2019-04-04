package com.example.football;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImagesUrls;
    private Context mContext;
    private DrawerLayout mLayout;

    public RecyclerViewAdapter(Context mContext,ArrayList mImageNames, ArrayList mImagesUrls,DrawerLayout mLayout) {
        this.mImageNames = mImageNames;
        this.mImagesUrls = mImagesUrls;
        this.mContext = mContext;
        this.mLayout = mLayout;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        View view = LayoutInflater.from(mLayout.getContext()).inflate(R.layout.layout_listitem,mLayout,false);
        ViewHolder holder = new ViewHolder(view);

        for(int ih=0; ih<10;ih++){


        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called ");

        Glide.with(mContext).asBitmap().load(mImagesUrls.get(position)).into(viewHolder.image);
        viewHolder.imagename.setText(mImageNames.get(position));
        viewHolder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        CircleImageView image;
        TextView imagename;
        RelativeLayout parentlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagename = itemView.findViewById(R.id.leftIcon);
            parentlayout = itemView.findViewById(R.id.parent_layout);

        }
    }

}
