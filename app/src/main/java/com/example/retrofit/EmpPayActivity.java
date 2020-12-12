package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofit.api_interface.JsonHolder;
import com.example.retrofit.models.EmployeePay;
import com.example.retrofit.models.EmployeesDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpPayActivity extends AppCompatActivity {

    TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_pay);
        textResult = findViewById(R.id.text_result);

        createEmpPay();
    }
    public void getEmployeePay()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:4060/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder JsonHolder=retrofit.create(JsonHolder.class);
        Call<List<EmployeePay>> call=JsonHolder.getEmpPay();

        call.enqueue(new Callback<List<EmployeePay>>() {
            @Override
            public void onResponse(Call<List<EmployeePay>> call, Response<List<EmployeePay>> response) {
                if(!response.isSuccessful()) {
                    textResult.setText("Code :"+response.code());
                    return;
                }

                List<EmployeePay>  posts= response.body();
                for(EmployeePay post: posts){
                    String content=" ";
                    content+="EMP ID: "+post.getEmpid()+"\n";
                    content+="Amount: "+post.getAmount()+"\n";
                    content+="Date: "+post.getDate()+"\n";
                    textResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<EmployeePay>> call, Throwable t) {

                textResult.setText(t.getMessage());
            }
        });
    }

    public void createEmpPay(){

        EmployeePay emp= new EmployeePay("2020-10-10","500","4");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:4060/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder JsonHolder=retrofit.create(JsonHolder.class);
        Call<EmployeePay>  call=JsonHolder.createEmpPay(emp);

        call.enqueue(new Callback<EmployeePay>() {
            @Override
            public void onResponse(Call<EmployeePay> call, Response<EmployeePay> response) {
                if(!response.isSuccessful()) {
                    textResult.setText("Code :"+response.code());
                    return;
                }

                EmployeePay post= response.body();

                String content="";
                content+="Response Code: "+response.code()+"\n";
                content+="ID: "+post.getID()+"\n";
                content+="EMP ID: "+post.getEmpid()+"\n";
                content+="Amount: "+post.getAmount()+"\n";
                content+="Date: "+post.getDate()+"\n";

                textResult.append(content);


            }

            @Override
            public void onFailure(Call<EmployeePay> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }
}