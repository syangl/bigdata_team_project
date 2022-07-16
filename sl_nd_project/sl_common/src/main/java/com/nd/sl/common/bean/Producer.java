package com.nd.sl.common.bean;

import java.io.Closeable;
import java.io.IOException;

public interface Producer extends Closeable {
    void setIn(DataIn in,DataIn in2);
    void setOut(DataOut out);
    void producer()throws IOException;

}
