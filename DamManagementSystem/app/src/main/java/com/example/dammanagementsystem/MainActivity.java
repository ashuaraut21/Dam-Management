package com.example.dammanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button dam1,dam2,dam3,dam4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dam1=(Button)findViewById(R.id.dam1);
        dam2=(Button)findViewById(R.id.dam2);


        dam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,dam_manage.class);
                Bundle b1=new Bundle();
                b1.putString("name","DAM 1");
                i1.putExtras(b1);
                startActivity(i1);
            }
        });
        dam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,dam_manage.class);
                Bundle b1=new Bundle();
                b1.putString("name","DAM 2");
                i1.putExtras(b1);
                startActivity(i1);
            }
        });


    }
}
