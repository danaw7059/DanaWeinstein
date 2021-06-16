package com.example.danaweinstein;

public class Manicurist {
    private int id;
    private int account_id;
    private String details;
    private String full_name;

    public Manicurist() {
    }

    public Manicurist(int id, int account_id, String details, String full_name) {
        this.id = id;
        this.account_id = account_id;
        this.details = details;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "Manicurist{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", details='" + details + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
