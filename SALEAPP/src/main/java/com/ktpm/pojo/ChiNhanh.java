/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author NhatTien
 */
public class ChiNhanh {

    
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
     * @return the IDChiNhanh
     */
    public String getIDChiNhanh() {
        return IDChiNhanh;
    }

    /**
     * @param IDChiNhanh the IDChiNhanh to set
     */
    public void setIDChiNhanh(String IDChiNhanh) {
        this.IDChiNhanh = IDChiNhanh;
    }

    /**
     * @return the tenChiNhanh
     */
    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    /**
     * @param tenChiNhanh the tenChiNhanh to set
     */
    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    private String IDChiNhanh;
    private String tenChiNhanh;
    private String diaChi;
    private String IDNguoiDung;
    private boolean Active;
    
    public ChiNhanh() {
    }
    
    public ChiNhanh(String IDChiNhanh, String tenChiNhanh, String diaChi, String IDNguoiDung, boolean Active) {
        this.IDChiNhanh = IDChiNhanh;
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.IDNguoiDung = IDNguoiDung;
        this.Active = Active;
    }
}
