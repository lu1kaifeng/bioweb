package edu.henu.bioweb;

public class GetPathReturn {

    private final byte[] plotBytes;
    private final String strTip;

    public GetPathReturn(byte[] plotBytes, String strTip) {
        this.plotBytes = plotBytes;
        this.strTip = strTip;
    }

    public GetPathReturn(String strTip) {
        this.plotBytes = null;
        this.strTip = strTip;
    }

    public String getStrTip() {
        return strTip;
    }

    public RPlot getPlotBytes() {
        return new RPlot(plotBytes);
    }
}
