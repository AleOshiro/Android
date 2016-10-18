package ale.movcalendar.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ale.movcalendar.DB.MoviesDB;
import ale.movcalendar.R;

public class AddMovieActivity extends AppCompatActivity {

    EditText nombre, genero, temporada, descripcion, imagen;
    Button agregar;
    String sNombre, sGenero, sTemporada, sDescripcion, sImagen;
    MoviesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Agregar serie");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nombre = (EditText)findViewById(R.id.editText_peliNombre);
        genero = (EditText)findViewById(R.id.editText_peliGenero);
        temporada = (EditText)findViewById(R.id.editText_peliTemporada);
        descripcion = (EditText)findViewById(R.id.editText_peliDescripcion);
        imagen = (EditText)findViewById(R.id.editText_peliImagen);
        agregar = (Button)findViewById(R.id.button_peliAgregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarDatos();
                Intent i = new Intent(AddMovieActivity.this, MovieActivity.class);
                startActivity(i);
            }
        });

    }

    public void agregarDatos(){
        db = new MoviesDB(this);
        sNombre = nombre.getText().toString();
        sGenero = genero.getText().toString();
        sTemporada = temporada.getText().toString();
        sDescripcion = descripcion.getText().toString();
        sImagen = imagen.getText().toString();
        db.agregarPeli(sNombre, sGenero, sTemporada, sDescripcion, sImagen);
        // db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settings = new Intent(AddMovieActivity.this, PreferencesActivity.class);
                startActivity(settings);
                return true;
            case R.id.action_misdatos:
                SharedPreferences sharedPreferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("user", "");
                String pass = sharedPreferences.getString("pass", "");
                Toast.makeText(this, "Usuario: " + name + "\n" + "Pass: " + pass , Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_cerrarsesion:
                sharedPreferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user", "");
                editor.putString("pass", "");
                editor.apply();
                Intent cerrarsesion = new Intent(AddMovieActivity.this, LoginActivity.class);
                startActivity(cerrarsesion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
