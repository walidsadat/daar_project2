package com.daar.elasticsearch.service;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {
    private final CvRepository cvRepository;

    @Autowired
    public CvService(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public void save(final Cv cv){
        cvRepository.save(cv);
    }

    public Cv findById(final String id){
        return cvRepository.findById(id).orElse(null);
    }
}
