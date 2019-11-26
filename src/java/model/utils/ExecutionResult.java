/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ExecutionResult {
    @SerializedName("results")
    @Expose
    private CaseSummary caseSummary;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("status")
    @Expose
    private int status;
    
    public CaseSummary getCaseSummary(){
        return this.caseSummary;
    }
    public double getStatus(){
        return this.status;
    }
    
    public String getError(){
        return this.error;
    }
    
    public void setCaseSummary(CaseSummary caseSummary){
        this.caseSummary = caseSummary;
    }
    public void setError(String error){
        this.error = error;
    }
    public void setStatus(int status){
        this.status = status;
    }
    
}
