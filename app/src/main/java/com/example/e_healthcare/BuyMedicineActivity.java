package com.example.e_healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Uprise-D3  1000IU Capsule","" , "" ,"","50"},
            {"HealthVit Chromium Picolinates 200mg Capcule","" , "" ,"","305"},
            {"Vitamin B Complex Capsule","" , "" ,"","448"},
            {"INLife Vitamin E Wheat Germ Oil Capsule","" , "" ,"","539"},
            {"Dolo 650 Tablet","" , "" ,"","30"},
    };
    // 4:02:49 add Mediciene and details
    private  String[] package_details = {
      "Building and Keeping the bones and teeth Strong\n"+
      "Reducing Fatigue/stress and muscular pains\n"+
      "Boosting immunity and increasing resistance against infection",
       "chromium is an essential trace mineral that plays an important role in helping insulin regular blood glucose .",
       "Provides relief from vitamin B deficiencies\n"+
               "Helps in Formation of red blood cells\n"+
               "Maintains healthy nervous System",
            "It promotes health as well as skin benefit\n"+
                    "It helps reduce skin blemish and pigmentation.\n"+
                   "It act as safegaurad the skin from the harsh UVA and UVB sun rays .",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messenger responsible for fever and pain"
    };
    HashMap<String,String> item;
    ArrayList list ;
    SimpleAdapter sa;
    Button btnBack, btnGoTOCart;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoTOCart = findViewById(R.id.buttonBMGotoCart);
        listView = findViewById(R.id.listViewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        btnGoTOCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost :"+packages[i][4]+"/-");
           list.add(item);
        }
//        sa = new SimpleAdapter(this,list,
//                R.layout.multi_lines,
//                new String[]{"line1","line2","line3","line4","line5"},
//                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
//       lst.setAdapter(sa);
       sa = new SimpleAdapter(this,list,
               R.layout.multi_lines,
               new String[] {"line1","line2","line3","line4","line5"},
               new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
               it.putExtra("text1",packages[i][0]);
               it.putExtra("text2",package_details[i]);
               it.putExtra("text3",packages[i][4]);
               startActivity(it);
           }
       });
    }
}