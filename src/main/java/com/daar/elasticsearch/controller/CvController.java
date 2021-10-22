package com.daar.elasticsearch.controller;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cv")
public class CvController {
    private final CvService cvService;

    @Autowired
    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping
    public void save(@RequestBody final Cv cv){
        cvService.save(cv);
    }

    @GetMapping("/{id}")
    public Cv findById(@PathVariable final String id){
        return cvService.findById(id);
    }

    @GetMapping
    public Iterable<Cv> findAll(){
        return cvService.findAllCv();

    }

}
