package com.daar.elasticsearch.controller;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.search.SearchCvRequest;
import com.daar.elasticsearch.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void index(@RequestBody final Cv cv){
        cvService.index(cv);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Cv findById(@PathVariable final String id){
        return cvService.getById(id);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Cv> search(@RequestBody final SearchCvRequest request){
        return cvService.search(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Cv> search() {return cvService.getAllCv();}
}
