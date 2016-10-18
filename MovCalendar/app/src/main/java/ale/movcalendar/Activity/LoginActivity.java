package ale.movcalendar.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import ale.movcalendar.R;
import ale.movcalendar.DB.Usuarios;

public class LoginActivity extends Activity {

    private EditText edittextUsuario, edittextContraseña;
    private Button buttonLogin, buttonRegistro, b;
    private CheckBox checkBox;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.toolbar_login);
        setContentView(R.layout.activity_login);

        edittextUsuario = (EditText)findViewById(R.id.usernameLogin);
        edittextContraseña = (EditText)findViewById(R.id.passwordLogin);
        buttonLogin = (Button)findViewById(R.id.loginLogin);
        buttonRegistro = (Button)findViewById(R.id.registerLogin);
        checkBox = (CheckBox)findViewById(R.id.showLogin);

        Usuarios checkUser = new Usuarios(this, "DBmovCalendar", null, 1);
        db = checkUser.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null ){
            edittextUsuario.setText(bundle.getString("username"));
        }

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!edittextUsuario.getText().toString().isEmpty()){
                    if(!edittextContraseña.getText().toString().isEmpty()){
                        String[] args = new String[] {edittextUsuario.getText().toString(), edittextContraseña.getText().toString()};
                        Cursor c = db.rawQuery("SELECT usuario, contraseña FROM usuarios WHERE usuario=? AND contraseña=?", args);
                        if(c.moveToFirst()){
                            String usuario = c.getString(0);
                            String contraseña = c.getString(1);
                            if(edittextUsuario.getText().toString().equals(usuario) && edittextContraseña.getText().toString().equals(contraseña)){
                                Toast.makeText(LoginActivity.this, "Bienvenido" + usuario, Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("user", edittextUsuario.getText().toString());
                                editor.putString("pass", edittextContraseña.getText().toString());
                                editor.apply();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(LoginActivity.this, "Usuario y contraseña invalidos", Toast.LENGTH_SHORT).show();
                                edittextUsuario.setText("");
                                edittextContraseña.setText("");
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                            edittextUsuario.setText("");
                            edittextContraseña.setText("");
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Ingrese una contraseña para ingresar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Ingrese un usuario para ingresar", Toast.LENGTH_SHORT).show();
                    edittextContraseña.setText("");
                }
            }
        });

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(main);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    edittextContraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    edittextContraseña.setTransformationMethod((HideReturnsTransformationMethod.getInstance()));
                }
            }
        });
    }
}


/*
    Verificacion de los datos del usuario (Mis datos)

    b = (Button) findViewById(R.id.b);

    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("user", "");
            String pass = sharedPreferences.getString("pass", "");
            Toast.makeText(LoginActivity.this, "Usuario: " + name + "/n Pass: " + pass , Toast.LENGTH_SHORT).show();
        }
    });
*/