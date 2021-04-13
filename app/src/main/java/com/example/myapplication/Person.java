package com.example.myapplication;

public class Person
{
    private String name;
    private String email;
    private String address;
    private String phone;

    public Person()
    {

    }
    public Person(String name, String email, String address,String phone)
    {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    public String getAddress()
    {
        return address;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
