package edu.tectii.eva2_9_sqlite_2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    SQLiteDatabase sqlMiBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        sqlMiBD = openOrCreateDatabase("mi_base_datos", MODE_PRIVATE,null);
        try{
            //crear tabla
            sqlMiBD.execSQL("create table datos(" +
            "datosID integer primary key autoincrement, " +
            "nombre text," +
            "apellido text);");
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        ContentValues cvDAtos = new ContentValues();
        cvDAtos.put("nombre","Maritza Guadalupe");
        cvDAtos.put("apellido","Ordoñez Lopez");
        sqlMiBD.insert("datos",null,cvDAtos);
        cvDAtos.clear();

        cvDAtos.put("nombre","Fátima");
        cvDAtos.put("apellido","Ordoñez Lopez");
        sqlMiBD.insert("datos",null,cvDAtos);
        cvDAtos.clear();

        cvDAtos.put("nombre","Fulanito");
        cvDAtos.put("apellido","Perez");
        long iClave;
       iClave = sqlMiBD.insert("datos",null,cvDAtos);
        cvDAtos.clear();
        Toast.makeText(this,"Última llave: " + iClave, Toast.LENGTH_LONG).show();
        //Actualizar un campo
        cvDAtos.put("nombre", "XXXXX");
        cvDAtos.put("apellido", "YYYYY");
        String[] sArgs= {"Fulanito"};
        sqlMiBD.update("datos",cvDAtos,"datosID = ?",sArgs);
        sqlMiBD.delete("datos","nombre like ?",sArgs);

    }
}
