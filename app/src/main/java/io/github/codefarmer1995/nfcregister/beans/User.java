package io.github.codefarmer1995.nfcregister.beans;


import java.util.Objects;


public class User {


    private String name;
    private String email;
    private String password;
    private String phone;
    private Boolean sex;

    private String address;

    public User(String name, String email, String password, String phone, Boolean sex, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.sex = sex;

        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(sex, that.sex) &&

                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email, password, phone, sex, address);
    }


}
