package com.example;

import com.example.helpers.StubServletOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IOUtils.class)
public class PtsControllerTest {

    private PtsService ptsService;
    private ByteArrayInputStream myStream;
    private PtsController controller;

    @Before
    public void setUp() throws Exception {
        ptsService = mock(PtsService.class);
        myStream = new ByteArrayInputStream("helo".getBytes());
        when(ptsService.create("helo")).thenReturn(myStream);
        controller = new PtsController(ptsService);
    }

    @Test
    public void test_pts_callsPtsServiceWithWordQueryParam() throws Exception {
        MockMvc subject = standaloneSetup(controller).build();


        subject.perform(get("/?word=helo"))
                .andExpect(status().isOk());


        verify(ptsService).create("helo");
    }

    @Test
    public void test_pts_copiesInputStreamToResponseOutputStream() throws Exception {
        mockStatic(IOUtils.class);

        HttpServletResponse response = mock(HttpServletResponse.class);
        StubServletOutputStream servletOutputStream = new StubServletOutputStream();
        when(response.getOutputStream()).thenReturn(servletOutputStream);


        controller.pts("helo", response);


        verifyStatic(times(1));
        IOUtils.copy(myStream, response.getOutputStream());

        verify(response).flushBuffer();
    }
}


