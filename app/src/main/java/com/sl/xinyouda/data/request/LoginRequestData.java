package com.sl.xinyouda.data.request;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.sl.xinyouda.BR;

public class LoginRequestData extends BaseObservable{
    private String username;
    private String password;
    private String registerId = "";
    private String systemCode = "PMSPDA";

    public String getRegisterId() {
        return registerId;
    }

    public String getSystemCode() {
        return systemCode;
    }

    @Bindable
    public String getUsername() {
        return username;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
