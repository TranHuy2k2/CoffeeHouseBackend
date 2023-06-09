package com.tma.coffeehouse.Cart.DTO;

import com.tma.coffeehouse.Customers.Customer;
import com.tma.coffeehouse.Voucher.Voucher;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private String note = "";
    private Customer customer;
    private Voucher voucher;
    Timestamp createdAt;
    Timestamp updatedAt;

}
