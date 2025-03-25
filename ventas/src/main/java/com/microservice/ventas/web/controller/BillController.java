package com.microservice.ventas.web.controller;

import java.text.ParsePosition;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ventas.persistence.entities.ventas.BillEntity;
import com.microservice.ventas.web.service.ventas.BillService;

@RestController
public class BillController {
    /**
     * metodos a devolver en.. VENTAS- factura
     * registrar
     * listar general
     * listar unidadd
     * calcular valor total de venta (producto, envio, ¿iva?)
     */
    @Autowired
    BillService serviceBill;

    @GetMapping("/bills")
    public void getBillListAll(){
        try {
            serviceBill.getBillListAll();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se hizo la consulta, verifique problemas con la db, lee la excepcion");
        }finally{
            System.out.println("Evento cerrado");
        }
    }

    @GetMapping("/bills/{id}")
    public void getBillById(@PathVariable Long id){
        try {
            serviceBill.getBillById(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("No se hizo la consulta, verifique problemas con la db, lee la excepcion");
        }finally{
            System.out.println("Evento cerrado");
        }
    }

    @PostMapping("/save/bill")
    public Boolean saveBill(@RequestParam Double v_pro, @RequestParam Double v_envio, @RequestBody BillEntity b){
        return serviceBill.save(v_envio, v_pro, b);
    }
}
