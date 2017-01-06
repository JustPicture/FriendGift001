package com.ak.lkp.friendgift.Entity;

import java.io.Serializable;

/**
 * Created by likunpeng on 2017/1/3.
 */

public class LkpResponse <T> implements Serializable{

    private static final long serialVersionUID = 259684609866270449L;
    public int code;
    public String message;
    public T data;

}
