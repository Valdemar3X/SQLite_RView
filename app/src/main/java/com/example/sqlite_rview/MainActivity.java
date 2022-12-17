package com.example.sqlite_rview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText myProductName;
    private GroceryAdapter mAdapter;
    private TextView myAmount;
    private int number = 0;
    private SQLiteDatabase myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myProductName = findViewById(R.id.et_product);
        myAmount = findViewById(R.id.tv_result);

        DbHelper dbHelper = new DbHelper(this);
        myDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GroceryAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        Button myIncrease = findViewById(R.id.b_plus);
        Button myDecrease = findViewById(R.id.b_minus);
        Button add = findViewById(R.id.b_add);

        myIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increase();


            }
        });
        myDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease();

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

    }
    private void increase(){
        number++;
        myAmount.setText(String.valueOf(number));
    }
    private void decrease(){
        if (number > 0){
            number--;
            myAmount.setText(String.valueOf(number));
        }

    }
    private void addItem(){
        if(myAmount.getText().toString().trim().length() ==0 || number == 0){
            return;
        }
        String name = myProductName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.DbConstants.COLUMN_NAME, name);
        cv.put(MyConstants.DbConstants.COLUMN_AMOUNT, number);
        myDatabase.insert(MyConstants.DbConstants.TABLE_NAME,null,cv);

        mAdapter.swapCursor(getAllItems());
        myProductName.getText().clear();
    }

    private Cursor getAllItems(){
        return myDatabase.query(
                MyConstants.DbConstants.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MyConstants.DbConstants.COLUMN_TIMESTAMP + " DESC"
        );
    }
}