package com.example.danaweinstein;

import java.util.Arrays;

public class Design {
    private int id;
    private int id_mani;
    private String details;
    private byte[] image;
    private String company_name;
    private String polish_nail_code;

    public Design(int id, int id_mani, String details, byte[] image, String company_name, String polish_nail_code) {
        this.id = id;
        this.id_mani = id_mani;
        this.details = details;
        this.image = image;
        this.company_name = company_name;
        this.polish_nail_code = polish_nail_code;
    }

    public Design() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_mani() {
        return id_mani;
    }

    public void setId_mani(int id_mani) {
        this.id_mani = id_mani;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getPolish_nail_code() {
        return polish_nail_code;
    }

    public void setPolish_nail_code(String polish_nail_code) {
        this.polish_nail_code = polish_nail_code;
    }

    @Override
    public String toString() {
        return "Design{" +
                "id=" + id +
                ", id_mani=" + id_mani +
                ", details='" + details + '\'' +
                ", image=" + Arrays.toString(image) +
                ", company_name='" + company_name + '\'' +
                ", polish_nail_code='" + polish_nail_code + '\'' +
                '}';
    }

}
