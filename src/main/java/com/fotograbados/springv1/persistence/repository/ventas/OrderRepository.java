package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.domain.dto.*;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUsers(Users users);

            //Sales for Region
    @Query(nativeQuery = true,
            value = "SELECT r.region_name AS region_name, u.tipo AS customer_type, u.codigo_postal AS postal_code, COUNT(*) AS customer_count " +
                    "FROM users u " +
                    "JOIN regions r ON u.direccion LIKE CONCAT('%', r.region_name, '%') " +
                    "GROUP BY r.region_name, u.tipo, u.codigo_postal ")
    List<CustomerTypePerRegionWithPostalCodeDTO> findCustomerTypesPerRegionWithPostalCode();


}
