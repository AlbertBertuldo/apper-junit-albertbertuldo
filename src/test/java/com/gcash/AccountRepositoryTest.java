package com.gcash;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        // Setup
        AccountRepository repository = new AccountRepository();

        // Kick
        String accountId = repository.createAccount("Albert", 89.9);

        // Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("Albert", repository.getAccount(accountId).getName());
        Assertions.assertNotNull(accountId);
    }

    @Test
    void successfulGetAccount() {
        AccountRepository repository = new AccountRepository();

        String accountId = repository.createAccount("Albert", 89.9);

        Assertions.assertEquals("Albert", repository.getAccount(accountId).getName());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).getBalance());
        Assertions.assertNull(repository.getAccount("randomid"));
    }

    @Test
    void successfulDelete() {
        //Setup
        AccountRepository repository = new AccountRepository();
        String id = repository.createAccount("Albert", 89.9);

        //Kick
        repository.deleteAccount(id);

        //Verify
        Assertions.assertEquals(0, repository.getNumberOfAccounts());
        Assertions.assertNull(repository.getAccount(id));
    }

    @Test
    void successfulGetNumberOfAccounts() {
        //Setup and kick
        AccountRepository repository = new AccountRepository();
        String id0 = repository.createAccount("Albert", 89.9);
        String id1 = repository.createAccount("Albert", 89.9);
        String id2 = repository.createAccount("Albert", 89.9);
        String id3 = repository.createAccount("Albert", 89.9);

        //Verify
        Assertions.assertEquals(4, repository.getNumberOfAccounts());

        //Setup
        repository.deleteAccount(id0);

        //Verify
        Assertions.assertEquals(3, repository.getNumberOfAccounts());
    }
    @Test
    public void testNoRegisteredAccount() {
        AccountRepository repository = new AccountRepository();

        // Check if there are no registered accounts
        Assertions.assertTrue(repository.noRegisteredAccount());

        // Create an account
        String id = repository.createAccount("John Albert", 100.00);

        // Check if there is now one registered account
        Assertions.assertFalse(repository.noRegisteredAccount());
    }

    @Test
    public void testSetBalance() {
        AccountRepository accountRepository = new AccountRepository();
        String accountId = accountRepository.createAccount("John Albert", 100.0);
        Account account = accountRepository.getAccount(accountId);
        Assertions.assertNotNull(account);

        account.setBalance(150.0);
        Assertions.assertEquals(150.0, account.getBalance(), 0.001);
    }
}