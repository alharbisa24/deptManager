package com.example.demo.models;

public enum Position {
    MANAGER,SUPERVISIOR,INTERN,MEMBER;



public String toString() {
    switch(this){
        case MANAGER:
            return "Manager";
        case SUPERVISIOR:
            return "Supervisior";
        case INTERN:
            return "Intern";
        case MEMBER:
            return "Member";
        default:
            return "";
    }
}
}
