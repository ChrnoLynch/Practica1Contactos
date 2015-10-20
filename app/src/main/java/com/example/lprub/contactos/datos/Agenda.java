package com.example.lprub.contactos.datos;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.lprub.contactos.datos.GestionContactos.getListaTelefonos;

/**
 * Created by lprub on 15/10/2015.
 */
public class Agenda extends ArrayList{
    private static ArrayList<Contacto> agenda;
    
    public Agenda (Context contexto){
        List a = GestionContactos.getListaContactos(contexto);
        //ArrayList de objetos Contacto donde se guardaran todos los contactos usados por la aplicación
        agenda = new ArrayList<>();
        Contacto aux;
        for (int i = 0; a.size() > i; i++) {
            aux = (Contacto) a.get(i);
            agenda.add(new Contacto(aux.getId(), aux.getNombre(), getListaTelefonos(contexto, aux.getId())));
        }
    }

    public ArrayList<Contacto> getAgenda() {
        return agenda;
    }

    public void add(int index, Contacto object) {
        agenda.add(index, object);
    }

    public static Contacto getContacto(int indice){
        return agenda.get(indice);
    }

    public static void setContacto(int indice, Contacto contacto){
        agenda.set(indice, contacto);
    }

    public static void añadirContacto(Contacto contacto){
        agenda.add(contacto);
    }

    public static int sizeAgenda(){
        return agenda.size();
    }

    public void ordenarAgenda(){
        Collections.sort(agenda);
     }

    public void ordenarAgendaInverso(){
        Collections.sort(agenda, new ComparatorInverso());
            }
    @Override
    public Contacto get(int index) {
        return agenda.get(index);
    }
    @Override
    public int size() {
        return agenda.size();
    }
}
