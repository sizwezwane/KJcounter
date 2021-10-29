package com.example.kjcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor mEditor;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerAdapter adapter;
    private TextView todayInfo;

    private Button Calculator;
    static ArrayList<Entry> entries;

    static SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    static ArrayList<String> ll;
    static Double dailyAverage;
    static String dailyStr;
    static ArrayList<String> dates;
    static ArrayList<Double> daily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
        ll= getStringArrayEntries();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Calculator=findViewById(R.id.calculator);
        todayInfo=findViewById(R.id.infoToday);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter= new RecyclerAdapter(ll);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


    }
    @Override
    protected void onResume(){
        super.onResume();

        if ((daily.size()==0)&&(dates.size()==0)){
            daily.add(0.0);
            dates.add("");
        }

        Double sum=0.0;

        for(Double i : daily){
            sum=sum+i;
        }
        if(daily.size()>1&&dates.get(0).equals("")){
            dailyAverage=sum/(daily.size()-1);
        }else{
            dailyAverage=sum/(daily.size());
        }
        String now2=sdf.format(new Date()).toString();

        dailyStr=getString(R.string.DATE)+now2.substring(0,10)+"\n"+getString(R.string.TODAY)+daily.get(daily.size()-1)+"\n"+getString(R.string.AVERAGE)+dailyAverage;
        todayInfo.setText(dailyStr);


        Calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double jkl=daily.get(daily.size()-1);
                Intent intent=new Intent(Calculator.getContext(), calculator.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Calculator.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public static ArrayList<String> getStringArrayEntries(){
        ArrayList<String> STRentries= new ArrayList<>();
        for (Entry i: entries){
            STRentries.add(i.toString());
        }
        return STRentries;
    }
    protected void loadData(){
        SharedPreferences sharedPreferences= getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("task list",null);
        Type type=new TypeToken<ArrayList<Entry>>() {}.getType();
        entries=gson.fromJson(json,type);

        if (entries==null){
            entries=new ArrayList<>();
        }

        SharedPreferences sharedPreferencesD= getSharedPreferences("sharedD preferences",MODE_PRIVATE);
        Gson gsonD=new Gson();
        String jsonD=sharedPreferencesD.getString("tasks list",null);
        Type typeD=new TypeToken<ArrayList<Double>>() {}.getType();
        daily=gsonD.fromJson(jsonD,typeD);

        if (daily==null){
            daily=new ArrayList<>();
        }

        SharedPreferences sharedPreferencesDp= getSharedPreferences("sharedDp preferences",MODE_PRIVATE);
        Gson gsonDp=new Gson();
        String jsonDp=sharedPreferencesDp.getString("taskss list",null);
        Type typeDp=new TypeToken<ArrayList<String>>() {}.getType();
        dates=gsonDp.fromJson(jsonDp,typeDp);

        if (dates==null){
            dates=new ArrayList<>();
        }

    }
}
