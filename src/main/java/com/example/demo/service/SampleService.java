package com.example.demo.service;

import com.example.demo.entity.Sample;
import com.example.demo.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SampleService {


    @Autowired
    SampleRepository repository;

    public List selectAll(int id){
        return repository.selectAll();
    }

    public List insert(){
        Sample dummy = new Sample();
        dummy.setName("田中");
        dummy.setMail("dummy@mail");
        dummy.setFilterlevel(1);
        repository.insert(dummy);
        return repository.selectAll();
    }
}