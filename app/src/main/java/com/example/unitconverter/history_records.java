package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class history_records extends AppCompatActivity {

    TextView t1,t2;
    Button n,p,e;
    SQLiteDatabase db;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_records);

        t1 = findViewById(R.id.record1);
        t2 = findViewById(R.id.record2);
        n = findViewById(R.id.next);
        p = findViewById(R.id.prev);
        e = findViewById(R.id.exit);

        db = openOrCreateDatabase("mydb", MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from records", null);
        cursor.moveToFirst();

        t1.setText(cursor.getString(cursor.getColumnIndex("fromunitname")));
        t1.setText(cursor.getString(cursor.getColumnIndex("fromunit")));
        t2.setText(cursor.getString(cursor.getColumnIndex("tounitname")));
        t2.setText(cursor.getString(cursor.getColumnIndex("tounit")));

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cursor.moveToNext();
                    t1.setText(cursor.getString(cursor.getColumnIndex("fromunitname")));
                    t1.setText(cursor.getString(cursor.getColumnIndex("fromunit")));
                    t2.setText(cursor.getString(cursor.getColumnIndex("tounitname")));
                    t2.setText(cursor.getString(cursor.getColumnIndex("tounit")));

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"You are seeing the last record",Toast.LENGTH_LONG).show();
                }
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    cursor.moveToPrevious();
                    t1.setText(cursor.getString(cursor.getColumnIndex("fromunitname")));
                    t1.setText(cursor.getString(cursor.getColumnIndex("fromunit")));
                    t2.setText(cursor.getString(cursor.getColumnIndex("tounitname")));
                    t2.setText(cursor.getString(cursor.getColumnIndex("tounit")));

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"You are seeing the first record",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}