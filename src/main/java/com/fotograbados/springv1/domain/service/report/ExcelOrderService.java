package com.fotograbados.springv1.domain.service.report;

import com.fotograbados.springv1.domain.report.ListarOrderUserExcel;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelOrderService {
    public byte[] generateExcel(ListarOrderUserExcel listarOrderUserExcel) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Reporte de Ventas");

            // Crear y agregar el título
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Reporte de Ventas");
            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 18);
            titleStyle.setFont(titleFont);
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

            // Crear una fila para los encabezados
            Row headerRow = sheet.createRow(1);
            String[] headers = {"ID", "Fecha Venta", "Fecha Envío", "Total"};
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
            List<OrderEntity> orders = listarOrderUserExcel.getOrderEntities();
            int rowIndex = 2;
            for (OrderEntity order : orders) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(order.getIdOrder());
                row.createCell(1).setCellValue(order.getFechaVenta());
                row.createCell(2).setCellValue(order.getFechaEnvio());
                row.createCell(3).setCellValue(order.getTotal());
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el reporte de Excel", e);
        }
    }
}
