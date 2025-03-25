package com.microservice.ventas.web.service.ventas;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservice.ventas.persistence.entities.ventas.OrderEntity;
import com.microservice.ventas.persistence.repository.ventas.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository repositoryOrder;
    //listar genreral
    public List<OrderEntity> getOrderListAll(){
        return (List<OrderEntity>) repositoryOrder.findAll();
    }
    //listar unidad
    public Optional<OrderEntity> getOrderById(Long id){
        return (Optional<OrderEntity>) repositoryOrder.findById(id);
    }
    //save()
    public Boolean save(OrderEntity o){
        if(o != null){
            repositoryOrder.save(o);
            return true;
        }else{
            return false;
        }
    }    
    //actualizar unicamente f_envio
    public Boolean editF_envio(Date d_current, Long id){
        //validated
        List<OrderEntity> o = repositoryOrder.updateTransportationSent(d_current, id);
        //test basic
        if(o != null){
            return true;
        } else{
            return false;
        }
    } 

    //delete
    public void deleteOrder(OrderEntity o){
        repositoryOrder.delete(o);
    }

}
