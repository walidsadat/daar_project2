package com.daar.elasticsearch.controller;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.pdfparser.PDFParser;
import com.daar.elasticsearch.search.SearchCvRequest;
import com.daar.elasticsearch.service.CvService;
import org.elasticsearch.client.ml.inference.preprocessing.Multi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/cv")
public class CvController {
    private final CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping
    public void index(@RequestBody final Cv cv){
        cvService.index(cv);
    }

    @GetMapping("/{id}")
    public Cv findById(@PathVariable final String id){
        return cvService.getById(id);
    }

    @PostMapping("/search")
    public List<Cv> search(@RequestBody final SearchCvRequest request){
        return cvService.search(request);
    }

    @PostMapping("/file")
    public void indexFile(@RequestPart("file") final MultipartFile file) throws IOException {
        File convFile = new File("src/main/resources/cvs/"+file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write (file.getBytes());
        fos.close();
        String cvAsString = PDFParser.parsePdf(convFile.getAbsolutePath());
        Cv cv = new Cv();
        cv.setContent(cvAsString);
        cvService.index(cv);
    }

    @PostMapping("/all")
    public List<Cv> search() {return cvService.getAllCv();}
}
