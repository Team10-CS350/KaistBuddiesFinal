package com.example.buddyapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityAuthenticate extends AppCompatActivity {

    private EditText enteredCode;
    private Button authenticate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        enteredCode = findViewById(R.id.enterCode);
        authenticate = findViewById(R.id.authenticate);

        String code = enteredCode.getText().toString();
        String userEmail = getIntent().getStringExtra("USERMAIL");

        authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                enteredCode = findViewById(R.id.enterCode);
                authenticate = findViewById(R.id.authenticate);

                String code = enteredCode.getText().toString();
                String userEmail = getIntent().getStringExtra("USERMAIL");

                if (DemoServer.checkIfCodeCorrect(userEmail, code)) {


                    toastThis ("you have been regisered");

                    SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("userStatus", "loggedOut");
                    editor.apply();

                    Intent i = new Intent(ActivityAuthenticate.this, ActivityLogIn.class);
                    startActivity(i);
                } else toastThis("the code was incorrect");
            }
        });

    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
