package com.daar.elasticsearch.controller;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.search.SearchCvRequest;
import com.daar.elasticsearch.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
    public void indexFile(@RequestPart("file") final MultipartFile file) throws IOException { cvService.indexFile(file);}

    @PostMapping("/all")
    public List<Cv> search() {return cvService.getAllCv();}
}
