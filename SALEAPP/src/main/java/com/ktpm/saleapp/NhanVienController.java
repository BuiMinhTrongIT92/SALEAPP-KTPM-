/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ktpm.saleapp;

import com.ktpm.pojo.NhanVien;
import com.ktpm.services.NhanVienService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class NhanVienController implements Initializable {
    private static final NhanVienService nvsv = new NhanVienService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public boolean LoginNVBH(String tk, String pass) throws SQLException{
        if(tk != null && pass !=null){
            if(nvsv.findNVByUSPS(tk, pass).getTaiKhoan().contains(tk) && nvsv.findNVByUSPS(tk, pass).getMatKhau().contains(pass) && nvsv.findNVByUSPS(tk, pass).getRole().contains("NhanVien")
                    && nvsv.findNVByUSPS(tk, pass).isActive() == true){
                return true;
            }
            else{
                 return false;
            }
        }
         return false;
       
    }
}
