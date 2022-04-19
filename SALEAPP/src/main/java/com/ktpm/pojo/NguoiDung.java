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
public abstract class NguoiDung {
    private String IDNguoiDung;
    private String TenNguoiDung;
    private String TaiKhoan;
    private String MatKhau;
    private String GioiTinh;
    private boolean Active;
    private String Email;
    private Date NgayTao;
    private int SDT;
    private String Role;
    private Date NgaySinh;

    public NguoiDung() {
    }

    public NguoiDung(String IDNguoiDung, String TengNguoiDung, String TaiKhoan, String MatKhau, String GioiTinh, boolean Active, String Email, Date NgayTao, int SDT, String Role,Date NgaySinh) {
        this.IDNguoiDung = IDNguoiDung;
        this.TenNguoiDung = TengNguoiDung;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.GioiTinh = GioiTinh;
        this.Active = Active;
        this.Email = Email;
        this.NgayTao = NgayTao;
        this.SDT = SDT;
        this.Role = Role;
        this.NgaySinh = NgaySinh;
    }
    
    public abstract boolean dangNhap();

    /**
     * @return the IDNguoiDung
     */
    public String getIDNguoiDung() {
        return IDNguoiDung;
    }

    /**
     * @param IDNguoiDung the IDNguoiDung to set
     */
    public void setIDNguoiDung(String IDNguoiDung) {
        this.IDNguoiDung = IDNguoiDung;
    }

    /**
     * @return the TenNguoiDung
     */
    public String getTenNguoiDung() {
        return TenNguoiDung;
    }

    /**
     * @param TenNguoiDung the TenNguoiDung to set
     */
    public void setTenNguoiDung(String TenNguoiDung) {
        this.TenNguoiDung = TenNguoiDung;
    }

    /**
     * @return the TaiKhoan
     */
    public String getTaiKhoan() {
        return TaiKhoan;
    }

    /**
     * @param TaiKhoan the TaiKhoan to set
     */
    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    /**
     * @return the MatKhau
     */
    public String getMatKhau() {
        return MatKhau;
    }

    /**
     * @param MatKhau the MatKhau to set
     */
    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    /**
     * @return the GioiTinh
     */
    public String getGioiTinh() {
        return GioiTinh;
    }

    /**
     * @param GioiTinh the GioiTinh to set
     */
    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    /**
     * @return the Active
     */
    public boolean isActive() {
        return Active;
    }

    /**
     * @param Active the Active to set
     */
    public void setActive(boolean Active) {
        this.Active = Active;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the NgayTao
     */
    public Date getNgayTao() {
        return NgayTao;
    }

    /**
     * @param NgayTao the NgayTao to set
     */
    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    /**
     * @return the SDT
     */
    public int getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the Role
     */
    public String getRole() {
        return Role;
    }

    /**
     * @param Role the Role to set
     */
    public void setRole(String Role) {
        this.Role = Role;
    }

    /**
     * @return the NgaySinh
     */
    public Date getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
   
}
