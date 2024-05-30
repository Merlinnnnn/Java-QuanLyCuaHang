/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.LapTrinhTienTien.ui.model;

/**
 *
 * @author Hi
 */
public class modelChartLine {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public modelChartLine(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public modelChartLine() {
    }

    private String name;
    private double value;
}
