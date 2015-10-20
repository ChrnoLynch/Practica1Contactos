package com.example.lprub.contactos.actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lprub.contactos.adaptadores.Adaptador;
import com.example.lprub.contactos.R;
import com.example.lprub.contactos.datos.Agenda;

public class ActividadPrincipal extends AppCompatActivity {
    private Agenda agenda;
    private Adaptador clAdaptador;
    private ListView lv;

    @Override
    protected void onStart() {
        super.onStart();
        clAdaptador.notifyDataSetChanged();
        ordenarAgenView(lv.getSelectedView());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        iniciar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_añadir) {
            add();
            return true;
        }
        if (id == R.id.action_ordenarDes) {
            agenda.ordenarAgenda();
            clAdaptador.notifyDataSetChanged();
            return true;
        }
        if (id == R.id.action_ordenarAsc) {
            agenda.ordenarAgendaInverso();
            clAdaptador.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Recogemos la información sobre que item es el que hemos seleccionado.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int indice = info.position;
        View v = info.targetView;
        //Aqui es donde segun que opcion del menu contextual hemos seleccionado, se realizara un metodo u otro.
        switch (item.getItemId()) {
            case R.id.mneditar:
                Intent intent=new Intent(this, Editcontact.class);
                Bundle bundle=new Bundle();
                bundle.putInt("posicion", indice);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.mnborrar:
                borrar(indice);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void iniciar() {
        lv = (ListView) findViewById(R.id.lvContactos);
        agenda = new Agenda(this);
        //Crear objeto adaptador y ponerselo a nuestro listview
        clAdaptador = new Adaptador(this, R.layout.itemlist, agenda);
        lv.setAdapter(clAdaptador);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                llamar(posicion);
            }
        });

//        lv.getItemAtPosition()
        //Registrar el control LisrView para mostrar un menu contextual
        registerForContextMenu(lv);
    }

    public void llamar(int posicion){
        String fono=Agenda.getContacto(posicion).getNumero(0);
        Uri numero = Uri.parse( "tel:" + fono.toString() );
        Intent intent = new Intent(Intent.ACTION_CALL, numero);
        startActivity(intent);
    }



    public void borrar(final int indice) {
        String s=(this.getString(R.string.desea)+agenda.get(indice).getNombre()+"?");
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(this.getString(R.string.dialogo_borrar));
        dialogo1.setMessage(s);
        dialogo1.setCancelable(false);
        dialogo1.setNegativeButton(this.getString(R.string.cancelar), null);
        dialogo1.setPositiveButton(this.getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                agenda.getAgenda().remove(indice);
                clAdaptador.notifyDataSetChanged();
            }
        });
        dialogo1.show();
    }

    public void actualizarVista(){
        clAdaptador.notifyDataSetChanged();
    }

    public void add(){
        Intent intent= new Intent(this, AddContact.class);
        startActivity(intent);
    }

    public void añadir(View v){
        Intent intent= new Intent(this, AddContact.class);
        startActivity(intent);
    }

    public void ordenarAgenView(View v){
        agenda.ordenarAgenda();
        clAdaptador.notifyDataSetChanged();
    }

    public void ordenarAgenViewInver(View v){
        agenda.ordenarAgendaInverso();
        clAdaptador.notifyDataSetChanged();
    }

}
