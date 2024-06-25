package com.fotograbados.springv1.web.report;

import com.fotograbados.springv1.domain.report.ListarOrderUserExcel;
import com.fotograbados.springv1.domain.report.ListarUsuariosExcel;
import com.fotograbados.springv1.domain.report.ListarUsuariosPdf;
import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.report.ExcelOrderService;
import com.fotograbados.springv1.domain.service.report.ExcelUserService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelOrderService excelOrderService;
    @Autowired
    ExcelUserService excelUserService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("/excel")
    public ResponseEntity<byte[]> getOrdersExcel() {
        List<OrderEntity> orders = orderService.findAll();
        ListarOrderUserExcel listarOrderUserExcel = new ListarOrderUserExcel(orders);
        byte[] excelBytes = excelOrderService.generateExcel(listarOrderUserExcel);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=orders.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok().headers(headers).body(excelBytes);
    }

    @GetMapping("/users")
    public ResponseEntity<byte[]> getUsersExcel() {
        List<Users> users = usuarioService.findAll();
        ListarUsuariosExcel listarUsuariosExcel = new ListarUsuariosExcel(users);
        byte[] excelBytes = excelUserService.generateExcel(listarUsuariosExcel);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=users.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok().headers(headers).body(excelBytes);
    }
}
