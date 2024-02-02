package com.example.e_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edAddress, edContact , edFullName, edPincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        edFullName = findViewById(R.id.editTextLTBFullName);
        edAddress = findViewById(R.id.editTextLTBaddress);
        edPincode = findViewById(R.id.editTextLTBPinCode);
        edContact = findViewById(R.id.editTextLTBContactNumber);
        btnBooking = findViewById(R.id.buttonLTBBooking);

        Intent intent = getIntent();
        String [] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edFullName.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),Integer.parseInt(edPincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Your booking is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });
    }
}