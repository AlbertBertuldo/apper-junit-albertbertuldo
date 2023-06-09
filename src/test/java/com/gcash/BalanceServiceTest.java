package com.gcash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BalanceServiceTest {

    private BalanceService balanceService;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
        balanceService = new BalanceService(accountRepository);
    }

    @Test
    void testGetBalance() {
        // Create an account
        String accountId = accountRepository.createAccount("Albert Bertuldo", 100.0);

        // Test getBalance
        Double balance = balanceService.getBalance(accountId);
        Assertions.assertEquals(100.0, balance);
    }
    @Test

    void testGetBalanceForNonexistentAccount() {
        // Test getBalance for a non-existent account
        Double balance = balanceService.getBalance("nonexistent-account");
        Assertions.assertNull(balance);
    }

    @Test
    void testDebit() {
        // Create an account
        String accountId = accountRepository.createAccount("John Bertuldo", 100.0);

        // Test debit
        balanceService.debit(accountId, 50.0);
        Double newBalance = balanceService.getBalance(accountId);
        Assertions.assertEquals(50.0, newBalance);
    }

    @Test
    void testCredit() {
        // Create an account
        String accountId = accountRepository.createAccount("Bertuldo Albert", 100.0);

        // Test credit
        balanceService.credit(accountId, 50.0);
        Double newBalance = balanceService.getBalance(accountId);
        Assertions.assertEquals(150.0, newBalance);
    }

    @Test
    void testTransfer() {
        // Create source account
        String sourceAccountId = accountRepository.createAccount("Albert", 100.0);

        // Create destination account
        String destinationAccountId = accountRepository.createAccount("John", 50.0);

        // Test transfer
        balanceService.transfer(sourceAccountId, destinationAccountId, 50.0);

        Double sourceBalance = balanceService.getBalance(sourceAccountId);
        Double destinationBalance = balanceService.getBalance(destinationAccountId);

        Assertions.assertEquals(50.0, sourceBalance);
        Assertions.assertEquals(100.0, destinationBalance);
    }
    @Test
    void testDeleteAccount() {
        // Create an account
        String accountId = accountRepository.createAccount("Testing Albert Bertuldo", 200.0);

        // Delete the account
        accountRepository.deleteAccount(accountId);

        // Try to retrieve the deleted account
        Account deletedAccount = accountRepository.getAccount(accountId);

        Assertions.assertNull(deletedAccount);

    }

    @Test
    void testGetNumberOfAccounts() {
        // Create some accounts
        accountRepository.createAccount("Account 1", 100.0);
        accountRepository.createAccount("Account 2", 200.0);
        accountRepository.createAccount("Account 3", 300.0);

        // Get the number of accounts
        int numberOfAccounts = accountRepository.getNumberOfAccounts();

        Assertions.assertEquals(3, numberOfAccounts);
    }

    @Test
    void testNoRegisteredAccount() {
        // Initially, there should be no registered accounts
        boolean noRegisteredAccount = accountRepository.noRegisteredAccount();

        Assertions.assertTrue(noRegisteredAccount);

        // Create an account
        accountRepository.createAccount("Test Albert", 200.0);

        // After creating an account, there should be registered accounts
        noRegisteredAccount = accountRepository.noRegisteredAccount();

        Assertions.assertFalse(noRegisteredAccount);
    }

    @Test
    void testAccountGetName() {
        // Create an account
        String accountId = accountRepository.createAccount("Albert Test", 200.0);

        // Get the account's name
        Account account = accountRepository.getAccount(accountId);
        String name = account.getName();

        Assertions.assertEquals("Albert Test", name);
    }
}