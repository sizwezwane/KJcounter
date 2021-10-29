package com.example.kjcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import static com.example.kjcounter.MainActivity.daily;
import static com.example.kjcounter.MainActivity.dates;
import static com.example.kjcounter.MainActivity.entries;
import static com.example.kjcounter.MainActivity.sdf;
import static com.example.kjcounter.RecyclerAdapter.currentPosition;
import static com.example.kjcounter.RecyclerAdapter.ent;
import static com.example.kjcounter.RecyclerAdapter.list;

public class calculator extends AppCompatActivity {

    static Button saveEntry=null;
    static TextView enterKJ=null;
    static Spinner categories=null;
    static TextView todayInfo=null;
    static Button Exit=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }
    @Override
    protected void onStart(){
        super.onStart();

        saveEntry = findViewById(R.id.saveEntry);
        enterKJ = findViewById(R.id.entry);
        categories = findViewById(R.id.categories);
        TextView direction = findViewById(R.id.direction);
        Exit=findViewById(R.id.exit);
    }
    @Override
    protected void onResume(){
        super.onResume();

        saveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nter= enterKJ.getText().toString();
                Double enterD= Double.valueOf(nter);
                String now=sdf.format(new Date()).toString();
                String cate= categories.getSelectedItem().toString();
                String sDate= now.substring(0,10);
                String ch=dates.get(dates.size()-1);
                if(sDate.equals(ch)){
                    if (cate.equals(getString(R.string.GYM))|cate.equals(getString(R.string.JOGGING))|cate.equals("Sport")|cate.equals("Yoga")|cate.equals(getString(R.string.OTe))){
                        daily.set(dates.size()-1,daily.get(dates.size()-1)-enterD);
                    }else {
                        daily.set(dates.size()-1,daily.get(dates.size()-1)+enterD);
                    }
                }else{
                    dates.add(now.substring(0,10));
                    daily.add(enterD);
                }

                Entry Dev=new Entry(now,cate,enterD);
                ent=Dev.toString();
                entries.add(Dev);
                list.add(Dev.toString());
                saveData();


                Intent intent= new Intent(saveEntry.getContext(), tapEntry.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Exit.getContext(), MainActivity.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });


        enterKJ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String enterKJInp=enterKJ.getText().toString().trim();
                String sel=categories.getSelectedItem().toString();
                saveEntry.setEnabled(!enterKJInp.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    protected void saveData(){
        SharedPreferences sharedPreferences= getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(entries);
        editor.putString("task list",json);
        editor.apply();

        SharedPreferences sharedPreferencesD= getSharedPreferences("sharedD preferences",MODE_PRIVATE);
        SharedPreferences.Editor editorD= sharedPreferencesD.edit();
        Gson gsonD=new Gson();
        String jsonD=gsonD.toJson(daily);
        editorD.putString("tasks list",jsonD);
        editorD.apply();

        SharedPreferences sharedPreferencesDp= getSharedPreferences("sharedDp preferences",MODE_PRIVATE);
        SharedPreferences.Editor editorDp= sharedPreferencesDp.edit();
        Gson gsonDp=new Gson();
        String jsonDp=gsonDp.toJson(dates);
        editorDp.putString("taskss list",jsonDp);
        editorDp.apply();
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
}
