package com.example.danaweinstein;

public class Meeting {
    private int mani_id;
    private int client_id;
    private String date;

    public Meeting() {
    }

    public int getMani_id() {
        return mani_id;
    }

    public void setMani_id(int mani_id) {
        this.mani_id = mani_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "mani_id=" + mani_id +
                ", client_id=" + client_id +
                ", date='" + date + '\'' +
                '}';
    }
}
