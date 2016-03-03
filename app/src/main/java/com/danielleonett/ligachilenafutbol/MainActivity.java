package com.danielleonett.ligachilenafutbol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.danielleonett.ligachilenafutbol.models.Jornada;
import com.danielleonett.ligachilenafutbol.models.Partido;
import com.danielleonett.ligachilenafutbol.utils.Constants;
import com.danielleonett.ligachilenafutbol.views.SimpleDividerItemDecoration;
import com.danielleonett.ligachilenafutbol.views.adapters.JornadasListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements JornadasListAdapter.OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress)
    ProgressBar progressBar;

    private LinearLayoutManager mLayoutManager;
    private JornadasListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject views
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        Jornada.getHttpClient().getAll(new Callback<List<Jornada>>() {
            @Override
            public void onResponse(Call<List<Jornada>> call, Response<List<Jornada>> response) {
                ArrayList<Jornada> jornadas = (ArrayList<Jornada>) response.body();

                mAdapter = new JornadasListAdapter(MainActivity.this, jornadas, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Jornada>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(View view, Partido partido) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.KEY_PARTIDO, partido);
        startActivity(intent);
    }
}
