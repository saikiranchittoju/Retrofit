package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofit.api_interface.JsonHolder;
import com.example.retrofit.models.EmployeesDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpDetailsActivity extends AppCompatActivity {

    TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        textResult = findViewById(R.id.text_result);

        createEmp();
    }
    public void getEmployees()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:4060/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder JsonHolder=retrofit.create(JsonHolder.class);
        Call<List<EmployeesDetails>> call=JsonHolder.getEmp();

        call.enqueue(new Callback<List<EmployeesDetails>>() {
            @Override
            public void onResponse(Call<List<EmployeesDetails>> call, Response<List<EmployeesDetails>> response) {
                if(!response.isSuccessful()) {
                    textResult.setText("Code :"+response.code());
                    return;
                }

                List<EmployeesDetails>  posts= response.body();
                for(EmployeesDetails post: posts){
                    String content=" ";
                    content+="First Name: "+post.getFirstname()+"\n";
                    content+="Last Name: "+post.getLastname()+"\n";
                    content+="Username: "+post.getUsername()+"\n";
                    content+="Business Code: "+post.getBusinesscode() +"\n";
                    content+="Phone: "+post.getPhone() +"\n";
                    content+="Address: "+post.getAddress() +"\n";
                    textResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<EmployeesDetails>> call, Throwable t) {

                textResult.setText(t.getMessage());
            }
        });
    }

    public void createEmp(){

        EmployeesDetails emp= new EmployeesDetails("Varun_chittoju","BC1001","varun@111","varun","varun","514-224-1006","Montreal");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:4060/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder JsonHolder=retrofit.create(JsonHolder.class);
        Call<EmployeesDetails>  call=JsonHolder.createEmp(emp);

        call.enqueue(new Callback<EmployeesDetails>() {
            @Override
            public void onResponse(Call<EmployeesDetails> call, Response<EmployeesDetails> response) {
                if(!response.isSuccessful()) {
                    textResult.setText("Code :"+response.code());
                    return;
                }

                EmployeesDetails post= response.body();

                String content="";
                content+="Response Code: "+response.code()+"\n";
                content+="ID: "+post.getId()+"\n";
                content+="First Name: "+post.getFirstname()+"\n";
                content+="Last Name: "+post.getLastname()+"\n";
                content+="Username: "+post.getUsername()+"\n";
                content+="Business Code: "+post.getBusinesscode() +"\n";
                content+="Phone: "+post.getPhone() +"\n";
                content+="Address: "+post.getAddress() +"\n";

                textResult.append(content);


            }

            @Override
            public void onFailure(Call<EmployeesDetails> call, Throwable t) {
                textResult.setText(t.getMessage());
            }
        });
    }
}