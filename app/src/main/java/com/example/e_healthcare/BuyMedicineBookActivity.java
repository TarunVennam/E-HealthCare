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

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edAddress, edContact , edFullName, edPincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        edFullName = findViewById(R.id.editTextBMBFullName);
        edAddress = findViewById(R.id.editTextBMBaddress);
        edPincode = findViewById(R.id.editTextBMBPinCode);
        edContact = findViewById(R.id.editTextBMBContactNumber);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String [] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
//        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edFullName.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),Integer.parseInt(edPincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Your booking is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });


    }
}