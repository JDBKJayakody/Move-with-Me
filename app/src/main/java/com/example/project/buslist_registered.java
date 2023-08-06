package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class buslist_registered extends AppCompatActivity {
    RecyclerView recyclerView_1;
    ArrayList<String> from,to,seats,date;
    DBHelper db;
    MyAdapter adapter;
    int[] busimages={R.drawable.bus};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslist_registered);
        db=new DBHelper(this);
        from=new ArrayList<>();
        to=new ArrayList<>();
        seats=new ArrayList<>();
        date=new ArrayList<>();
        recyclerView_1=findViewById(R.id.recyclerview_1);
        adapter=new MyAdapter(this,from,to,seats,date);
        recyclerView_1.setAdapter(adapter);
        recyclerView_1.setLayoutManager(new LinearLayoutManager(this));
        displayData();


    }

    private void displayData() {
        Cursor cursor=db.viewbuses();
        if (cursor.getCount()==0){
           Toast.makeText(buslist_registered.this,"No entry exists",Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()){
            from.add(cursor.getString(1));
            to.add(cursor.getString(2));
            date.add(cursor.getString(3));
            seats.add(cursor.getString(4));
        }
    }
}