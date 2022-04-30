package com.example.demo.enigma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Settings {


    @Pattern(regexp = "[A-Z]")
    String mapping;

    @Size(min = 3, max = 3)
    List<Integer> initialValues;

}
