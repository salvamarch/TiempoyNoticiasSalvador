package com.example.usuario.tiempoynoticiassalvador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class noticias extends AppCompatActivity {
Button mos;
ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        mos=findViewById(R.id.mos);
        lista=findViewById(R.id.lista);
    }

    public void inicio(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
