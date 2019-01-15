package com.tusharmalik.playersearch;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPlayer extends AppCompatActivity {
    public EditText edName,edCountry,edType;
    Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
        edName = findViewById(R.id.npname);
        edCountry = findViewById(R.id.npCountry);
        edType= findViewById(R.id.nptype);
        btnadd=findViewById(R.id.btnAdd);
        PlayerDatabaseHelper myDbHelper= new PlayerDatabaseHelper(this);
        final SQLiteDatabase writeDb = myDbHelper.getWritableDatabase();



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!edName.getText().toString().isEmpty() && !edCountry.getText().toString().isEmpty() && !edType.getText().toString().isEmpty() ) {
                    PlayerTable.insertBuyerItem(new Player(0,
                                    edName.getText().toString(),
                                    edCountry.getText().toString(),
                                    edType.getText().toString()),

                            writeDb);

                    Intent i = new Intent(NewPlayer.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(NewPlayer.this, "job done" , Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(NewPlayer.this, "Please fill the incomplete fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
