package com.nd.sl.common.bean;

import com.nd.sl.common.constant.Val;

public abstract class Data implements Val {
   public String context;
    @Override
    public void setValue(Object val) {
        context=(String)val;
    }

    @Override
    public Object getValue() {
        return context;
    }
}
