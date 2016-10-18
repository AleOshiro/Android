package ale.movcalendar.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDB extends SQLiteOpenHelper {

    public static final String peli_id = "pId";
    public static final String peli_nombre = "pNombre";
    public static final String peli_genero = "pGenero";
    public static final String peli_temporada = "pTemporada";
    public static final String peli_descripcion = "pDecripcion";
    public static final String peli_imagen= "pImagen";

    private static final String database = "DB";
    private static final String peli_table = "peliculas";

    public MoviesDB(Context context) {
        super(context, database, null, 1);
    }

    String sqlPelis = "CREATE TABLE " + peli_table + " (" +
            peli_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            peli_nombre + " STRING, " +
            peli_genero + " STRING, " +
            peli_temporada + " STRING, " +
            peli_descripcion + " STRING, " +
            peli_imagen + " STRING)";

    // Método encargado de inicializar la base de datos.
    // Se ejecuta siempre y cuando se crea la clase
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlPelis);
    }

    // Método encargado de actualizar la base de datos si la version de la BD es distinta.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + peli_table);
    }

    // Método encargado para cerrar la conexion de esta clase.
    public void close(){
        this.close();
    }

    // Método encargado de insertar los datos a la tabla peliculas
    public void agregarPeli(String nombre, String genero, String temporada, String descripcion, String imagen){
        ContentValues valores = new ContentValues();
        valores.put(peli_nombre, nombre);
        valores.put(peli_genero, genero);
        valores.put(peli_temporada, temporada);
        valores.put(peli_descripcion, descripcion);
        valores.put(peli_imagen, imagen);
        this.getWritableDatabase().insert(peli_table, null, valores);
    }

    // Método encargado de devolver todos los datos de la tabla peliculas
    public Cursor getPelis(){
        String columnas[] = {peli_nombre, peli_genero, peli_temporada, peli_descripcion, peli_imagen};
        Cursor c = this.getReadableDatabase().query(peli_table, columnas, null, null, null, null, null);
        return c;
    }
}
