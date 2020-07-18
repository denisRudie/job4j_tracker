package ru.job4j.tracker.stream.bank;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BankServiceTest {

    @Test
    public void addUser() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("3434"), is(Optional.of(user)));
    }

    @Test
    public void addAccount() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150.0));
        Assert.assertEquals(bank.findByRequisite("3434", "5546").get().getBalance(), 150, 0.01);
    }

    @Test
    public void addAccountCheckNull() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150.0));
        Assert.assertThat(bank.findByRequisite("3433", "5546"), is(Optional.empty()));
    }

    @Test
    public void transferMoney() {
        User user = new User("3434", "Petr Arsentev");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("5546", 150));
        bank.addAccount(user.getPassport(), new Account("113", 50));
        bank.transferMoney(user.getPassport(), "5546", user.getPassport(), "113", 150);
        Assert.assertEquals(bank.findByRequisite("3434", "113").get().getBalance(), 200, 0.01);
    }

    @Test
    public void nullWhenAbsentUser() {
        BankService bank = new BankService();
        Assert.assertThat(bank.findByPassport("123"), is(Optional.empty()));
    }
}
