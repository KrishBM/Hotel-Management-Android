package com.example.hotelmanagement;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    String T_no,T_id,C_Name,C_No,I_Name,I_Qty,I_Note,I_State,O_id;

    public Order(String t_no, String t_id, String i_Name, String i_Qty, String i_Note, String i_State,String o_Id) {
        T_no = t_no;
        T_id = t_id;
        I_Name = i_Name;
        I_Qty = i_Qty;
        I_Note = i_Note;
        I_State = i_State;
        O_id=o_Id;
    }

    protected Order(Parcel in) {
        T_no = in.readString();
        T_id = in.readString();
        C_Name = in.readString();
        C_No = in.readString();
        I_Name = in.readString();
        I_Qty = in.readString();
        I_Note = in.readString();
        I_State = in.readString();
        O_id=in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

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

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_No() {
        return C_No;
    }

    public void setC_No(String c_No) {
        C_No = c_No;
    }

    public String getI_Name() {
        return I_Name;
    }

    public void setI_Name(String i_Name) {
        I_Name = i_Name;
    }

    public String getI_Qty() {
        return I_Qty;
    }

    public void setI_Qty(String i_Qty) {
        I_Qty = i_Qty;
    }

    public String getI_Note() {
        return I_Note;
    }

    public void setI_Note(String i_Note) {
        I_Note = i_Note;
    }

    public String getI_State() {
        return I_State;
    }

    public void setI_State(String i_State) {
        I_State = i_State;
    }

    public String getO_id() {
        return O_id;
    }

    public void setO_id(String o_id) {
        O_id = o_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(T_no);
        parcel.writeString(T_id);
        parcel.writeString(C_Name);
        parcel.writeString(C_No);
        parcel.writeString(I_Name);
        parcel.writeString(I_Qty);
        parcel.writeString(I_Note);
        parcel.writeString(I_State);
        parcel.writeString(O_id);
    }
}
