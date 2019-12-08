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
            Intent intent = new Intent (ActivityLogIn.this, MainActivity.class);
            startActivity(intent);
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

                    User currentUser = DemoServer.getUserFromEmail(email.getText().toString());
                    if (currentUser.passwordMatches(password.getText().toString())) {

                        SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userStatus", "loggedIn");
                        editor.apply();

                        Intent i = new Intent(ActivityLogIn.this, MainActivity.class);
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
