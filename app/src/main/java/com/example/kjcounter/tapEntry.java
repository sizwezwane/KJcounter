package com.example.kjcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.kjcounter.RecyclerAdapter.ent;
import static com.example.kjcounter.RecyclerAdapter.stringNext;
import static com.example.kjcounter.RecyclerAdapter.stringPrevious;

public class tapEntry extends AppCompatActivity {

    protected static TextView detail;
    protected static Button next;
    protected static Button previous;
    protected static Button Calculator;
    protected static Button overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_entry);
    }
    @Override
    protected void onStart(){
        super.onStart();

        detail=findViewById(R.id.details);
        Calculator=findViewById(R.id.calculator);
        overview=findViewById(R.id.overview);
        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
    }
    @Override
    protected void onResume(){
        super.onResume();

        detail.setText(ent);

        Calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Calculator.getContext(),calculator.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Calculator.getContext().startActivity(intent);
            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(overview.getContext(),MainActivity.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overview.getContext().startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringNext();
                Intent intent=new Intent(next.getContext(),tapEntry.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                next.getContext().startActivity(intent);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringPrevious();
                Intent intent=new Intent(previous.getContext(),tapEntry.class);
                intent.setFlags(intent.getFlags()|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                previous.getContext().startActivity(intent);
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
}
