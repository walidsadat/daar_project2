package com.daar.elasticsearch.pdfparser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDFParser {

    // should download https://www.apache.org/dyn/closer.lua/pdfbox/2.0.24/pdfbox-app-2.0.24.jar
    // and add it to external libraries

    public static String parsePdf(String filePath){
        PDFManager pdfManager = new PDFManager();
        pdfManager.setFilePath(filePath);
        try {
            return pdfManager.toText();
        } catch (IOException ex) {
            Logger.getLogger(com.daar.elasticsearch.pdfparser.PDFParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args) {
    System.out.println(parsePdf("/home/amine/Documents/elasticsearch/src/main/resources/static/pdfs/test.pdf"));
    }
}
