package com.example.retrofit.api_interface;

import com.example.retrofit.models.EmployeePay;
import com.example.retrofit.models.EmployeesDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonHolder {

    @GET("addEmployee")
    Call<List<EmployeesDetails>> getEmp();

    @POST("addEmployee")
    Call<EmployeesDetails> createEmp(@Body EmployeesDetails post);

    @GET("addEmployeePay")
    Call<List<EmployeePay>> getEmpPay();

    @POST("addEmployeePay")
    Call<EmployeePay> createEmpPay(@Body EmployeePay post);
}
