package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercise2.database.DBContoroller;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class UpdateData extends AppCompatActivity {
    private TextInputEditText tNama,tTelpon;
    private Button updateBtn;
    String id,nm,tlp;
    DBContoroller contoroller = new DBContoroller(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        
        tNama = (TextInputEditText)findViewById(R.id.TietNama);
        tTelpon = (TextInputEditText)findViewById(R.id.TietTelpon);
        updateBtn = (Button)findViewById(R.id.btnUpdate);
        
        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nm");
        tlp = getIntent().getStringExtra("tlp");
        
        tNama.setText(nm);
        tTelpon.setText(tlp);
        
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("") || tTelpon.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Data belum complete!", Toast.LENGTH_SHORT).show();
                } else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    HashMap<String, String> qvalues = new HashMap<>();
                    qvalues.put("id", id);
                    qvalues.put("nama", nm);
                    qvalues.put("telpon", tlp);

                    contoroller.updateData(qvalues);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent intent = new Intent(UpdateData.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}