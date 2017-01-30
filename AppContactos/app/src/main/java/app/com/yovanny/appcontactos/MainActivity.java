package app.com.yovanny.appcontactos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dao.ContactoDao;
import model.Contacto;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ContactoDao contactoDao = new ContactoDao();
    ListView lvContactos;
    ListAdapter adapter;
    FloatingActionButton btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContactos = (ListView)findViewById(R.id.lvContactos);
        btnAgregar = (FloatingActionButton) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);

        Listar();
    }

    private void Listar(){
        try {
            List<Contacto> lista = contactoDao.Listar(this);
            adapter =  new ArrayAdapter<Contacto>(this,android.R.layout.simple_list_item_1,lista);
            lvContactos.setAdapter(adapter);
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, AgregarActivity.class);
        startActivity(intent);
    }
}
