package com.example.hotelmanagement;

import android.os.Parcel;
import android.os.Parcelable;

public class Tables implements Parcelable {
    String T_no;
    String T_id;
    String T_state;
    String W_id;

    public Tables(String t_no, String t_id, String t_state,String w_id) {
        T_no = t_no;
        T_id = t_id;
        T_state = t_state;
        W_id=w_id;
    }

    protected Tables(Parcel in) {
        T_no = in.readString();
        T_id = in.readString();
        T_state = in.readString();
        W_id=in.readString();
    }

    public static final Creator<Tables> CREATOR = new Creator<Tables>() {
        @Override
        public Tables createFromParcel(Parcel in) {
            return new Tables(in);
        }

        @Override
        public Tables[] newArray(int size) {
            return new Tables[size];
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

    public String getT_state() {
        return T_state;
    }

    public void setT_state(String t_state) {
        T_state = t_state;
    }

    public String getW_id() {
        return W_id;
    }

    public void setW_id(String w_id) {
        W_id = w_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(T_no);
        parcel.writeString(T_id);
        parcel.writeString(T_state);
        parcel.writeString(W_id);
    }
}
