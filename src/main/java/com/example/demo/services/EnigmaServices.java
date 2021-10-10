package com.example.demo.services;


import com.example.demo.enigma.dto.Input;
import com.example.demo.enigma.dto.Output;
import com.example.demo.enigma.dto.Settings;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface EnigmaServices {


    @PostMapping("/encrypt")
    Output engimaEncrypt(@RequestBody Input input);

    @PostMapping("/set-config")
    String setConfig(@RequestBody Settings set);
}
