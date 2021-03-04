package com.hst.recycleview_api;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<ReportsViewModel> data = new ArrayList<>();
    private Context context;


    public Adapter(Context context, ArrayList<ReportsViewModel> data) {
        this.context = context;
        this.data = data;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        ImageView author_img;

        public ViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            author_img= itemView.findViewById(R.id.pic);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(data.get(i));
        final ReportsViewModel d = data.get(i);
        viewHolder.author.setText(d.getAuthor());
      //  Toast.makeText(context, d.getAuthor(), Toast.LENGTH_SHORT).show();

        //    viewHolder.date.setText(d.getDate());
/*
        viewHolder.view_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(d.getComplete_document()));
                context.startActivity(intent);

            }
        });
*/
        Glide.with(context).load("https://picsum.photos/300/300?image="+d.getId()).into(viewHolder.author_img);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(ArrayList<ReportsViewModel> datas) {
        data = new ArrayList<>(datas);
    }


}