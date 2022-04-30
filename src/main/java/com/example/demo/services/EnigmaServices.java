package com.example.demo.services;


import com.example.demo.enigma.dto.DecryptRequest;
import com.example.demo.enigma.dto.Input;
import com.example.demo.enigma.dto.Output;
import com.example.demo.enigma.dto.Settings;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface EnigmaServices {


    @PostMapping("/encrypt")
    @ApiOperation("Encrypt any string using enigma")
    Output enigmaEncrypt(@RequestBody Input input);

    @PostMapping("/set-config")
    @ApiOperation("Set the configuration settings")
    String setConfig(@RequestBody Settings set);

    @PostMapping("/decrypt")
    @ApiOperation("Decrypt the enigma message")
    String enigmaDecrypt(@RequestBody DecryptRequest decryptRequest);
}
