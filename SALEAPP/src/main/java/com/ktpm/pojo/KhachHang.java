/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class KhachHang extends NguoiDung{

    @Override
    public boolean dangNhap() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public KhachHang() {
    }
    public KhachHang(String IDNguoiDung, String TenNguoiDung, String TaiKhoan, String MatKhau, String GioiTinh, boolean Active, String Email, Date NgayTao, int SDT, String Role,Date NgaySinh){
        super(IDNguoiDung, TenNguoiDung, TaiKhoan, MatKhau, GioiTinh, Active, Email, NgayTao, SDT, Role,NgaySinh);
    }
    
}
