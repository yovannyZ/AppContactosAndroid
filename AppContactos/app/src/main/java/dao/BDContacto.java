package dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yovanny on 29/01/2017.
 */

public class BDContacto extends SQLiteOpenHelper {

    String query = "";

    public BDContacto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BDContacto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, String query) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        query = "CREATE TABLE CONTACTOS( " +
                "ID INTEGER PRIMARY KEY,  " +
                "NOMBRE TEXT,  " +
                "APELLIDO TEXT,  " +
                "FECHA_NACIMIENTO TEXT,  " +
                "TIPO_CONTACTO TEXT,  " +
                "FIJO TEXT,  " +
                "MOVIL TEXT,  " +
                "EMAIL TEXT,  " +
                "DIRECCION TEXT, " +
                "URL TEXT, " +
                "TYPE TEXT )";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE  CONTACTO";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }
}
