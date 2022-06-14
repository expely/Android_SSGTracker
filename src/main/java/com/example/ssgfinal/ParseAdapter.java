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

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.subTextView.setText(parseItem.getSubTitle());
        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        TextView subTextView;
        TextView subSubTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            subTextView = itemView.findViewById(R.id.subTextView);
            subSubTextView = itemView.findViewById(R.id.psubsubTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ParseItem parseItem = parseItems.get(position);

            if (context instanceof RocketLeague) {
                Intent intent = new Intent(context, RLPlayer.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("mainUrl", parseItem.getMainUrl());
                context.startActivity(intent);
            } else if (context instanceof HaloInfinite) {
                Intent intent = new Intent(context, HIPlayer.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("mainUrl", parseItem.getMainUrl());
                context.startActivity(intent);
            } else if (context instanceof Rainbow6) {
                Intent intent = new Intent(context, R6Player.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("mainUrl", parseItem.getMainUrl());
                context.startActivity(intent);
            } else if (context instanceof IRacing) {
                Intent intent = new Intent(context, IRacingPlayer.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("mainUrl", parseItem.getMainUrl());
                context.startActivity(intent);
            } else if (context instanceof Apex) {
                Intent intent = new Intent(context, ApexPlayer.class);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("mainUrl", parseItem.getMainUrl());
                context.startActivity(intent);
            }
        }
    }
}
