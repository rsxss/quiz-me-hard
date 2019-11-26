/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ExecutionData {
    private String code;
    private String lang;
    
    public ExecutionData(){}
    public ExecutionData(String code, String lang){
        this.code = code;
        this.lang = lang;
    }
    
    public String getCode(){
        return this.code;
    }
    public String getLang(){
        return this.lang;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    public void setLang(String lang){
        this.lang = lang;
    }
}
