package com.yk47.Studentmanage;



public class Attendance {
    private String Name;
    private Integer Rollno,Attend;;

    public Attendance() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getRollno() {
        return Rollno;
    }

    public void setRollno(Integer rollno) {
        Rollno = rollno;
    }

    public Integer getAttend() {
        return Attend;
    }

    public void setAttend(Integer attend) {
        Attend = attend;
    }
}