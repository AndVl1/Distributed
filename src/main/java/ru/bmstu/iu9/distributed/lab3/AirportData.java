package ru.bmstu.iu9.distributed.lab3;

import scala.Serializable;

public class AirportData implements Serializable {
    private int id;
    private String name;

    public AirportData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AirportData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
