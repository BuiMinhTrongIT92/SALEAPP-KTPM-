/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

/**
 *
 * @author ACER
 */
public class HangHoa {
    private String idHangHoa;
    private String TenHangHoa;
    private Double Gia;
    private String XuatXu;
    private String IDLoaiHH;
    private String AnhHH;
    private Double SL;
    private Double KG;

    public HangHoa() {
    }

    public HangHoa(String idHangHoa, String TenHangHoa, Double Gia, String XuatXu, String IDLoaiHH, String AnhHH, Double SL, Double KG) {
        this.idHangHoa = idHangHoa;
        this.TenHangHoa = TenHangHoa;
        this.Gia = Gia;
        this.XuatXu = XuatXu;
        this.IDLoaiHH = IDLoaiHH;
        this.AnhHH = AnhHH;
        this.SL = SL;
        this.KG = KG;
    }
    
    /**
     * @return the idHangHoa
     */
    public String getIdHangHoa() {
        return idHangHoa;
    }

    /**
     * @param idHangHoa the idHangHoa to set
     */
    public void setIdHangHoa(String idHangHoa) {
        this.idHangHoa = idHangHoa;
    }

    /**
     * @return the TenHangHoa
     */
    public String getTenHangHoa() {
        return TenHangHoa;
    }

    /**
     * @param TenHangHoa the TenHangHoa to set
     */
    public void setTenHangHoa(String TenHangHoa) {
        this.TenHangHoa = TenHangHoa;
    }

    /**
     * @return the Gia
     */
    public Double getGia() {
        return Gia;
    }

    /**
     * @param Gia the Gia to set
     */
    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    /**
     * @return the XuatXu
     */
    public String getXuatXu() {
        return XuatXu;
    }

    /**
     * @param XuatXu the XuatXu to set
     */
    public void setXuatXu(String XuatXu) {
        this.XuatXu = XuatXu;
    }

    /**
     * @return the IDLoaiHH
     */
    public String getIDLoaiHH() {
        return IDLoaiHH;
    }

    /**
     * @param IDLoaiHH the IDLoaiHH to set
     */
    public void setIDLoaiHH(String IDLoaiHH) {
        this.IDLoaiHH = IDLoaiHH;
    }

    /**
     * @return the AnhHH
     */
    public String getAnhHH() {
        return AnhHH;
    }

    /**
     * @param AnhHH the AnhHH to set
     */
    public void setAnhHH(String AnhHH) {
        this.AnhHH = AnhHH;
    }

    /**
     * @return the SL
     */
    public Double getSL() {
        return SL;
    }

    /**
     * @param SL the SL to set
     */
    public void setSL(Double SL) {
        this.SL = SL;
    }

    /**
     * @return the KG
     */
    public Double getKG() {
        return KG;
    }

    /**
     * @param KG the KG to set
     */
    public void setKG(Double KG) {
        this.KG = KG;
    }

    
    
}
