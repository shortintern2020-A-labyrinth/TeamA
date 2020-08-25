package com.example.demo.service;

import com.example.demo.entity.Sample;
//import com.example.demo.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SampleService {

//    @Autowired
//    SampleRepository repository;
//
//    public List<Sample> selectAll(int id){
//        return repository.selectAll(id);
//    }
//
//    public List<Sample> add(){
//        Sample s = new Sample();
//        return repository.add(s,"id");
//    }
}