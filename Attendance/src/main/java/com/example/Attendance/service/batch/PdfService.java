package com.example.Attendance.service.batch;


import com.example.Attendance.error.CustomException;
import com.example.Attendance.error.ErrorCode;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
@Slf4j
public class PdfService {

    public byte[] convertHtmlToPdf(String html) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = renderer.getFontResolver();

            // JAR 내부의 폰트 파일을 스트림으로 읽어오기
            try (InputStream fontStream = getClass().getResourceAsStream("/fonts/NanumGothic-Regular.ttf")) {
                if (fontStream == null) {
                    throw new CustomException(ErrorCode.PDF_CREATE_ERROR);
                }
                fontResolver.addFont(
                        getClass().getResource("/fonts/NanumGothic-Regular.ttf").toString(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED
                );
            }

            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(baos, true);
            baos.flush();

            byte[] pdfContent = baos.toByteArray();
            log.info("PDF 생성 완료: {} bytes", pdfContent.length);
            return pdfContent;

        } catch (Exception e) {
            log.error("PDF 변환 중 오류 발생: {}", e.getMessage(), e);
            throw new CustomException(ErrorCode.PDF_CREATE_ERROR);
        }

    }
}