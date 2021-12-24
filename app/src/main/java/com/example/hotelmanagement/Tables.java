package com.example.hotelmanagement;

public class Tables{
    String T_no;
    String T_id;
    String T_state;

    public Tables(String t_no, String t_id, String t_state) {
        T_no = t_no;
        T_id = t_id;
        T_state = t_state;
    }

    public String getT_no() {
        return T_no;
    }

    public void setT_no(String t_no) {
        T_no = t_no;
    }

    public String getT_id() {
        return T_id;
    }

    public void setT_id(String t_id) {
        T_id = t_id;
    }

    public String getT_state() {
        return T_state;
    }

    public void setT_state(String t_state) {
        T_state = t_state;
    }
}
