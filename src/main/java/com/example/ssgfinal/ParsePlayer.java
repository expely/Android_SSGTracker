package com.example.ssgfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParsePlayer extends RecyclerView.Adapter<ParsePlayer.ViewHolder> {

    private ArrayList<ParsePlayerItems> parseItems;
    private Context context;

    public ParsePlayer(ArrayList<ParsePlayerItems> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParsePlayer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_player, parent, false);
        return new ParsePlayer.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParsePlayer.ViewHolder holder, int position) {
        ParsePlayerItems parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.subTextView.setText(parseItem.getSubTitle());
        holder.subSubTextView.setText(parseItem.getSubSubTitle());
    }


    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView subTextView;
        TextView subSubTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.ptextView);
            subTextView = itemView.findViewById(R.id.psubTextView);
            subSubTextView = itemView.findViewById(R.id.psubsubTextView);
        }


    }
}
