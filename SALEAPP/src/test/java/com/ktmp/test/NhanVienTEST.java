/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.LoaiHH;
import com.ktpm.pojo.NhanVien;
import com.ktpm.services.LoaiHHService;
import com.ktpm.services.NhanVienService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class NhanVienTEST {
    private static NhanVienService nvSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        nvSV = new NhanVienService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //findNVByID
    @Test
    public void testExistfindNVByID() throws SQLException{
        NhanVien nv = nvSV.findNVByID("d85ed478-a4ac-4359-ac24-1c3e88ddd278");
        Assertions.assertEquals("Sang", nv.getTenNguoiDung());
    }
    @Test
    public void testFailfindNVByID() throws SQLException{
        nvSV.findNVByID("d85ed478-a4ac-4359-ac24-1c3e88ddd278");
//        Assertions.assertNotEquals("Sang", nv.getTenNguoiDung());
    }
}
