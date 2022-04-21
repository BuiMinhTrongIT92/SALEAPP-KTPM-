/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import com.ktpm.pojo.HangHoa;
import com.ktpm.pojo.LoaiHH;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class KhuyenMaiService {
    public double getKM(String ChuVu) throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
             PreparedStatement stm = conn.prepareStatement("SELECT Giam FROM khuyenmai WHERE ChucVu = N?");
            stm.setString(1,ChuVu);
            ResultSet rs = stm.executeQuery();
            double giam = 0;
            while(rs.next()){
                giam = rs.getDouble("Giam");
            }
            return giam;
        }
        
    }
}
