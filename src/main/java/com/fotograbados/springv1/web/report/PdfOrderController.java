package com.fotograbados.springv1.web.report;

import com.fotograbados.springv1.domain.report.ListOrderUserPdf;
import com.fotograbados.springv1.domain.service.report.PdfOrderService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pdfOrder")
public class PdfOrderController {
    @Autowired
    private PdfOrderService pdfOrderService;

    @Autowired
    private IOrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<byte[]> getOrdersPdf() {
        List<OrderEntity> orders = orderService.findAll();
        ListOrderUserPdf listOrderUserPdf = new ListOrderUserPdf(orders);
        byte[] pdfBytes = pdfOrderService.generatePdf(listOrderUserPdf);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=orders.pdf");
        headers.add("Content-Type", "application/pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
