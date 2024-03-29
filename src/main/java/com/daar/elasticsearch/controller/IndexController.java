package com.daar.elasticsearch.controller;

import com.daar.elasticsearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cv")
public class IndexController {
    private final IndexService indexService;
    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }
    @PostMapping("/recreate")
    public void recreateAllIndices(){
        indexService.recreateIndices(true);
    }
}
