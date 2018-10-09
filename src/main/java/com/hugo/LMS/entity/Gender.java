package com.hugo.LMS.entity;

public enum Gender {

    MALE("male"),
    FEMALE("female");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender fromName(String name){
        for(Gender gender: values()){
            if(name.equalsIgnoreCase(gender.name())){
                return gender;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
