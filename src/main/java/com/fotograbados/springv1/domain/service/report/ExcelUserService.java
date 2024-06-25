package com.fotograbados.springv1.domain.service.report;


import com.fotograbados.springv1.domain.report.ListarUsuariosExcel;
import com.fotograbados.springv1.persistence.entities.Users;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelUserService {
    public byte[] generateExcel(ListarUsuariosExcel listarUsuariosExcel) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Reporte de Usuarios");

            // Crear y agregar el título
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Usuarios");
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 18);
            titleStyle.setFont(titleFont);
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

            // Crear una fila para los encabezados
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Nombre", "Email", "Teléfono"};
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerCellStyle.setFont(headerFont);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Añadir datos de usuarios
            List<Users> users = listarUsuariosExcel.getUsers();
            int rowIndex = 2;
            for (Users user : users) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getNombre());
                row.createCell(2).setCellValue(user.getEmail());
                row.createCell(3).setCellValue(user.getTelefono());
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el reporte de Excel", e);
        }
    }
}