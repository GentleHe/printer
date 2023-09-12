package com.hgf.printer.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.IIOException;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
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

//        PrintServiceLookupProvider printServiceLookupProvider = new PrintServiceLookupProvider();
//        printServiceLookupProvider.getPrintServiceByName(printerName);
//        PrintService printService = new Win32Prin;
//        DocPrintJob printJob = printService.createPrintJob();
//        printJob.print();

    }

    public static void main(String[] args) throws PrintException {
        HashPrintRequestAttributeSet requestAttributeSet = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        //查找所有的可用的打印服务
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, requestAttributeSet);

        PrintService toPdfService = null;
        for (PrintService printService : printServices) {
            if (printService.getName().equals("Microsoft Print to PDF")) {
                toPdfService = printService;
            }
        }
        System.out.println("hehe");

//        Microsoft Print to PDF
        DocPrintJob printJob = toPdfService.createPrintJob();
        HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();
        Doc doc = new SimpleDoc("123", flavor, hashDocAttributeSet);

        printJob.print(doc, new HashPrintRequestAttributeSet());
    }
}
