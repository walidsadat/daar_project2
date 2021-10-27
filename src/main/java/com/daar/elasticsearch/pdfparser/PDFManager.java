package com.daar.elasticsearch.pdfparser;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFManager {
    private PDDocument pdDoc;
    private String filePath;

    public PDFManager() {}

    public String toText() throws IOException {
        PDFTextStripper pdfStripper;
        COSDocument cosDoc;
        this.pdDoc = null;

        File file = new File(filePath);
        PDFParser parser = new PDFParser(
                new RandomAccessFile(file, "r"));

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(0);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
        return pdfStripper.getText(pdDoc);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PDDocument getPdDoc() {
        return pdDoc;
    }
}
