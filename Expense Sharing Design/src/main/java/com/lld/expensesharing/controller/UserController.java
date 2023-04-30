package com.lld.expensesharing.controller;

import com.lld.expensesharing.exception.ContributionExceededException;
import com.lld.expensesharing.exception.ExpenseSettledException;
import com.lld.expensesharing.exception.InvalidExpenseStateException;
import com.lld.expensesharing.model.Contribution;
import com.lld.expensesharing.model.User;
import com.lld.expensesharing.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{email}/{name}/{phoneNumber}")
    public ResponseEntity<User> createUser(@PathVariable String email, @PathVariable String name, @PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.createUser(email, name, phoneNumber));
    }

    @PostMapping("/{emailId}/contibute/expense/{expenseId}")
    public ResponseEntity<Void> contibuteToExpense(@PathVariable String emailId, @PathVariable String expenseId, @RequestBody Contribution contribution) throws ExpenseSettledException, InvalidExpenseStateException, ContributionExceededException {
        userService.contributeToExpense(expenseId, emailId, contribution);
        return ResponseEntity.ok().build();
        //return new ResponseEntity<>(HttpStatus.OK);
    }

}
