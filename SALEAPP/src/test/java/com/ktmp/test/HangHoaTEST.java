/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktmp.test;

import com.ktpm.services.HangHoaService;
import com.ktpm.pojo.HangHoa;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author ASUS
 */
public class HangHoaTEST {
    private static Connection conn;
    private static HangHoaService hhsv;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JDBCutils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        hhsv = new HangHoaService();
    }
    
    @AfterAll
    public static void afterAll() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Test //các sp active =0 cần ghi chú
//    public void testExistGetIDHH() throws SQLException{
//        List<String> listIDHH = new ArrayList<>();
//        listIDHH.add(hhsv.getIDHH("Pepsi"));
//        listIDHH.add(hhsv.getIDHH("Spire"));
//        listIDHH.add(hhsv.getIDHH("Sữa"));
//        listIDHH.add(hhsv.getIDHH("Snack Hành"));
//        listIDHH.add(hhsv.getIDHH("SnackLay"));
//        listIDHH.add(hhsv.getIDHH("Đen"));
//        for (int i=0;i<listIDHH.size();i++)
//            Assertions.assertEquals(String.valueOf(i+1), listIDHH.get(i));
//    }
    @Test
    public void testNotExistGetIDHH() throws SQLException{
        for (int i=0;i<7;i++)
            Assertions.assertNotEquals(String.valueOf(i+1), hhsv.getIDHH("bánh"));
    }
    
    @Test //các sp active =0 cần ghi chú 
    public void testExistGetHangHoa() throws SQLException{
        List<String> listTenHH = new ArrayList<>();
        listTenHH.add(hhsv.getHangHoa("1").getTenHangHoa());
//        listTenHH.add(hhsv.getHangHoa("2").getTenHangHoa());
        listTenHH.add(hhsv.getHangHoa("3").getTenHangHoa());
//        listTenHH.add(hhsv.getHangHoa("4").getTenHangHoa());
        listTenHH.add(hhsv.getHangHoa("5").getTenHangHoa());
        listTenHH.add(hhsv.getHangHoa("6").getTenHangHoa());
        List<String> listTenHHTest = new ArrayList<>();
        listTenHHTest.add("Pepsi");
//        listTenHHTest.add("Spire");
        listTenHHTest.add("Sữa");
//        listTenHHTest.add("Snack Hành");
        listTenHHTest.add("SnackLay");
        listTenHHTest.add("Đen");
        for (int i=0; i<listTenHH.size();i++){
            Assertions.assertEquals(listTenHH.get(i),listTenHHTest.get(i));
        }
    }
    @Test
    public void testExistIsHangHoa() throws SQLException{
        try(Connection conn = JDBCutils.getConn()){
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa where Active = true");
            ResultSet rs = stm.executeQuery();
            String sp="Pepsi";
            List<HangHoa> listHH = new ArrayList<>();
            HangHoa hh = null;
            while (rs.next()){
                hh = new HangHoa();
                hh.setIdHangHoa(rs.getString("idHangHoa"));
                hh.setTenHangHoa(rs.getString("TenHangHoa"));
                hh.setGia(rs.getDouble("Gia"));
                hh.setXuatXu(rs.getString("XuatXu"));
                hh.setIDLoaiHH(rs.getString("idLoaiHH"));
                hh.setAnhHH(rs.getString("AnhHH"));
                hh.setSL(rs.getDouble("SoLuong"));
                hh.setKG(rs.getDouble("KG"));
                listHH.add(hh);
            }
            for (HangHoa htt:listHH){
                System.out.println(htt.getTenHangHoa());
            }
            Assertions.assertEquals(true, hhsv.isHangHoa(listHH, sp));
        }
    }
    @Test
    public void testExistGetHHByKey() throws SQLException{
        List<HangHoa> lsHH = hhsv.getHHByKey("a");
        for (HangHoa i : lsHH){
            Assertions.assertEquals(true, i.getTenHangHoa().contains("a"));
        }   
    }
    
    @Test
    public void testExistGetHHByLoai() throws SQLException{
        List<HangHoa> lsHH = hhsv.getHHByLoai( "Bánh","L");
        for (HangHoa i : lsHH){
            Assertions.assertEquals(true, i.getTenHangHoa().contains("L") && (i.getIDLoaiHH().compareTo("2") ==0) );
        }
    }
    
    @Test
    public void testExistGetSLCheck() throws SQLException{
        Assertions.assertEquals(11.0,hhsv.getSLCheck("Đen"));//đổi theo số lượng trong SQL
    }
    
    @Test
    public void testExistGetKGCheck() throws SQLException{
        Assertions.assertEquals(0.0,hhsv.getKGCheck("Đen"));//đổi theo số lượng trong SQL
    }
    
    @Test
    public void testExistCheckGiamGia() throws SQLException{
//        Date d =new java.sql.Date(2022, 2, 2);
//        System.out.println(d.toString());
        Assertions.assertEquals(6000.0, hhsv.checkGiamGia("1","2022-03-02"));
    }
    
    @Test
    public void testExistUpdateHangHoa() throws SQLException{
        HangHoa hh = new HangHoa();
        hh.setIdHangHoa("99");
        hh.setTenHangHoa("test");
        hh.setGia(0.0);
        hh.setXuatXu("");
        hh.setIDLoaiHH("1");
        hh.setAnhHH("");
        hh.setSL(-1.0);
        hh.setKG(-1.0);
        hhsv.UpdateHH(hh);
        Assertions.assertEquals(3.0,hhsv.getHangHoa("99").getSL());
    }
    @Test
    public void testExistgetHangHoa1() throws SQLException{
        List<HangHoa> lsHH = hhsv.getHangHoa("a",true);
        for (HangHoa i : lsHH){
            Assertions.assertEquals(true, i.getTenHangHoa().contains("a"));
        }  
    }
    @Test
    public void testExistXoaHangHoa() throws SQLException{
        Assertions.assertEquals(1,hhsv.xoaHangHoa("1"));//false
    }
    @Test
    public void testExistthemHangHoa() throws SQLException{
        HangHoa hh = new HangHoa();
        hh.setIdHangHoa("99");
        hh.setTenHangHoa("test");
        hh.setGia(0.0);
        hh.setXuatXu("");
        hh.setIDLoaiHH("1");
        hh.setAnhHH("");
        hh.setSL(0.0);
        hh.setKG(0.0);
        hhsv.themHangHoa(hh);
        Assertions.assertEquals("test",hhsv.getHangHoa("99").getTenHangHoa());
    }

    @Test
    public void testExistcapNhatHangHoa() throws SQLException{
        HangHoa hh = new HangHoa();
        hh.setIdHangHoa("99");
        hh.setTenHangHoa("test");
        hh.setGia(0.0);
        hh.setXuatXu("");
        hh.setIDLoaiHH("1");
        hh.setAnhHH("");
        hh.setSL(-1.0);
        hh.setKG(-1.0);
        hhsv.capNhatHangHoa(hh);
        Assertions.assertEquals(-1.0,hhsv.getHangHoa("99").getSL());
    }
    @Test
    public void testExistxoaHangHoa_TamThoi() throws SQLException{
        Assertions.assertEquals(1,hhsv.xoaHangHoa_TamThoi("99"));
    }
}
