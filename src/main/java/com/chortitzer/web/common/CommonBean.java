/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.web.common;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Industria
 */
@Controller
@Scope("view")
public class CommonBean {

    public String mesInttoString(Integer mes) {
        switch (mes) {
            case 1: {
                return "Enero";
            }
            case 2: {
                return "Febrero";
            }
            case 3: {
                return "Marzo";
            }
            case 4: {
                return "Abril";
            }
            case 5: {
                return "Mayo";
            }
            case 6: {
                return "Junio";
            }
            case 7: {
                return "Julio";
            }
            case 8: {
                return "Agosto";
            }
            case 9: {
                return "Septiembre";
            }
            case 10: {
                return "Octubre";
            }
            case 11: {
                return "Noviembre";
            }
            case 12: {
                return "Diciembre";
            }
            default: {
                return "Error";
            }
        }
    }

    public String mesInttoString(String mes) {
        switch (mes) {
            case "1": {
                return "Enero";
            }
            case "2": {
                return "Febrero";
            }
            case "3": {
                return "Marzo";
            }
            case "4": {
                return "Abril";
            }
            case "5": {
                return "Mayo";
            }
            case "6": {
                return "Junio";
            }
            case "7": {
                return "Julio";
            }
            case "8": {
                return "Agosto";
            }
            case "9": {
                return "Septiembre";
            }
            case "10": {
                return "Octubre";
            }
            case "11": {
                return "Noviembre";
            }
            case "12": {
                return "Diciembre";
            }
            default: {
                return "Error";
            }
        }
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
    }

}
