package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Contacto;

/**
 * Created by Yovanny on 29/01/2017.
 */

public class ContactoDao {

    public List<Contacto> Listar(Context context){
        List<Contacto> lista = new ArrayList<>();
        BDContacto bdContacto = new BDContacto(context,"BDCONTACTOS",null,1);
        SQLiteDatabase db = bdContacto.getWritableDatabase();
        String query="SELECT * FROM CONTACTOS";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Contacto contacto = new Contacto();
                contacto.setId(cursor.getInt(0));
                contacto.setNombre(cursor.getString(1));
                contacto.setApellido(cursor.getString(2));
                contacto.setFechaNac(cursor.getString(3));
                contacto.setTipocontacto(cursor.getString(4));
                contacto.setFijo(cursor.getString(5));
                contacto.setMovil(cursor.getString(6));
                contacto.setEmail(cursor.getString(7));
                contacto.setDireccion(cursor.getString(8));
                contacto.setURL(cursor.getString(9));
                contacto.setType(cursor.getString(10));
                lista.add(contacto);
            }while (cursor.moveToNext());
        }
        db.close();
        return lista;
    }

    public boolean Add(Contacto contacto,Context context){

        boolean esValido = false;
        BDContacto bdContacto = new BDContacto(context,"BDCONTACTOS",null,1);
        SQLiteDatabase db= bdContacto.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NOMBRE",contacto.getNombre());
        values.put("APELLIDO",contacto.getApellido());
        values.put("FECHA_NACIMIENTO",contacto.getFechaNac());
        values.put("TIPO_CONTACTO",contacto.getTipocontacto());
        values.put("FIJO",contacto.getFijo());
        values.put("MOVIL",contacto.getMovil());
        values.put("EMAIL",contacto.getEmail());
        values.put("DIRECCION",contacto.getDireccion());
        values.put("URL",contacto.getURL());
        values.put("TYPE",contacto.getType());

        esValido = db.insert("CONTACTOS", null, values) > 0;

        db.close();
        return esValido;
    }
}
