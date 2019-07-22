package com.musala.gateways.repository;

import com.musala.gateways.model.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rbartolome on 22/07/19
 */
@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {

}
