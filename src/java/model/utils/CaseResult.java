/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class CaseResult {
    @SerializedName("result")
    @Expose
    private boolean result;
    @SerializedName("score")
    @Expose
    private int score;
    
    public boolean getResult(){
        return this.result;
    }
    public int getScore(){
        return this.score;
    }
    
    public void setResult(boolean result){
        this.result = result;
    }
    public void setScore(int score){
        this.score = score;
    }
}
