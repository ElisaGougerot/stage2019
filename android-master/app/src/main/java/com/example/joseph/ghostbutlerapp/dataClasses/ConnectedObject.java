package com.example.joseph.ghostbutlerapp.dataClasses;

public class ConnectedObject {

    public static final int GAMEPAD = 0;
    public static final int MUSIC = 1;
    public static final int TEXTTOSPOEECH = 2;
    public static final int LIGHT = 3;
    public static final int OUTLET = 4;

    private int type;
    private String name;


    public ConnectedObject(int type, String name){
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
