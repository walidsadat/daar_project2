package com.daar.elasticsearch.service;

import com.daar.elasticsearch.model.Cv;
import com.daar.elasticsearch.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {
    private static final String ID_NOT_FOUND_MSG = "ID %s not found !" ;
    private final CvRepository cvRepository;

    @Autowired
    public CvService(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public void save(final Cv cv){
        cvRepository.save(cv);
        System.out.println("CV crée avec l'indentifiant : "+cv.getId());
    }

    public Cv findById(final String id){
        return cvRepository
                .findById(id)
                .orElseThrow( () -> new IllegalArgumentException(String.format(ID_NOT_FOUND_MSG, id)));
    }

    public Iterable<Cv> findAllCv(){
        return cvRepository.findAll();
    }
}
