package com.tma.coffeehouse.Cart;

import com.tma.coffeehouse.Cart.DTO.CartDTO;
import com.tma.coffeehouse.Cart.DTO.CheckOutInfoDTO;
import com.tma.coffeehouse.Cart.DTO.GetFullCartDTO;
import com.tma.coffeehouse.CartDetails.CartDetail;
import com.tma.coffeehouse.CartDetails.DTO.AddCartDetailDTO;
import com.tma.coffeehouse.Order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetFullCartDTO findone (@PathVariable Long customerId){
        return cartService.findOne(customerId);
    }
    @PostMapping("/detail")
    @ResponseStatus(HttpStatus.CREATED)
    public CartDetail insertCartDetail(@RequestBody AddCartDetailDTO detail){
        return cartService.insertCartDetail(detail);
    }
    @PostMapping("/checkout/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order checkOutCart(@PathVariable Long id, @RequestBody CheckOutInfoDTO checkOutInfoDTO){
        return cartService.checkOutCart(id, checkOutInfoDTO);
    }
    @DeleteMapping("/detail/{id}")
    public CartDetail deleteCartDetail(@PathVariable Long id){
        return cartService.deleteCartDetail(id);
    }
    @PostMapping("/addvoucher/{id}")
    public CartDTO addVoucher(@PathVariable Long id, @RequestBody Long voucherId){
        return cartService.addVoucher(id, voucherId);
    }
    @DeleteMapping("/deletevoucher/{id}")
    public CartDTO addVoucher(@PathVariable Long id){
        return cartService.deleteVoucher(id);
    }
}
