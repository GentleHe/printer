package com.hgf.printer.util;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.FileInputStream;
import java.io.IOException;

public class PrintImage {
    public PrintImage(String filename) {
        try {
            // 获得打印属性
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(new Copies(1));
            // 获得打印设备
            PrintService pss[] = PrintServiceLookup.lookupPrintServices(
                    DocFlavor.INPUT_STREAM.GIF, pras);
            if (pss.length == 0)
                throw new RuntimeException("No printer services available.");
            PrintService toPdfService = null;
            for (PrintService printService : pss) {
                if (printService.getName().equals("Microsoft Print to PDF")) {
                    toPdfService = printService;
                }
            }

            System.out.println("Printing to " + toPdfService);
            // 获得打印工作
            DocPrintJob job = toPdfService.createPrintJob();
            FileInputStream fin = new FileInputStream(filename);
            Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null);
            // 开始打印
            job.print(doc, pras);
            fin.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (PrintException pe) {
            pe.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {

        new PrintImage("D://img.png");
    }
}
