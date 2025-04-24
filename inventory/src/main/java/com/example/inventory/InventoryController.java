package com.example.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @GetMapping("/check/{amount}")
    public boolean checkInventory(@PathVariable  int amount) {
        // Simulate checking inventory
        if (amount <= 5) {
            System.out.println("Amount is fulfilled");
            return true;
        }
        else {
            System.out.println("Insufficient Inventory");
            return false;
        }
    }
}
