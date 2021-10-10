package com.example.demo.implementation;

import com.example.demo.classes.Chair;
import com.example.demo.services.services;



import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


@RequestMapping("/hello")
@RestController
public class ServiceClass implements services {

    @Override
    public Object findMe() {
        System.out.println("Something pressesdd");
        return "Hello";
    }

    @Override
    public void addPeople(Chair chair) {
        System.out.println(chair);
    }

    @Override
    public byte[] returnPrabhas() throws IOException {
        File initialFile = new File("Prabhas_at_MAMI_18th_Mumbai_film_festival.jpg");
        InputStream in = new FileInputStream(initialFile);
        return StreamUtils.copyToByteArray(in);
    }

}
