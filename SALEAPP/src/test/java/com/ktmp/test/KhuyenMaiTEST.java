/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.KhachHang;
import com.ktpm.services.KhachHangService;
import com.ktpm.services.KhuyenMaiService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.JDBCutils;

/**
 *
 * @author ACER
 */
public class KhuyenMaiTEST {
    private static KhuyenMaiService kmSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        kmSV = new KhuyenMaiService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //getKM
    @Test
    public void testExistgetKM() throws SQLException{
        double kq = kmSV.getKM("QuanLy");
        Assertions.assertEquals(0.5,kq );
    }
    @Test
    public void testNotExistgetKM() throws SQLException{
        double kq = kmSV.getKM("KhachHang");
        Assertions.assertEquals(0,kq );
    }
}
