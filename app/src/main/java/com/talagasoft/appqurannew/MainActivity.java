package com.talagasoft.appqurannew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btnDaftar){
            Intent intent=new Intent("SuratActivity");
            startActivity(intent);
        }
    }
}
