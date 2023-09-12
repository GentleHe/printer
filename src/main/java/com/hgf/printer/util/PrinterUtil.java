package com.hgf.printer.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.IIOException;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.awt.color.CMMException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PrinterUtil {
    public static List<String> listPrinterNames(){
        List<String> list = new ArrayList<>();
        HashPrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //查找所有的可用的打印服务
        PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, requestAttributeSet);
        if (printService == null || printService.length == 0) {

            log.info("打印获取失败，未找到可用打印机，请检查。");
        }
        if (printService != null) {
            for (PrintService print : printService) {
                list.add(print.getName());
                System.out.println(print.getName());
            }
        }
        return list;
    }

    public static void print(String printerName, String text){
        PrintServiceLookup.lookupDefaultPrintService();
        PrintServiceLookupProvider printServiceLookupProvider = new PrintServiceLookupProvider();
        printServiceLookupProvider.getPrintServiceByName(printerName);
//        PrintService printService = new Win32Prin;
//        DocPrintJob printJob = printService.createPrintJob();
//        printJob.print();

    }

    public static void main(String[] args) {
        PrinterJobWrapper
        PrintServiceLookupProvider printServiceLookupProvider = new PrintServiceLookupProvider();
        PrintService fax = printServiceLookupProvider.getPrintServiceByName("Fax");
        System.out.println(fax.getName());
    }
}
