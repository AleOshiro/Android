package ale.movcalendar.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ale.movcalendar.R;
import ale.movcalendar.DB.Usuarios;

public class RegistroActivity extends AppCompatActivity {

    EditText edittextNuevoUsuario, edittextNuevaContraseña, edittextNuevoMail;
    Button buttonRegistrar;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.toolbar_registro);
        setContentView(R.layout.activity_registro);

        Usuarios newUsers = new Usuarios(this, "DBmovCalendar", null, 1);
        db = newUsers.getWritableDatabase();

        edittextNuevoUsuario = (EditText)findViewById(R.id.NuevoUsuario);
        edittextNuevaContraseña = (EditText)findViewById(R.id.NuevoContraseña);
        edittextNuevoMail = (EditText)findViewById(R.id.NuevoMail);
        buttonRegistrar = (Button)findViewById(R.id.Registrar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edittextNuevoUsuario.getText().toString().isEmpty()){
                    if(!edittextNuevaContraseña.getText().toString().isEmpty()){
                        String[] args = new String[] {edittextNuevoUsuario.getText().toString()};
                        Cursor c = db.rawQuery("SELECT * FROM usuarios WHERE usuario=?", args);
                        if(c.moveToFirst()){
                            Toast.makeText(RegistroActivity.this, "Usuario YA EXISTENTE!\nSeleccione otro usuario" , Toast.LENGTH_LONG).show();
                            edittextNuevoUsuario.setText("");
                            edittextNuevaContraseña.setText("");
                        } else {
                            db.execSQL("INSERT INTO usuarios (usuario, contraseña) VALUES ('" + edittextNuevoUsuario.getText() + "', '" + edittextNuevaContraseña.getText() + "')");
                            Toast.makeText(RegistroActivity.this, "Usuario creado con exito!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                            i.putExtra("username", edittextNuevoUsuario.getText().toString());
                            startActivity(i);
                        }
                    }else{
                        Toast.makeText(RegistroActivity.this, "Debe ingresar una contraseña para registrarse", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegistroActivity.this, "Debe ingresar un usuario para registrarse", Toast.LENGTH_SHORT).show();
                    edittextNuevaContraseña.setText("");

                }
            }
        });

    }
}
