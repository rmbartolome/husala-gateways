package com.musala.gateways.controller;

import com.musala.gateways.exception.ResourceNotFoundException;
import com.musala.gateways.model.Device;
import com.musala.gateways.model.Gateway;
import com.musala.gateways.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rbartolome on 22/07/19
 */
@RestController
@RequestMapping("/api")
public class GatewayController {

    @Autowired
    GatewayRepository gatewayRepository;

    @GetMapping("/gateways")
    public ResponseEntity<?> getAllGateways() {
        return new ResponseEntity<>(gatewayRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/gateway")
    public ResponseEntity<?> createGateway(@Valid @RequestBody Gateway gateway) {
        if(gateway.getDevices().size()<=10){
            gateway = gatewayRepository.save(gateway);
            return new ResponseEntity<>(gateway, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error don't save more of ten peripherals", HttpStatus.CONFLICT);
    }

    @GetMapping("/gateway/{id}")
    public Gateway getGatewayById(@PathVariable(value = "id") Long gatewayId) {
        return gatewayRepository.findById(gatewayId)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway", "id", gatewayId));
    }

    @PutMapping("/gateway/{id}")
    public Gateway updateGateway(@PathVariable(value = "id") Long gatewayId,
                                 @Valid @RequestBody Gateway gatewayDetails) {

        Gateway gateway = gatewayRepository.findById(gatewayId)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway", "id", gatewayId));

        gateway.setName(gatewayDetails.getName());
        gateway.setAdddress(gatewayDetails.getAdddress());
        gateway.setDevices(gatewayDetails.getDevices());

        Gateway updatedGateway = gatewayRepository.save(gateway);
        return updatedGateway;
    }

    @DeleteMapping("/gateway/{id}")
    public ResponseEntity<?> deleteGateway(@PathVariable(value = "id") Long gatewayId) {
        Gateway gateway = gatewayRepository.findById(gatewayId)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway", "id", gatewayId));

        gatewayRepository.delete(gateway);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/device")
    public ResponseEntity<?> deleteGateway(@RequestParam(value = "gatewayId") Long gatewayId,
                                           @RequestParam(value = "nameDevice") String name) {
        Gateway gateway = gatewayRepository.findById(gatewayId)
                .orElseThrow(() -> new ResourceNotFoundException("Gateway", "id", gatewayId));

        for(Device deviceDelete : gateway.getDevices()){
            if(deviceDelete.getVendor().equals(name)){
                gateway.getDevices().remove(deviceDelete);
                break;
            }
        }
        Gateway updatedGateway = gatewayRepository.save(gateway);
        return new ResponseEntity<>(updatedGateway, HttpStatus.OK);
    }
}
