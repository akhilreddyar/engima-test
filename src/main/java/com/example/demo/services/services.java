package com.example.demo.services;

import com.example.demo.classes.Chair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Api("sample")
@ApiModel("Just does something")
@RequestMapping("/default")
public interface services {

    @GetMapping("/findMe")
    Object findMe();

    @PostMapping("/add")
    void addPeople(@RequestBody Chair chair);

    @GetMapping(value = "/", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] returnPrabhas() throws IOException;


}
