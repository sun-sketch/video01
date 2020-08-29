package com.domain;

public class Zone {
    private int id;
    private String zonename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZonename() {
        return zonename;
    }

    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", zonename='" + zonename + '\'' +
                '}';
    }
}
