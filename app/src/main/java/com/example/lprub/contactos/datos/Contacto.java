package com.example.lprub.contactos.datos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 11/10/2015.
 */
public class Contacto implements Serializable, Comparable<Contacto>{

    private long id;
    private String nombre;
    private List<String> numeros;

    public Contacto() {
    }

    public Contacto(long id, String nombre, List numeros) {
        this.id = id;
        this.nombre = nombre;
        this.numeros =numeros;
    }

    public long getId() {
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

    public String getNumero(int pos) {
        return numeros.get(pos);
    }

    public List getNumeros() {
        return numeros;
    }


    public void setNumeros(ArrayList numeros) {
        this.numeros = numeros;
    }
    //Metodo para añadir numero nuevo a nuestra lista de numeros
    public void addNumeroNuevo(int location, String object) {
        numeros.add(location, object);
    }

    public boolean isEmpty() {
        return numeros.isEmpty();
    }

    public int sizeNumeros() {
        return numeros.size();
    }
    //Metodo para modificar un numero ya existente en nuestra lista
    public String setNumero(int location, String object) {
        return numeros.set(location, object);
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numeros=" + numeros +
                '}';
            }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        return !(numeros != null ? !numeros.equals(contacto.numeros) : contacto.numeros != null);

    }

    @Override
    public int hashCode() {
        return numeros != null ? numeros.hashCode() : 0;
    }

    @Override
    public int compareTo(Contacto another) {
            int r=this.nombre.compareToIgnoreCase(another.nombre);
            if(r==0)
                r=(int) (this.id-another.id);
            return r;
    }
}
