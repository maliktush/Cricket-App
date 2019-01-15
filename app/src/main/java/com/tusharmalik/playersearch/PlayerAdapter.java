package com.tusharmalik.playersearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tushm on 13-01-2019.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.RecordViewHolder> {
    private ArrayList<Player> records;
    Context context;
    LayoutInflater layoutInflater;


    public PlayerAdapter(ArrayList<Player> records, Context context) {
        this.records = records;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        return new RecordViewHolder(li.inflate(R.layout.playerlist,parent,false));
    }
    @Override
    public void onBindViewHolder(RecordViewHolder holder, final int position) {
        final Player seller = records.get(position);
        holder.bindView(records.get(position));
    }
    @Override
    public int getItemCount() {
        return records.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvCountry,tvType;


        public RecordViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.pname);
            tvCountry=itemView.findViewById(R.id.pcountry);
            tvType=itemView.findViewById(R.id.ptype);

        }

        void bindView(Player record){

//            iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i=new Intent(HomeActivity.class,zoomactivity.class);
//                    startActivity(i);
//                }
//            });

            tvName.setText(record.getName());
            tvCountry.setText(record.getCountry());
            tvType.setText(record.getType());



        }
    }
}
