package com.microservice.ventas.web.service.ventas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ventas.persistence.entities.ventas.BillEntity;
import com.microservice.ventas.persistence.repository.ventas.BillRepository;

@Service
public class BillService {
    
    @Autowired
    BillRepository repositoryBill;
    // listar general
    public List<BillEntity> getBillListAll(){
        return (List<BillEntity>) repositoryBill.findAll();
    }
    //listar unidad
    public Optional<BillEntity> getBillById(Long id){
        return (Optional<BillEntity>) repositoryBill.findById(id);
    }
    //guardar sumando valores en total
    public Boolean save(Double v_Envio, Double v_Pro, BillEntity b){
        //definir valor antes de indexar en la db
        b.setValor_total(v_Envio + v_Pro);
        //verificamos obj
        BillEntity bInsertEnt = repositoryBill.save(b);
        if(bInsertEnt != null){
            return true;
        }else{
            return false;
        } 
    }
}
