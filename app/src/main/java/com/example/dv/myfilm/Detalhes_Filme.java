package com.example.dv.myfilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Detalhes_Filme extends AppCompatActivity {
    private ImageView dt_capaFilme, playVideo;
    private TextView dt_tituloFilme, dt_descricaoFilme, dt_elencoFilme;
    private Toolbar toolbar_detalhes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_filme);
//        getSupportActionBar().hide();

        IniciarComponentes();

        //Criando objeto
        String capa = getIntent().getExtras().getString("capa");
        String descricao = getIntent().getExtras().getString("descricao");
        String elenco = getIntent().getExtras().getString("elenco");
        String titulo = getIntent().getExtras().getString("titulo");
        String video = getIntent().getExtras().getString("video");

        String stVideo = video;

        Glide.with(this).load(capa).into(dt_capaFilme);
        dt_tituloFilme.setText(titulo);
        dt_descricaoFilme.setText(descricao);
        dt_elencoFilme.setText(elenco);

        //Toolbar
        toolbar_detalhes.setNavigationOnClickListener(view -> finish());

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detalhes_Filme.this, Video.class);
                intent.putExtra("video", stVideo);
                startActivity(intent);
            }
        });


    }

    public void IniciarComponentes() {
        dt_capaFilme = findViewById(R.id.dt_capaFilme);
        playVideo = findViewById(R.id.playVideo);
        dt_tituloFilme = findViewById(R.id.dt_tituloFilme);
        dt_descricaoFilme = findViewById(R.id.dt_descricaoFilme);
        dt_elencoFilme = findViewById(R.id.dt_elencoFilme);
        toolbar_detalhes = findViewById(R.id.toolbar_detalhes);

    }
}