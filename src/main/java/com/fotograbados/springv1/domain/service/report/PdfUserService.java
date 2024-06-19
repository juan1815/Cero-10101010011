package com.fotograbados.springv1.domain.service.report;


import com.fotograbados.springv1.domain.report.ListarUsuariosPdf;
import com.fotograbados.springv1.persistence.entities.Users;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfUserService {

    public byte[] generatePdf(ListarUsuariosPdf listarUsuariosPdf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();


        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Crear y agregar el título
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Reporte de Usuarios", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20); // Espacio después del título
            document.add(title);

            //Creat table with columns
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

            cell = new PdfPCell(new Phrase("Nombre", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Email", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Telef", headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Añadir datos de usuarios
            for (Users users : listarUsuariosPdf.getUsuarios()) {
                cell = new PdfPCell(new Phrase(String.valueOf(users.getId()), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(users.getNombre(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(users.getEmail(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(users.getTelefono(), cellFont));
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
