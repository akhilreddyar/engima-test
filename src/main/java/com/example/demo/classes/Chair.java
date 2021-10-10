package com.example.demo.classes;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Chair implements Serializable {

    String name;
    int size;

}
