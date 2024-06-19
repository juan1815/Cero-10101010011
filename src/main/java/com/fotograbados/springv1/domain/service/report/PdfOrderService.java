package com.fotograbados.springv1.domain.service.report;

import com.fotograbados.springv1.domain.report.ListOrderUserPdf;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
@Service
public class PdfOrderService {
    public byte[] generatePdf(ListOrderUserPdf listOrderUserPdf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Crear y agregar el título
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Ventas", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); // Espacio después del título
            document.add(title);

            // Crear una tabla con 4 columnas
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Fuentes para las celdas de encabezado y las celdas normales
            Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font cellFont = new Font(Font.HELVETICA, 12);

            // Añadir encabezados
            PdfPCell cell = new PdfPCell(new Phrase("ID", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha_venta", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha_envio", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Total", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Añadir datos de usuarios
            for (OrderEntity order : listOrderUserPdf.getOrderEntities()) {
                cell = new PdfPCell(new Phrase(String.valueOf(order.getIdOrder()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(order.getFechaVenta()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(order.getFechaEnvio()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(order.getTotal()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


            }
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return baos.toByteArray();
    }
}
