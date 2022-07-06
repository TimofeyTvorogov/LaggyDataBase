package com.example.databaset;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    Cursor cursor;
    DbHelper helper;
    SQLiteDatabase db;
    FragmentManager fragmentManager;
    AddFragment addFragment;
    Button getData;
    TextView address,target,type;

    Switch aSwitch;
    boolean isOpened = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData = findViewById(R.id.get_button);
        address = findViewById(R.id.address_TV);
        target = findViewById(R.id.target_TV);
        type = findViewById(R.id.type_TV);
        aSwitch = findViewById(R.id.volunteer_switch);
        helper = new DbHelper(getBaseContext());
        db = helper.getWritableDatabase();
        fragmentManager = getSupportFragmentManager();
        addFragment = new AddFragment();
       //ContentValues values = new ContentValues(); это для проверки читает ли заполненную в коде базу (спойлер нет)
       //values.put(DbHelper.COL_ADDRESS,"shit street");
       //values.put(DbHelper.COL_TYPE,"tag");
       //values.put(DbHelper.COL_OBJECT_OF_VANDALISM,"building");
       //db.insert(DbHelper.TABLE_NAME,null, values);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aSwitch.isChecked()){
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.container_FL, addFragment)
                            .commit();

                }
                else {
                    fragmentManager.beginTransaction().remove(addFragment).commit();
                }


            }
        });
getData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String getQuery = String.format("SELECT * FROM %s",DbHelper.TABLE_NAME);
        cursor = db.rawQuery(getQuery,null);
        cursor.moveToFirst();
        address.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COL_ADDRESS)));
        target.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COL_OBJECT_OF_VANDALISM)));
        type.setText(cursor.getString(cursor.getColumnIndex(DbHelper.COL_TYPE)));
        cursor.close();
    }
});



    }

    @Override
    protected void onResume() {
        super.onResume();
    db = helper.getWritableDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();
    db.close();
    }
}