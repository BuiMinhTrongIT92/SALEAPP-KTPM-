/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;


import com.ktpm.pojo.NguoiDung;
import com.ktpm.pojo.NhanVien;
import com.ktpm.pojo.QuanLy;
import com.ktpm.services.QuanLyService;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class QuanLyTEST {
     private static QuanLyService ql;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        ql = new QuanLyService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
     public void testExistfindQLByUSPS() throws SQLException{
         QuanLy quanly = ql.findQLByUSPS("Lam", "202cb962ac59075b964b07152d234b70");
        Assertions.assertEquals("1", quanly.getIDNguoiDung());  
     }
       @Test
     public void testFailfindQLByUSPS() throws SQLException{
         QuanLy quanly = ql.findQLByUSPS("Lam", "202cb962ac59075b964b07152d234b70");
//        Assertions.assertEquals("1", quanly.getIDNguoiDung());  
     }
}
