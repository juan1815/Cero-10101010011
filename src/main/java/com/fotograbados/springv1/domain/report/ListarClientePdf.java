package com.fotograbados.springv1.domain.report;

import com.fotograbados.springv1.persistence.entities.Users;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.util.List;
import java.util.Map;

@Component("/administrador/usuarios")
public class ListarClientePdf extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Users> listUsers = (List<Users>) model.get("usuarios");
        PdfPTable tablaClient = new PdfPTable(6);
        listUsers.forEach(client ->{
            tablaClient.addCell(client.getId().toString());
            tablaClient.addCell(client.getNombre());
            tablaClient.addCell(client.getDireccion());
            tablaClient.addCell(client.getEmail());
            tablaClient.addCell(client.getTelefono());
            tablaClient.addCell(client.getGenero());
        });
        document.add(tablaClient);
    }
}
