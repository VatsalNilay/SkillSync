package com.example.trial2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAlumniDetailAdapter extends RecyclerView.Adapter<RecyclerAlumniDetailAdapter.ViewHolder>{

    Context context;
    ArrayList<AlumniModel> alumniDetails;
    RecyclerAlumniDetailAdapter(Context context, ArrayList<AlumniModel> alumniDetails) {
        this.context = context;
        this.alumniDetails = alumniDetails;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.alumni_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(alumniDetails.get(position).a_name);
        holder.description.setText(alumniDetails.get(position).a_skill);
//        holder.img.setImageResource();

    }

    @Override
    public int getItemCount() {
        return alumniDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, description;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.alumniName);
            description = itemView.findViewById(R.id.alumniSpecial);
            img = itemView.findViewById(R.id.alumniImage);
        }
    }
}
