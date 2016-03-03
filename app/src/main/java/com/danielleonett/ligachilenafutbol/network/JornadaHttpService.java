package com.danielleonett.ligachilenafutbol.network;

import com.danielleonett.ligachilenafutbol.models.Jornada;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 02/03/2016.
 */
public interface JornadaHttpService {

    @GET("/Futbol/1.0/Resumen/Fixture")
    Call<List<Jornada>> getAll(@Query("campeonato") String campeonato, @Query("estado") String estado);

}
