package com.example.buddyapp4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegistration extends AppCompatActivity {
    private EditText name, email, password, repeatPassword;
    private CheckBox buddyCheckBox;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        name = (EditText) findViewById(R.id.rNameInput);
        email = (EditText) findViewById(R.id.rEmailInput);
        password = (EditText) findViewById(R.id.rPasswordInput);
        repeatPassword = (EditText) findViewById(R.id.rRepeatPasswordInput);
        buddyCheckBox = (CheckBox) findViewById(R.id.buddyCheckBox);
        submit = (Button) findViewById(R.id.submitRegistration);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String password1 = password.getText().toString();
                String password2 = repeatPassword.getText().toString();
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String userType = buddyCheckBox.isChecked()? "buddy" : "normal user";
                if (password1.length() * name1.length()
                        * email1.length() == 0) {
                    toastThis ("please fill every field");
                }
                else if (password1.equals(password2)) {
                    if (email1.length() <= 12 || !email1.endsWith("@kaist.ac.kr") ) {
                        toastThis ("Only Kaist email permitted");
                    } else if (DemoServer.checkIfEmailExists(email1)) {
                        toastThis ("email already exists");
                    } else {
                        User newUser = new User(name.getText().toString(), email.getText().toString(),
                                password1, buddyCheckBox.isChecked());

                        DemoServer.sendAuthentication(newUser);
                        DemoServer.addNewUser(newUser);


                        Intent startAuthentication = new Intent(ActivityRegistration.this, ActivityAuthenticate.class);
                        startAuthentication.putExtra("USERMAIL", newUser.getEmail());
                        startActivity(startAuthentication);
                    }

                } else toastThis("your passwords don't match");
            }
        });

    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
