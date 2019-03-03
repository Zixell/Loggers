package com.zixel.core.beans;

public class Client {
    private String id;
    private String fullName;

    private String greeting;

    public Client(){

    }

    public Client(String id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getFullName(){
        return this.fullName;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
