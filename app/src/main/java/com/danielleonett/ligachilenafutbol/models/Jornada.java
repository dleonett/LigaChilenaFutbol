package com.danielleonett.ligachilenafutbol.models;

import com.danielleonett.ligachilenafutbol.network.HttpClientGenerator;
import com.danielleonett.ligachilenafutbol.network.JornadaHttpService;
import com.danielleonett.ligachilenafutbol.utils.Constants;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by user on 02/03/2016.
 */
public class Jornada {

    int id;
    String name;
    String desde;
    String hasta;
    String temporada;
    String version;
    String dia;
    String fecha;
    List<Partido> partidos;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesde() {
        return desde;
    }

    public String getHasta() {
        return hasta;
    }

    public String getTemporada() {
        return temporada;
    }

    public String getVersion() {
        return version;
    }

    public String getDia() {
        return dia;
    }

    public String getFecha() {
        return fecha;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public static HttpClient getHttpClient() {
        return new HttpClient();
    }

    public static class HttpClient {

        private JornadaHttpService mJornadaHttpService;

        public HttpClient() {
            mJornadaHttpService = HttpClientGenerator.createClient(JornadaHttpService.class, Constants.URL_BASE);
        }

        public void getAll(Callback<List<Jornada>> callback) {
            mJornadaHttpService
                    .getAll("chile", "anteriores")
                    .enqueue(callback);
        }

    }
}