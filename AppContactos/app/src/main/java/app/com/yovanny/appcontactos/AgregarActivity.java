package app.com.yovanny.appcontactos;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ContactoDao;
import model.Contacto;
import util.DbBitmapUtility;


public class AgregarActivity extends AppCompatActivity implements View.OnClickListener,AlertDialog.OnClickListener{

    ContactoDao contactoDao = new ContactoDao();

    EditText txtNombre, txtApellido, txtFechaNac, txtCelular, txtFijo, txtEmail, txtDireccion;
    Spinner spTipoContacto;
    Button btnAgregar;

    String[] tipoContacto;
    ArrayAdapter adapter;
    String dir , type;

    ImageView ivFoto;
    CharSequence[] Opciones = {"Tomar foto","Elegir galeria","Cancelar"};
    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY =   APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "";

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtFechaNac = (EditText)findViewById(R.id.txtFechaNac);
        txtCelular = (EditText)findViewById(R.id.txtCelular);
        txtFijo = (EditText)findViewById(R.id.txtFijo);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        spTipoContacto = (Spinner)findViewById(R.id.spTipoContacto);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        ivFoto = (ImageView)findViewById(R.id.ivFoto);

        ivFoto.setOnClickListener(this);

        btnAgregar.setOnClickListener(this);

        tipoContacto = new String[]{"Familia","Amigos","Trabajo"};
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tipoContacto);
        spTipoContacto.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgregar:
                Agregar();
                break;
            case  R.id.ivFoto:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Elija un aopcion");
                builder.setItems(Opciones,this);
                AlertDialog alert = builder.create();
                alert.show();
                break;

        }

    }

    private void Agregar(){

        boolean esValido = false;

        Contacto contacto = new Contacto();
        contacto.setNombre(txtNombre.getText().toString());
        contacto.setApellido(txtApellido.getText().toString());
        contacto.setFechaNac(txtFechaNac.getText().toString());
        contacto.setTipocontacto(spTipoContacto.getSelectedItem().toString());
        contacto.setFijo(txtFijo.getText().toString());
        contacto.setMovil(txtCelular.getText().toString());
        contacto.setEmail(txtEmail.getText().toString());
        contacto.setDireccion(txtDireccion.getText().toString());
        contacto.setType(type);
        contacto.setURL(dir);

        try {
            esValido = contactoDao.Add(contacto,this);

            if(esValido){
                Toast.makeText(this,"Contacto agregado correctamente",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"No se pudo agregar el contacto",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if(resultCode == RESULT_OK){
                     dir = Environment.getExternalStorageDirectory() +
                            File.separator  +
                            MEDIA_DIRECTORY +
                            File.separator +
                            TEMPORAL_PICTURE_NAME;
                    type = "DIR";
                    decodeBitmap(dir);
                }
                break;
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    Uri Path = data.getData();
                    dir = Path.getPath();
                    type = "URI";
                    ivFoto.setImageURI(Path);
                }
                break;
        }
    }

    private void decodeBitmap(String dir) {
       Bitmap bitmap = BitmapFactory.decodeFile(dir);
        ivFoto.setImageBitmap(bitmap);

    }


    @Override
    public void onClick(DialogInterface dialog, int i) {
        if(Opciones[i] == "Tomar foto"){
            AbreCamara();
        }else if(Opciones[i] == "Elegir galeria"){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Seleccione Imagen"),SELECT_PICTURE);
        }else if(Opciones[i] == "Cancelar"){
            dialog.dismiss();
        }
    }

    private void AbreCamara() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();
        TEMPORAL_PICTURE_NAME = getCode()+".jpg";
        String Path = Environment.getExternalStorageDirectory() +
                file.separator +
                MEDIA_DIRECTORY +
                file.separator +
                TEMPORAL_PICTURE_NAME ;

        File nuevoFile = new File(Path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevoFile));

        startActivityForResult(intent,PHOTO_CODE);
    }

    @SuppressLint("SimpleDateFormat")
    private String getCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "pic_" + date;
        return photoCode;
    }
}
