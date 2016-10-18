package ale.movcalendar.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ale.movcalendar.R;
import ale.movcalendar.DB.Usuarios;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button borrar, insertar, consultar, main2;
    EditText editText, editText2;
    TextView texto;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertar = (Button)findViewById(R.id.insertar);
        borrar = (Button)findViewById(R.id.borrar);
        consultar = (Button)findViewById(R.id.consultar);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        texto = (TextView)findViewById(R.id.textView);
        main2 = (Button)findViewById(R.id.Buttonn);

        insertar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        consultar.setOnClickListener(this);
        texto.setOnClickListener(this);
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivity(i);
            }
        });



        Usuarios users = new Usuarios (this, "DBUsuarios", null, 1);

        db = users.getWritableDatabase();

        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int id = i;
                String usuario = "Usuario" + i;
                String contraseña = "Usuario" + i + 1;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO usuarios (id, usuario, contraseña) VALUES (" + id + ", '" + usuario + "' , '" +  contraseña + "')");
            }

            //Cerramos la base de datos
            //db.close();
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.insertar:
                    Intent i = new Intent(MainActivity.this, RecommendedActivity.class);
                    startActivity(i);
                break;
            case R.id.borrar:
                    db.execSQL("DELETE FROM usuarios WHERE usuario ='" + editText + "'");
                break;
            case R.id.consultar:
                String[] args = new String[] {editText.getText().toString()};
                Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE usuario=?", args);
                if(c.moveToFirst()){
                    texto.setText("");

                    do {
                        Integer id = c.getInt(0);
                        String usuario = c.getString(1);
                        String contraseña = c.getString(2);
                        texto.append("id: " + id + " , nombre: " + usuario + " , contraseña: " + contraseña);
                    } while(c.moveToNext());
                }
                break;
            default:
                break;
        }
    }
}
