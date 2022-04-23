/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.pojo;

import javafx.scene.control.Button;

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
    private Button btnSL;
    private Button btnKG;
    private Button btnCong;
    private Button btnTru;
    private Button btnXoa;
    private Double GiaGiam;
    private boolean Active;
    public HangHoa() {
    }

    public HangHoa(String idHangHoa, String TenHangHoa, Double Gia, String XuatXu, String IDLoaiHH, String AnhHH, Double SL, Double KG,boolean active) {
        this.idHangHoa = idHangHoa;
        this.TenHangHoa = TenHangHoa;
        this.Gia = Gia;
        this.XuatXu = XuatXu;
        this.IDLoaiHH = IDLoaiHH;
        this.AnhHH = AnhHH;
        this.SL = SL;
        this.KG = KG;
        this.Active = active;
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

    /**
     * @return the btnSL
     */
    public Button getBtnSL() {
        return btnSL;
    }

    /**
     * @param btnSL the btnSL to set
     */
    public void setBtnSL(Button btnSL) {
        this.btnSL = btnSL;
    }

    /**
     * @return the btnKG
     */
    public Button getBtnKG() {
        return btnKG;
    }

    /**
     * @param btnKG the btnKG to set
     */
    public void setBtnKG(Button btnKG) {
        this.btnKG = btnKG;
    }

    /**
     * @return the btnCong
     */
    public Button getBtnCong() {
        return btnCong;
    }

    /**
     * @param btnCong the btnCong to set
     */
    public void setBtnCong(Button btnCong) {
        this.btnCong = btnCong;
    }

    /**
     * @return the btnTru
     */
    public Button getBtnTru() {
        return btnTru;
    }

    /**
     * @param btnTru the btnTru to set
     */
    public void setBtnTru(Button btnTru) {
        this.btnTru = btnTru;
    }

    /**
     * @return the btnXoa
     */
    public Button getBtnXoa() {
        return btnXoa;
    }

    /**
     * @param btnXoa the btnXoa to set
     */
    public void setBtnXoa(Button btnXoa) {
        this.btnXoa = btnXoa;
    }

    /**
     * @return the GiaGiam
     */
    public Double getGiaGiam() {
        return GiaGiam;
    }

    /**
     * @param GiaGiam the GiaGiam to set
     */
    public void setGiaGiam(Double GiaGiam) {
        this.GiaGiam = GiaGiam;
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
