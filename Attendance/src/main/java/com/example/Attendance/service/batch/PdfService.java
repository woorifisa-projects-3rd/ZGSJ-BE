package com.example.Attendance.service.batch;


import com.example.Attendance.error.CustomException;
import com.example.Attendance.error.ErrorCode;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;  // 추가
import org.apache.commons.io.IOUtils;  // 추가

@Service
@Slf4j
public class PdfService {

    @Value("classpath:fonts/NanumGothic-Regular.ttf")
    private Resource regularFont;

    @Value("classpath:fonts/NanumGothic-Bold.ttf")
    private Resource boldFont;

    @Value("classpath:fonts/NanumGothic-ExtraBold.ttf")
    private Resource extraBoldFont;

    public byte[] convertHtmlToPdf(String html) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ITextRenderer renderer = new ITextRenderer();

            // 폰트 설정
            try (InputStream regularIs = regularFont.getInputStream();
                 InputStream boldIs = boldFont.getInputStream();
                 InputStream extraBoldIs = extraBoldFont.getInputStream()) {

                File regularTempFile = File.createTempFile("regular", ".ttf");
                File boldTempFile = File.createTempFile("bold", ".ttf");
                File extraBoldTempFile = File.createTempFile("extraBold", ".ttf");

                FileUtils.copyInputStreamToFile(regularIs, regularTempFile);
                FileUtils.copyInputStreamToFile(boldIs, boldTempFile);
                FileUtils.copyInputStreamToFile(extraBoldIs, extraBoldTempFile);

                renderer.getFontResolver().addFont(
                        regularTempFile.getAbsolutePath(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED,
                        "NanumGothic"
                );
                renderer.getFontResolver().addFont(
                        boldTempFile.getAbsolutePath(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED,
                        "NanumGothicBold"
                );
                renderer.getFontResolver().addFont(
                        extraBoldTempFile.getAbsolutePath(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED,
                        "NanumGothicExtraBold"
                );

                // 임시 파일 삭제
                regularTempFile.deleteOnExit();
                boldTempFile.deleteOnExit();
                extraBoldTempFile.deleteOnExit();
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