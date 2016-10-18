package ale.movcalendar.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import ale.movcalendar.Class.RecommendedAdapter;
import ale.movcalendar.Class.RecommendedItem;
import ale.movcalendar.R;

public class RecommendedActivity extends AppCompatActivity {

    public static final String[] nombre = new String[] {
            "Rapido y Furioso 4",
            "Piratas del Caribe",
            "Transformers",
            "Thor",
            "Avatar",
            "Jurassic Park",
            "Rocky",
            "Volver al Futuro 3"};

    public static final String[] genero = new String[] {
            "Accion",
            "Ciencia ficcion",
            "Ciencia ficcion",
            "Ciencia ficcion",
            "Aventura",
            "Ciencia ficcion",
            "Accion",
            "Ciencia ficcion"};

    public static final String[] año = new String[] {
            "2009",
            "2011",
            "2014",
            "2011",
            "2010",
            "1997",
            "1983",
            "1990"};

    public static final Integer[] imagenes = {
            R.drawable.rapidoyfurioso4,
            R.drawable.piratasdelcaribe,
            R.drawable.transformer,
            R.drawable.thor,
            R.drawable.avatar,
            R.drawable.jurassicpark,
            R.drawable.rocky3,
            R.drawable.volveralfuturo3};

    ListView listView;
    List<RecommendedItem> recommendedItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        setTitle("Recomendado");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recommendedItemList = new ArrayList<>();
        for (int i = 0; i < nombre.length; i++) {
            RecommendedItem item = new RecommendedItem(imagenes[i], nombre[i], genero[i], año[i]);
            recommendedItemList.add(item);
        }

        listView = (ListView) findViewById(R.id.listview_recomendaciones);

        RecommendedAdapter adapter = new RecommendedAdapter(this,
                R.layout.activity_recommended_item, recommendedItemList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(RecommendedActivity.this, RecommendedDescriptionActivity.class);
                i.putExtra("imagen", imagenes[position].toString());
                i.putExtra("posicion", position);
                startActivity(i);
            }
        });

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
                Intent settings = new Intent(RecommendedActivity.this, PreferencesActivity.class);
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
                Intent cerrarsesion = new Intent(RecommendedActivity.this, LoginActivity.class);
                startActivity(cerrarsesion);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
