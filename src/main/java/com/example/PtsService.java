package com.example;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
class PtsService {
    InputStream create(String word) {
        return new ByteArrayInputStream( word.getBytes() );
    }
}
