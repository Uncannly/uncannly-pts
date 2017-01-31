package com.example;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class PtsController {
    private PtsService ptsService;

    public PtsController(PtsService ptsService) {
        this.ptsService = ptsService;
    }

    @GetMapping
    void pts(@RequestParam(value = "word") String word, HttpServletResponse response) throws IOException {
        InputStream myStream = ptsService.create(word);
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(myStream, outputStream);
        response.flushBuffer();
    }
}


