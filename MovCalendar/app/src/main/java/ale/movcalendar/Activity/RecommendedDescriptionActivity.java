package ale.movcalendar.Activity;

import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ale.movcalendar.R;

public class RecommendedDescriptionActivity extends AppCompatActivity {

    String [] descripciones;
    TextView descripcion;
    ImageView img;
    String recurso = "R.drawable.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_description);
        setTitle("Descripción");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        descripcion = (TextView) findViewById(R.id.textview_descripcion);
        img = (ImageView) findViewById(R.id.imagen_descripcion);

        Bundle datos = this.getIntent().getExtras();
        int posicion = datos.getInt("posicion");
        String imagen = datos.getString("imagen");

        descripciones = getResources().getStringArray(R.array.descripicones);

        descripcion.setText(descripciones[posicion]);
        int res_imagen = RecommendedDescriptionActivity.this.getResources().getIdentifier("drawable/" + imagen, null, RecommendedDescriptionActivity.this.getPackageName());
        img.setImageResource(res_imagen);

    }
}
