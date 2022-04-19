package com.ktpm.saleapp;

import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.QuanLyService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import utils.JDBCutils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class QuanLyController implements Initializable {
   private static final QuanLyService qlsv = new QuanLyService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
   public boolean LoginQL(String tk, String pass) throws SQLException{
        if(tk != null && pass !=null){
            if(qlsv.findQLByUSPS(tk, pass).getTaiKhoan().contains(tk) && qlsv.findQLByUSPS(tk, pass).getMatKhau().contains(pass) && 
                    qlsv.findQLByUSPS(tk, pass).getRole().contains("QuanLy")
                    && qlsv.findQLByUSPS(tk, pass).isActive()){
                return true;
            }
            else{
                 return false;
            }
        }
         return false;
    }
   
}
