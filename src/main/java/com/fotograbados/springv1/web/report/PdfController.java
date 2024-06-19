package com.fotograbados.springv1.web.report;

import com.fotograbados.springv1.domain.report.ListarUsuariosPdf;
import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.report.PdfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfUserService pdfService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<byte[]> getUsuariosPdf() {
        ListarUsuariosPdf listarUsuariosPdf = new ListarUsuariosPdf(usuarioService.findAll());
        byte[] pdfBytes = pdfService.generatePdf(listarUsuariosPdf);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=usuarios.pdf");
        headers.add("Content-Type", "application/pdf");
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
