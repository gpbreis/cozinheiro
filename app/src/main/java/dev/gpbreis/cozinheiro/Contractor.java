package dev.gpbreis.cozinheiro;

public class Contractor {

    private String name;
    private String sex;
    private String phone;
    private String cellphone;
    private String email;
    private Boolean priority;
    private int contractPreference;

    public Contractor(String name, String sex, String phone, String cellphone, String email, int contractPreference) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.cellphone = cellphone;
        this.email = email;
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
}
