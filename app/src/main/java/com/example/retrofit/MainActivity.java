package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addEmp;
    Button addEmpPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEmp = findViewById(R.id.addEmp);
        addEmpPay=findViewById(R.id.addEmpPay);
        addEmp.setOnClickListener(this);
        addEmpPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addEmp:
                Intent intent=new Intent(this.getApplicationContext(), EmpDetailsActivity.class);
                this.startActivity(intent);
                break;
            case R.id.addEmpPay:
                intent=new Intent(this.getApplicationContext(), EmpPayActivity.class);
                this.startActivity(intent);
                break;


        }
    }
}