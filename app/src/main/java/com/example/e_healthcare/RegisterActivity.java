package com.example.e_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edusername , edPassword , edConfirmPassword,edEmail;
    Button btn;
    TextView tv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       edusername = findViewById(R.id.editTextAppName);
        edEmail = findViewById(R.id.editTextLTBaddress);
        edPassword = findViewById(R.id.editTextLTBPinCode);
        edConfirmPassword = findViewById(R.id.editTextLTBContactNumber);
        btn = findViewById(R.id.buttonBack);
        tv = findViewById(R.id.textView3ExistingUser);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmpassword = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"Healthcare",null,1);
                
                if (username.length()==0 || email.length()==0||password.length()==0||confirmpassword.length()==0){
                    Toast.makeText(RegisterActivity.this, "Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirmpassword)!=0){
                        Toast.makeText(RegisterActivity.this, "password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (isVaild(password)){
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Password must contain at least 8 character having letter , Digit and Symbols", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
    public static boolean isVaild(String passdwordhere){
        int f1=0 , f2=0 , f3=0;
        if (passdwordhere.length()<8){
            return false;
        }
        else {
            for (int i =0 ; i<passdwordhere.length();i++){
                if (Character.isLetter(passdwordhere.charAt(i))){
                    f1 = 1;
                }
            }
            for (int j =0 ; j<passdwordhere.length();j++){
                if (Character.isDigit(passdwordhere.charAt(j))){
                    f2 = 1;
                }
            }
            for (int k =0 ; k<passdwordhere.length();k++){
                char c = passdwordhere.charAt(k);
                if (c >= 33 && c <= 46 || c == 64){
                    f3 = 1;
                }
            }
            if (f1==1 && f2==1 &&f3 ==1){ return true;}
            return  false;
        }

    }
}