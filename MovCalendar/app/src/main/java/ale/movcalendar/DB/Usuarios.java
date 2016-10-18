package ale.movcalendar.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Usuarios extends SQLiteOpenHelper {

    String sqlUsuarios = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario STRING, contraseña STRING, mail STRING)";

    public Usuarios(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
