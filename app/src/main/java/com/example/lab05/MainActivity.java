package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvContent;
    List<String> arrList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","onCreate() method started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContent = findViewById(R.id.lvContent);
        arrList.add("press Get Rates!");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrList);
        lvContent.setAdapter(arrayAdapter);
    }

    public void onBtnDownloadClick(View view) {
        Log.d("MainActivity","onBtnDownloadClick() method started");
        arrList.clear();
        arrList.add("Loading");
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrList);
        lvContent.setAdapter(arrayAdapter);
        new DataLoader(){
            @Override
            public void onPostExecute(String result)
            {
                arrList.clear();
                String temp = "";
                for(int i = 0; i < result.length(); i++){
                    if(result.charAt(i) == '\n'){
                        arrList.add(temp);
                        temp = "";
                    }
                    else{
                        temp += result.charAt(i);
                    }
                }
                arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrList);
                lvContent.setAdapter(arrayAdapter);
            }
        }.execute();
    }
}