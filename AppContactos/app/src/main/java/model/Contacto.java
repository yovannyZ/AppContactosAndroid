package model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Yovanny on 29/01/2017.
 */

public class Contacto
{
    private int id;
    private String nombre;
    private String apellido;
    private String fechaNac;
    private String tipocontacto;
    private String fijo;
    private String movil;
    private String email;
    private String direccion;
    private String URL;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTipocontacto() {
        return tipocontacto;
    }

    public void setTipocontacto(String tipocontacto) {
        this.tipocontacto = tipocontacto;
    }

    public String getFijo() {
        return fijo;
    }

    public void setFijo(String fijo) {
        this.fijo = fijo;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getURL() { return URL; }

    public void setURL(String URL) { this.URL = URL; }

    @Override
    public String toString() {
        return nombre +" "+ apellido;
    }
}
