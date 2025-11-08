package com.example.demo.models;

public enum Position {
    MANAGER,SUPERVISOR,INTERN,MEMBER;



public String toString() {
    switch(this){
        case MANAGER:
            return "Manager";
        case SUPERVISOR:
            return "Supervisor";
        case INTERN:
            return "Intern";
        case MEMBER:
            return "Member";
        default:
            return "";
    }
}
}
