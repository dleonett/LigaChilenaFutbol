package com.danielleonett.ligachilenafutbol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danielleonett.ligachilenafutbol.models.Partido;
import com.danielleonett.ligachilenafutbol.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_nombre_local)
    TextView tvNombreLocal;
    @Bind(R.id.tv_nombre_visita)
    TextView tvNombreVisita;
    @Bind(R.id.iv_escudo_local)
    ImageView ivEscudoLocal;
    @Bind(R.id.iv_escudo_visita)
    ImageView ivEscudoVisita;
    @Bind(R.id.tv_marcador)
    TextView tvMarcador;
    @Bind(R.id.tv_estado)
    TextView tvEstado;
    @Bind(R.id.tv_hora_estado)
    TextView tvHoraEstado;
    @Bind(R.id.tv_fecha)
    TextView tvFecha;
    @Bind(R.id.tv_hora)
    TextView tvHora;
    @Bind(R.id.tv_estadio)
    TextView tvEstadio;
    @Bind(R.id.tv_ciudad)
    TextView tvCiudad;
    @Bind(R.id.tv_arbitro)
    TextView tvArbitro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Partido partido = bundle.getParcelable(Constants.KEY_PARTIDO);
            if (partido != null) {
                tvNombreLocal.setText(partido.getNombreLocal());
                tvNombreVisita.setText(partido.getNombreVisita());
                tvEstado.setText(partido.getEstado());
                tvHoraEstado.setText(partido.getHoraEstado());
                tvFecha.setText(partido.getFecha());
                tvHora.setText(partido.getHora());
                tvEstadio.setText(partido.getEstadio());
                tvCiudad.setText(partido.getCiudad());
                tvArbitro.setText(partido.getNombreArbitro());
                tvMarcador.setText(getString(R.string.resultado_marcador,
                        partido.getGolLocal(),
                        partido.getGolVisita()));
                Glide.with(this).load(partido.getEscudoLocal())
                        .placeholder(R.mipmap.placeholder_shield)
                        .into(ivEscudoLocal);
                Glide.with(this).load(partido.getEscudoVisita())
                        .placeholder(R.mipmap.placeholder_shield)
                        .into(ivEscudoVisita);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar mItem clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}