package com.example.usuario.tiempoynoticiassalvador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void not(View view){
        Intent i = new Intent(this, noticias.class );
        startActivity(i);
    }

    public void tie(View view){
        Intent i = new Intent(this, tiempo.class );
        startActivity(i);
    }
}
