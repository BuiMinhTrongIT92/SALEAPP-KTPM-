/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.control.Alert;

/**
 *
 * @author ACER
 */
public class utills {
    public static Alert showBox (String AlertText, Alert.AlertType type){
        Alert al = new Alert(type);
        al.setContentText(AlertText);
        return al;
    }
    
}
