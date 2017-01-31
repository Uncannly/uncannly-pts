package com.example.helpers;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StubServletOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public void write(int i) throws IOException {
        baos.write(i);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {

    }
}
