package com.example.springinitdemo;

import com.example.springinitdemo.models.User;
import com.example.springinitdemo.services.AccountService;
import com.example.springinitdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    @Transactional // всички заявки в този метод да се изпълнят в една транзакция
    public void run(String... args) throws Exception {
        // this.userService.register("first", 20);

        User user = this.userService.findByUsername("first");
        this.accountService.depositMoney(BigDecimal.TEN, user.getAccountIds().get(0));
        this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccountIds().get(0));

    }
}
