package com.example.demo.controller;
import com.example.demo.model.Account;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/api")
public class APIController {





    @GetMapping("/{id}")
    public Float getInfoById(@PathVariable String id) throws SQLException {
        Account account1 = new Account();
        float amount = account1.findInfoById(id);
        return amount;
    }


  @PostMapping("/createUser")
    public String createUser(@RequestBody Account account ) throws SQLException {
        // Here you would normally process the user, e.g., save to date

       Account account1 = new Account(account.getAccountHolder(),account.getBalance());
        account1.saveToDb(account1);

        return "User " + account.getAccountHolder() + " with balance " + account.getBalance() +" inserted";
    }


}
