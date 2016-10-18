package ale.movcalendar.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import java.util.Timer;
import java.util.TimerTask;

import ale.movcalendar.R;

public class SplashActivity extends AppCompatActivity {

    private static final long splashDelay = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cambia la orientacion de la activity por la orientacion deseada
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Saca el titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Le indico de que activity a cual activity tiene que ir.
                Intent i = new Intent().setClass(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                // Cierro la activity para que no pueda se accesedida apretando el boton de atras.
                finish();
            }
        };

        // Genero un tiempo en la que se tiene que ejecutar la tarea para cambiar de activity.
        Timer tiempo = new Timer();
        tiempo.schedule(task, splashDelay);
    }
}
