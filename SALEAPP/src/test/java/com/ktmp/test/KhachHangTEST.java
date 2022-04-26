/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.pojo.KhachHang;
import com.ktpm.services.KhachHangService;
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
public class KhachHangTEST {
    private static KhachHangService khSV;
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        khSV = new KhachHangService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //getKHByTen
    @Test
    public void testExistgetKHByTen() throws SQLException{
        List<KhachHang> listkh = khSV.getKHByTen("Lam");
        Assertions.assertEquals(1, listkh.size());
    }
    @Test
    public void testNotExistgetKHByTen() throws SQLException{
        List<KhachHang> listkh = khSV.getKHByTen("Ngan");
        Assertions.assertEquals(0, listkh.size());
    }
    //getKHBySDT
     @Test
    public void testExistgetKHBySDT() throws SQLException{
        List<KhachHang> listkh = khSV.getKHBySDT("123456789");
        Assertions.assertEquals(1, listkh.size());
    }
    @Test
    public void testNotExistgetKHBySDT() throws SQLException{
        List<KhachHang> listkh = khSV.getKHBySDT("1234567");
        Assertions.assertEquals(0, listkh.size());
    }
}
