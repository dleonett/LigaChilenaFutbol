package com.danielleonett.ligachilenafutbol.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 02/03/2016.
 */
public class Partido implements Parcelable {

    private int idPartido;
    private String fecha;
    private String hora;

    // Estado del partido
    private int idEstado;
    private String estado;
    private String horaEstado;

    // Estadio
    private String estadio;
    private String ciudad;

    // Arbitro
    private int idArbitro;
    private String nombreArbitro;

    // Equipo local
    private String idLocal;
    private String nombreLocal;
    private String escudoLocal;
    private String golLocal;

    // Equipo visitante
    private String idVisita;
    private String nombreVisita;
    private String escudoVisita;
    private String golVisita;

    private boolean fechaPar;

    protected Partido(Parcel in) {
        idPartido = in.readInt();
        fecha = in.readString();
        hora = in.readString();
        idEstado = in.readInt();
        estado = in.readString();
        horaEstado = in.readString();
        estadio = in.readString();
        ciudad = in.readString();
        idArbitro = in.readInt();
        nombreArbitro = in.readString();
        idLocal = in.readString();
        nombreLocal = in.readString();
        escudoLocal = in.readString();
        golLocal = in.readString();
        idVisita = in.readString();
        nombreVisita = in.readString();
        escudoVisita = in.readString();
        golVisita = in.readString();
        fechaPar = in.readByte() != 0;
    }

    public static final Creator<Partido> CREATOR = new Creator<Partido>() {
        @Override
        public Partido createFromParcel(Parcel in) {
            return new Partido(in);
        }

        @Override
        public Partido[] newArray(int size) {
            return new Partido[size];
        }
    };

    public int getIdPartido() {
        return idPartido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public String getHoraEstado() {
        return horaEstado;
    }

    public String getEstadio() {
        return estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public String getNombreArbitro() {
        return nombreArbitro;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public String getEscudoLocal() {
        return escudoLocal;
    }

    public String getGolLocal() {
        return golLocal;
    }

    public String getIdVisita() {
        return idVisita;
    }

    public String getNombreVisita() {
        return nombreVisita;
    }

    public String getEscudoVisita() {
        return escudoVisita;
    }

    public String getGolVisita() {
        return golVisita;
    }

    public boolean esFechaPar() {
        return fechaPar;
    }

    public void setFechaPar(boolean fechaPar) {
        this.fechaPar = fechaPar;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return this.idPartido == ((Partido) obj).idPartido;
    }

    @Override
    public int hashCode() {
        return String.valueOf(this.idPartido).hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idPartido);
        parcel.writeString(fecha);
        parcel.writeString(hora);
        parcel.writeInt(idEstado);
        parcel.writeString(estado);
        parcel.writeString(horaEstado);
        parcel.writeString(estadio);
        parcel.writeString(ciudad);
        parcel.writeInt(idArbitro);
        parcel.writeString(nombreArbitro);
        parcel.writeString(idLocal);
        parcel.writeString(nombreLocal);
        parcel.writeString(escudoLocal);
        parcel.writeString(golLocal);
        parcel.writeString(idVisita);
        parcel.writeString(nombreVisita);
        parcel.writeString(escudoVisita);
        parcel.writeString(golVisita);
        parcel.writeByte((byte) (fechaPar ? 1 : 0));
    }
}
