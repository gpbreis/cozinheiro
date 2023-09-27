package dev.gpbreis.cozinheiro.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contractor {

    public static final int PHONE = 1;
    public static final int CELLPHONE = 2;
    public static final int EMAIL = 3;

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String sex;
    @NonNull
    private String phone;
    @NonNull
    private String cellphone;
    @NonNull
    private String email;
    private Boolean priority;
    @NonNull
    private int contractPreference;

    public Contractor(String name, String sex, String phone, String cellphone, String email, Boolean priority, int contractPreference) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.cellphone = cellphone;
        this.email = email;
        this.priority = priority;
        this.contractPreference = contractPreference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public int getContractPreference() {
        return contractPreference;
    }

    public void setContractPreference(int contractPreference) {
        this.contractPreference = contractPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
