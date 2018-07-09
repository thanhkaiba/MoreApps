package com.example.tienthanh.firstapp.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class App {
    private String id;
    private String name;
    private String link;
    private Bitmap icon;
    private String pkg;
    private int numberCoin;
    private String description;

    public App(String id, String name, String link, Bitmap icon, String pkg, int numberCoin, String description) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.icon = icon;
        this.pkg = pkg;
        this.numberCoin = numberCoin;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public int getNumberCoin() {
        return numberCoin;
    }

    public void setNumberCoin(int numberCoin) {
        this.numberCoin = numberCoin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}