package org.LapTrinhTienTien.ui.model;

import javax.swing.Icon;

public class modelCard {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return id;
    }

    public void setTitle(String id) {
        this.id = id;
    }

    public String getValues() {
        return date;
    }

    public void setValues(String date) {
        this.date = date;
    }

    public String getDescription() {
        return price;
    }

    public void setDescription(String price) {
        this.price = price;
    }

    public modelCard(Icon icon,String name, String id, String date, String price) {
        this.icon = icon;
        this.name = name;
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public modelCard() {
    }

    private Icon icon;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String id;
    private String date;
    private String price;
}