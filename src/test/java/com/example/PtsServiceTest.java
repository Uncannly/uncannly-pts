package com.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.example.helpers.InputStreamHelper.isEqual;
import static org.junit.Assert.assertTrue;

public class PtsServiceTest {
    @Test
    public void test_create_returnsInputStream() throws Exception {
        PtsService subject = new PtsService();
        InputStream result = subject.create("helo");

        ByteArrayInputStream expectedResult = new ByteArrayInputStream("helo".getBytes());

        assertTrue(isEqual(result, expectedResult));
    }

    @Test
    public void test_create_acceptsUtf8() throws Exception {


    }
}


