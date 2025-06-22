package com.example.dv.myfilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dv.myfilm.Adapter.AdapterFilme;
import com.example.dv.myfilm.Model.Filme;
import com.example.dv.myfilm.Model.FilmeApi;
import com.example.dv.myfilm.OnClick.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_filmes;
    private ArrayList<Filme> FilmeList;
    private AdapterFilme adapterFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();
        FilmeList = new ArrayList<>();
//        getSupportActionBar().hide();

        //evento de Click da recyclerView
        recyclerView_filmes.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(), recyclerView_filmes, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), Detalhes_Filme.class);
                intent.putExtra("capa", FilmeList.get(position).getCapa());
                intent.putExtra("descricao", FilmeList.get(position).getDescricao());
                intent.putExtra("elenco", FilmeList.get(position).getElenco());
                intent.putExtra("titulo", FilmeList.get(position).getTitulo());
                intent.putExtra("video", FilmeList.get(position).getVideo());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));

        //Confioguração do Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://firebasestorage.googleapis.com/v0/b/app-delivery-ba43e.firebasestorage.app/o/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        //Iniciar o Retrofit
        FilmeApi filmeApi = retrofit.create(FilmeApi.class);
        Call<List<Filme>> call = filmeApi.getFilmes();
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Filme>> call, Response<List<Filme>> response) {

                if (response.code() != 200) {
                    return;

                }
                List<Filme> filmes = response.body();

                for(Filme filme : filmes) {
                    FilmeList.add(filme);
                }

                adapterFilme = new AdapterFilme(getApplicationContext(), FilmeList);
                recyclerView_filmes.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                recyclerView_filmes.setHasFixedSize(true);
                recyclerView_filmes.setAdapter(adapterFilme);

            }

            @Override
            public void onFailure(retrofit2.Call<List<Filme>> call, Throwable t) {

            }
        });


    }

    public void IniciarComponentes() {
        recyclerView_filmes = findViewById(R.id.recyclerView_filmes);
    }
}