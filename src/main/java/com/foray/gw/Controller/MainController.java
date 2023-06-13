package com.foray.gw.Controller;

import com.foray.gw.Service.Pdflib;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private Pdflib pdflib;

    @GetMapping("/")
    public String Index(){
        return "redirect:/document/list?page=1";
//        return "gw_main/main";
    }
    @GetMapping("/out")
    //html -> pdf 파일 다운로드
    @ResponseBody
    public void PdfView(HttpServletResponse response) throws IOException {
        String html ="<h1>TEST</h1>";

        ByteArrayInputStream byteArrayInputStream = pdflib.convertHtmlToPdf(html);
        //response.setContentType("application/octet-stream");
        //response.setHeader("Content-Disposition", "attachment; filename=file.pdf");
        response.setContentType("application/pdf");
        response.addHeader("Content-Dispostion","attachment:filename=file.pdf");

        int size = byteArrayInputStream.available(); //지정 파일에서 읽을 수 있는 바이트 수를 반환

        byte[] buf = new byte[size]; //버퍼설정

        int readCount = byteArrayInputStream.read(buf);
        response.flushBuffer();

        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        bos.write(buf, 0, readCount);
        bos.flush();
        //IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }
}
