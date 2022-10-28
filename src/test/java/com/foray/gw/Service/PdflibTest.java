package com.foray.gw.Service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

class PdflibTest {
    private static final String ORIG = "http://localhost:8080/";
    private static final String OUTPUT_FOLDER = "E:\\data\\mycode\\church_gw\\";
    @Autowired
    private Pdflib pdflib;
    @Autowired
    private HttpServletResponse response;
    @Test
   void main() throws IOException
    {
        String html ="<h1>TEST</h1>";

        ByteArrayInputStream byteArrayInputStream = pdflib.convertHtmlToPdf(html);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}