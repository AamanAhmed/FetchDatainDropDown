package com.example.fetchdataindropdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Spinner dropdown;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ed1 = (EditText) findViewById(R.id.proname);
        ed2 = (EditText) findViewById(R.id.proprice);
        dropdown = (Spinner) findViewById(R.id.procat);
        btn = (Button) findViewById(R.id.probtn);
        Db d = new Db(this);
        List<String> record = d.fetchdata();
        ArrayAdapter<String> myadapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,record);
        myadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dropdown.setAdapter(myadapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String proname = ed1.getText().toString();
                int proprice = Integer.parseInt(ed2.getText().toString());
                int categoryid = (int) dropdown.getSelectedItemId();
                
                d.productinsert(proname,proprice,categoryid);
                Toast.makeText(ProductActivity.this, "Product Saved", Toast.LENGTH_SHORT).show();
                
            }
        });
    }
}