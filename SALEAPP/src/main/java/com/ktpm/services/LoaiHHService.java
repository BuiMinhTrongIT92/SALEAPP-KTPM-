/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.LoaiHH;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCutils;


/**
 *
 * @author ACER
 */
public class LoaiHHService {
    public List<LoaiHH> getLoaiHH() throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM loaihanghoa");
            
            List<LoaiHH> loaiHH = new ArrayList<>();
            while(rs.next()){
                String Id = rs.getString("idLoaiHH");
                String TenLoaiHH = rs.getString("TenLoaiHH");
                String DonVi = rs.getString("DonVi");
                loaiHH.add(new LoaiHH(Id,TenLoaiHH,DonVi));
            }
            return loaiHH;
        }
        
    }
}
