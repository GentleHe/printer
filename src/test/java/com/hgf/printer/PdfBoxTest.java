package com.hgf.printer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PdfBoxTest {

    @Test
    public void testListPrinter(){
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
                }
            }
    }
}
