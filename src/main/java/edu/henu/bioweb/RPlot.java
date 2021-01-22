package edu.henu.bioweb;


import org.apache.commons.codec.binary.Base64;

public class RPlot {
    private final byte[] bytes;

    public RPlot() {
        this.bytes = null;
    }

    public RPlot(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        if(bytes != null)
           return "data:image/png;base64," + new String(new Base64().encode(bytes));
        else
            return "data:image/png;base64,";
    }
}
