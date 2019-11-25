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
    public String code;
    public String lang;
    
    public ExecutionData(){}
    public ExecutionData(String code, String lang){
        this.code = code;
        this.lang = lang;
    }
}
