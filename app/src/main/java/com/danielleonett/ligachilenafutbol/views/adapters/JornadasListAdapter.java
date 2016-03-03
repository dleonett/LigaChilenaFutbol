package com.danielleonett.ligachilenafutbol.views.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danielleonett.ligachilenafutbol.R;
import com.danielleonett.ligachilenafutbol.models.Jornada;
import com.danielleonett.ligachilenafutbol.models.Partido;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 02/03/2016.
 */
public class JornadasListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Jornada> mJornadas;
    private List<Partido> mPartidos;
    private OnItemClickListener mListener;
    private Context mContext;

    public static class ViewHolderItem extends RecyclerView.ViewHolder {

        @Bind(R.id.container)
        View viewContainer;
        @Bind(R.id.tv_nombre_local)
        TextView tvNombreLocal;
        @Bind(R.id.tv_nombre_visita) TextView tvNombreVisita;
        @Bind(R.id.iv_escudo_local)
        ImageView ivEscudoLocal;
        @Bind(R.id.iv_escudo_visita) ImageView ivEscudoVisita;
        @Bind(R.id.tv_marcador) TextView tvMarcador;

        public ViewHolderItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public JornadasListAdapter(Context context, List<Jornada> jornadas, OnItemClickListener listener) {
        mContext = context;
        mJornadas = jornadas;
        mListener = listener;

        mPartidos = new ArrayList<Partido>();

        Jornada jornada;
        Partido partido;
        for (int i = 0; i < mJornadas.size(); i++) {
            jornada = mJornadas.get(i);
            for (int j = 0; j < jornada.getPartidos().size(); j++) {
                partido = mJornadas.get(i).getPartidos().get(j);
                //partido.setFechaPar((i + 1) % 2 == 0);
                partido.setFechaPar(Integer.valueOf(jornada.getName().split(" ")[1]) % 2 == 0);
                mPartidos.add(partido);
            }
        }

        // <--------------------------------------------------------------------------------
        // Nota: El web service retorna partidos repetidos incluso teniendo el mismo ID
        // Esta metodologia es solo para remover dichos elementos repetidos en la lista
        Set<Partido> set = new LinkedHashSet<Partido>(mPartidos);
        mPartidos.clear();
        mPartidos.addAll(set);
        // -------------------------------------------------------------------------------->
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.item_list_partido, parent, false);
        return new ViewHolderItem(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolderItem viewHolderItem = (ViewHolderItem) holder;

        viewHolderItem.tvNombreLocal.setText(mPartidos.get(position).getNombreLocal());
        viewHolderItem.tvNombreVisita.setText(mPartidos.get(position).getNombreVisita());
        viewHolderItem.tvMarcador.setText(mContext.getString(R.string.resultado_marcador,
                mPartidos.get(position).getGolLocal(),
                mPartidos.get(position).getGolVisita()));
        Glide.with(mContext).load(mPartidos.get(position).getEscudoLocal())
                .placeholder(R.mipmap.placeholder_shield)
                .into(viewHolderItem.ivEscudoLocal);
        Glide.with(mContext).load(mPartidos.get(position).getEscudoVisita())
                .placeholder(R.mipmap.placeholder_shield)
                .into(viewHolderItem.ivEscudoVisita);

        if (mPartidos.get(position).esFechaPar()) {
            viewHolderItem.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gray_250));
        }
        else {
            viewHolderItem.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }

        viewHolderItem.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(view, mPartidos.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPartidos.size();
    }

    // Interface for receiving click events from list items
    public interface OnItemClickListener {
        void onItemClick(View view, Partido partido);
    }

}