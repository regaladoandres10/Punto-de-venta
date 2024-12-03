/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author GORDILLO
 */
public class RegistroReporteTrimestral{
    private String nombreProd;
    private int trimestre1;
    private int trimestre2;
    private int trimestre3;
    private int trimestre4;

    public RegistroReporteTrimestral(String nombreProd, int trimestre1, int trimestre2, int trimestre3, int trimestre4) {
        this.nombreProd = nombreProd;
        this.trimestre1 = trimestre1;
        this.trimestre2 = trimestre2;
        this.trimestre3 = trimestre3;
        this.trimestre4 = trimestre4;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public int getTrimestre1() {
        return trimestre1;
    }

    public void setTrimestre1(int trimestre1) {
        this.trimestre1 = trimestre1;
    }

    public int getTrimestre2() {
        return trimestre2;
    }

    public void setTrimestre2(int trimestre2) {
        this.trimestre2 = trimestre2;
    }

    public int getTrimestre3() {
        return trimestre3;
    }

    public void setTrimestre3(int trimestre3) {
        this.trimestre3 = trimestre3;
    }

    public int getTrimestre4() {
        return trimestre4;
    }

    public void setTrimestre4(int trimestre4) {
        this.trimestre4 = trimestre4;
    }
    
    
    
    
    
    
    
    
    
}
