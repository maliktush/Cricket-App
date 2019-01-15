package com.tusharmalik.playersearch;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView PlayerList;
    PlayerAdapter playerAdapter;
    ArrayList<Player> records = new ArrayList<Player>();
    Button search;
    FloatingActionButton addb;
    EditText srch;

    @Override
    protected void onStart() {
        super.onStart();
        PlayerDatabaseHelper myDbHelper = new PlayerDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        records = PlayerTable.getAllPlayers(writeDb);
       // PlayerTable.getTableAsString(writeDb,"Sellers");
        playerAdapter = new PlayerAdapter(records,MainActivity.this);
        PlayerList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        PlayerList.setAdapter(playerAdapter);
        playerAdapter.notifyDataSetChanged();

    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       PlayerDatabaseHelper myDbHelper = new PlayerDatabaseHelper(this);



        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();
        SQLiteDatabase readDb = myDbHelper.getReadableDatabase();
        PlayerList = findViewById(R.id.PlayerList);

        playerAdapter = new PlayerAdapter(records,MainActivity.this);
        PlayerList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        PlayerList.setAdapter(playerAdapter);
        FloatingActionButton addb = (FloatingActionButton) findViewById(R.id.addb);
        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewPlayer.class));
            }
        });
        search=findViewById(R.id.btnsearch);
        srch=findViewById(R.id.edsearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerList=(RecyclerView) findViewById(R.id.PlayerList);
                records.clear();
                String word = srch.getText().toString();
                PlayerDatabaseHelper myDbHelpersearch = new PlayerDatabaseHelper(MainActivity.this);


                final SQLiteDatabase writeDb1 = myDbHelpersearch.getWritableDatabase();
                Cursor datasearch=PlayerDatabaseHelper.getInfoSearch(word,writeDb1);



                while(datasearch.moveToNext()){


                    Player worksearch=new Player();
                 //   issearch[0] =true;
                    worksearch.Name=datasearch.getString(1);
                    worksearch.Country=datasearch.getString(2);
                    worksearch.Type= datasearch.getString(3);
                 //   abc=SellerTable.getRecord(worksearch.description, writeDb1).getId();

                    records.add(worksearch);




                }







//        if (records.isEmpty()){
//
//            createTextView.setVisibility(View.VISIBLE);
//
//        }else {


                DividerItemDecoration itemDecoration1 = new DividerItemDecoration(MainActivity.this, new LinearLayoutManager(MainActivity.this).getOrientation());
                PlayerList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                PlayerList.addItemDecoration(itemDecoration1);
                PlayerList.setHasFixedSize(true);
                playerAdapter = new PlayerAdapter(records, getBaseContext());
                PlayerList.setAdapter(playerAdapter);
                playerAdapter.notifyDataSetChanged();
            }
        });

    }
}
