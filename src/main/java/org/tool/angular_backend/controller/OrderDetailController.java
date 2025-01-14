package org.tool.angular_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tool.angular_backend.dto.OrderDetailDTO;


@RestController
@RequestMapping("${api.prefix}/order_detail")
public class OrderDetailController {


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("getOrderDetail with id " + id);
    }

    @PostMapping("")
    public ResponseEntity<?> insertOrderDetail(@Valid @RequestBody OrderDetailDTO newOrderDetailDTO) {
        return ResponseEntity.ok("Create OrderDtail here");
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok("getOrderDetails with orderId = " + orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@Valid @PathVariable("id") Long id, @RequestBody OrderDetailDTO newOrderDetailData) {

        return ResponseEntity.ok("Update OrderDetail with id = " + id + " , newOrderDetailData : " + newOrderDetailData);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
