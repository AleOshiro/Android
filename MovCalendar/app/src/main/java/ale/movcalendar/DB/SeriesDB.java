package ale.movcalendar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SeriesDB extends SQLiteOpenHelper{

    String sqlSeries = "CREATE TABLE series (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre STRING, genero STRING, temporada STRING, descripcion STRING, imagen STRING)";

    public SeriesDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlSeries);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS series");
    }
}
