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
public class CaseSummary {
    @SerializedName("case_results")
    @Expose
    private Map<String,CaseResult> caseResult;
    @SerializedName("total_score")
    @Expose
    private double totalScore;
    
    public Map<String,CaseResult> getCaseResult(){
        return this.caseResult;
    }
        public double getTotalScore(){
        return this.totalScore;
    }
    
    public void setTotalScore(double totalScore){
        this.totalScore = totalScore;
    }
    public void setCaseResult(Map<String,CaseResult> caseResult){
        this.caseResult = caseResult;
    }
}
