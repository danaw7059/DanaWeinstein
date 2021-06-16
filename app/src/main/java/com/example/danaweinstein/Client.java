package com.example.danaweinstein;

public class Client {
    private int id;
    private int account_id;
    private int mani_id;
    private String full_name;

    public Client() {
    }

    public Client(int id, int account_id, int mani_id, String full_name) {
        this.id = id;
        this.account_id = account_id;
        this.mani_id = mani_id;
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getMani_id() {
        return mani_id;
    }

    public void setMani_id(int mani_id) {
        this.mani_id = mani_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", mani_id=" + mani_id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
