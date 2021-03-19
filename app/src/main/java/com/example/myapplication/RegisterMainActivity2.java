package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Html.fromHtml;

public class RegisterMainActivity2 extends AppCompatActivity {

    EditText TxUsername, TxPassword, TxConPassword;
    Button BtnRegister;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main2);

        dbHelper = new DBHelper(this);

        TxUsername = (EditText)findViewById(R.id.txUserName);
        TxPassword =  (EditText)findViewById(R.id.txPaswordReg);
        TxConPassword = (EditText)findViewById(R.id.txConPassword);
        BtnRegister = (Button)findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView)findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to "+
                "</font><font color='#3b5998'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterMainActivity2.this, Login_behasilActivity2.class));
            }
        });
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = TxUsername.getText().toString().trim();
                String Password = TxPassword.getText().toString().trim();
                String conPassword = TxConPassword.getText().toString().trim();

                ContentValues values= new ContentValues();

                if (!Password.equals(conPassword)){
                    Toast.makeText(RegisterMainActivity2.this, "password not macth ", Toast.LENGTH_SHORT).show();
                    }else if (Password.equals("") || Username.equals("")){
                    Toast.makeText(RegisterMainActivity2.this, "Username or Password ", Toast.LENGTH_SHORT).show();
                }else {
                    values. put(DBHelper.rov_username, Username);
                    values. put(DBHelper.rov_password, Password);
                    dbHelper.insertData(values);

                    Toast.makeText(RegisterMainActivity2.this, "Register successfull", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public  static Spanned fromHtml (String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
