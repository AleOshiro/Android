package ale.movcalendar.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import ale.movcalendar.Class.ListViewAdapter;
import ale.movcalendar.Class.SeriesClass;
import ale.movcalendar.R;
import ale.movcalendar.DB.SeriesDB;

public class HomeActivity extends AppCompatActivity {

    public ListView list;
    SQLiteDatabase db;

    private SeriesClass[] datos =
            new SeriesClass[]{
                new SeriesClass ("Game of thrones", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                new SeriesClass("House of cards", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                new SeriesClass("The walking dead", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                new SeriesClass("The last ship", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                new SeriesClass("Gotham", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                new SeriesClass("Arrow", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                    new SeriesClass("Gotham", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                    new SeriesClass("Arrow", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                    new SeriesClass("Gotham", "Ciencia ficcion", "1º Temporada", "Descripcion"),
                    new SeriesClass("Arrow", "Ciencia ficcion", "1º Temporada", "Descripcion"),
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SeriesDB series = new SeriesDB(this, "DBmovCalendar", null, 1);
        db = series.getWritableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM series", null);

        ListViewAdapter adaptador = new ListViewAdapter(this, datos);

        list = (ListView)findViewById(R.id.listView);

        list.setAdapter(adaptador);

    }


}
