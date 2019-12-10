package com.example.buddyapp4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ActivityLogIn extends AppCompatActivity {
    private TextView registerLink;
    private Button logInButton;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerLink = (TextView) findViewById(R.id.textRegister);
        logInButton = (Button) findViewById(R.id.button);

        SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
        String currentStatus = preferences.getString("userStatus", "");
        if (currentStatus.equals("loggedIn")) {
            Intent i = new Intent(ActivityLogIn.this, MainActivity.class);
            String userEmail = preferences.getString("loggedInUserEMail", "");
            i.putExtra("CURRENTUSER", (Serializable) DemoServer.getUserFromEmail(userEmail));
            startActivity(i);
        }

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent i = new Intent(ActivityLogIn.this, ActivityRegistration.class);
                startActivity(i);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = (EditText) findViewById(R.id.enterEmail);
                password = (EditText) findViewById(R.id.enterPassword);

                if (DemoServer.checkIfEmailExists(email.getText().toString())) {

                    User userToLogIn = DemoServer.getUserFromEmail(email.getText().toString());
                    if (userToLogIn.passwordMatches(password.getText().toString())) {

                        SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userStatus", "loggedIn");
                        editor.putString("loggedInUserEMail", userToLogIn.getEmail());
                        editor.apply();

                        Intent i = new Intent(ActivityLogIn.this, MainActivity.class);
                        i.putExtra("CURRENTUSER", (Serializable) userToLogIn);
                        startActivity(i);
                    } else toastThis ("password does not match");
                } else toastThis ("user is not registered");
            }
        });

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess (android.os.Process.myPid());
        System.exit(0);
        return;
    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
