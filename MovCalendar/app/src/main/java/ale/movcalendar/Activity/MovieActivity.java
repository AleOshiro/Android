package ale.movcalendar.Activity;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ale.movcalendar.DB.MoviesDB;
import ale.movcalendar.R;

public class MovieActivity extends AppCompatActivity {

    ListView lista;
    MoviesDB db;
    List<String> item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lista = (ListView)findViewById(R.id.listview_peliLista);

        mostrarDatos();
    }

    private void mostrarDatos(){
        db = new MoviesDB(this);
        Cursor c = db.getPelis();
        item = new ArrayList<String>();

        if(c.moveToFirst()){
            do{
                String nombre = c.getString(1);
                String genero = c.getString(2);
                String temporada = c.getString(3);
                item.add(nombre + " " + genero + temporada);
            } while(c.moveToNext());
        }

        // Adaptador del tipo ArrayAdapter
        // Los datos que esten almacenados en la lista item, se van a almacenar en el
        // objecto adaptador del tipo ArrayAdapter
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, item);

        lista.setAdapter(adaptador);
    }

}
