/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alex96.sincronizacostos.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jose Alejandro Lopez Flores
 */
public class MessagesResult {
    private boolean status;
    private String message;
    private JSONArray data;
    private JSONObject content;
    
    public MessagesResult() {}
    
/**
 * Metodo para crear un mensaje de exito
 * @param message Cadena de texto con el contenido del mensaje
 * @param data Objeto JSON con la data de informacion, puede ser null
 * @return Objeto Json con mensaje de exito, puede contener data o no
*/
    public JSONObject createContentAssert(String message, JSONArray data) {
        content = new JSONObject();
        content.put("success", true);
        content.put("message", message);
        if (data != null) content.put("data", data);
        return content;
    }
    
    
/**
 * Metodo para crear un mensaje de error
 * @param message Cadena de texto con el contenido del mensaje
 * @param data Objeto JSON con la data de informacion, puede ser null
 * @return Objeto Json con mensaje de error, puede contener data o no
*/
    public JSONObject createContentError(String message, JSONArray data) {
        content = new JSONObject();
        content.put("success", false);
        content.put("message", message);
        if (data != null) content.put("data", data);
        return content;
    }
    
}
