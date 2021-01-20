package edu.henu.bioweb.LMS;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

public class rtest {
    public static void main(String[] args) {
		Rengine engine=new Rengine(args, false, new TextConsole());		
        //Rengine engine = new Rengine(null, false, null);
		//engine.eval("setwd(\"d:/\")"); // ͼ����·��
		engine.eval("library(RODBC)");
		//engine.eval("library(survival)");
		//REXP rexpFolder = engine.eval("getwd()");
		//System.out.println(rexpFolder.asString());
		
		//long chartName = System.currentTimeMillis(); // ʱ��� 
		//engine.eval("jpeg(file=\""+chartName+".jpg\")"); // ͼ�����ʽ
		//engine.eval("plot(1:5, 1:5, xlim = c(0,6), ylim = c (0,6), type = \"n\")");
		//engine.eval("rect(xleft = c(1, 2), ybottom = c(1, 2), xright = c(5, 4), ytop = c(5, 4), border = c(\"red\", \"blue\"))");
		//engine.eval("dev.off()");
		engine.stop();
    }
}