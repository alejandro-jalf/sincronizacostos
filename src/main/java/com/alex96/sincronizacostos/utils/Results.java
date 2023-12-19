/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.utils;

/**
 *
 * @author alefl
 */
public class Results {
    private boolean success;
    private String message;
    private Object data;

    public Results() {
        this.success = false;
        this.message = "";
        this.data = null;
    }
    
    public void createContentAssert(String message, Object data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }
    
    public void createContentError(String message, Object data) {
        this.success = false;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
}
