/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author ACER
 */
public class LoaiHH {
    private String IDloaiHH;
    private String TenLoaiHH;
    private String DonVi;
    private boolean Active;

    public LoaiHH() {
    }

    public LoaiHH(String IDloaiHH, String TenLoaiHH, String DonVi,boolean active) {
        this.IDloaiHH = IDloaiHH;
        this.TenLoaiHH = TenLoaiHH;
        this.DonVi = DonVi;
        this.Active = active;
    }

    public LoaiHH(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return this.TenLoaiHH;
    }
    
    

    /**
     * @return the IDloaiHH
     */
    public String getIDloaiHH() {
        return IDloaiHH;
    }

    /**
     * @param IDloaiHH the IDloaiHH to set
     */
    public void setIDloaiHH(String IDloaiHH) {
        this.IDloaiHH = IDloaiHH;
    }

    /**
     * @return the TenLoaiHH
     */
    public String getTenLoaiHH() {
        return TenLoaiHH;
    }

    /**
     * @param TenLoaiHH the TenLoaiHH to set
     */
    public void setTenLoaiHH(String TenLoaiHH) {
        this.TenLoaiHH = TenLoaiHH;
    }

    /**
     * @return the DonVi
     */
    public String getDonVi() {
        return DonVi;
    }

    /**
     * @param DonVi the DonVi to set
     */
    public void setDonVi(String DonVi) {
        this.DonVi = DonVi;
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

   
}
