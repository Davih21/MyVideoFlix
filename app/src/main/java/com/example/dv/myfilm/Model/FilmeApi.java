package com.example.dv.myfilm.Model;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmeApi {

    @GET("filmes.json?alt=media&token=a2c5de2e-7bf1-4260-a438-4a3e338f9a99")
    Call<List<Filme>> getFilmes();


}
