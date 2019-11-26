/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.retrofit;

import model.utils.ExecutionData;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

/**
 *
 * @author Administrater
 */
public interface RetrofitExecutionData {
    
    @POST("execute")
    public Call<ExecutionData> sendExecData(@Body ExecutionData execData);
}
