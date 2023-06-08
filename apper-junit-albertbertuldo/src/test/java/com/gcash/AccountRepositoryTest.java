package com.gcash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountRepositoryTest {

    @Test
    void successfulAccountCreation() {
        //Setup
        AccountRepository repository = new AccountRepository();

        //Kick
        String accountId = repository.createAccount("Albert", 89.9 );

        //Verify
        Assertions.assertEquals(1, repository.getNumberOfAccounts());
        Assertions.assertEquals("Albert", repository.getAccount(accountId).name());
        Assertions.assertNotNull(accountId);
    }

    @Test
    void successfulGetAccount() {
        AccountRepository repository = new AccountRepository();
        String accountId = repository.createAccount("Albert", 89.9);

        Assertions.assertEquals("Albert", repository.getAccount(accountId).name());
        Assertions.assertEquals(89.9, repository.getAccount(accountId).balance());
        Assertions.assertNull(repository.getAccount("randomid"));

    }

    @Test
    void successfulDeleteAccount() {
        //Setup
        AccountRepository repository = new AccountRepository();
        String id = repository.createAccount("Albert", 89.9);

        //Kick
        repository.deleteAccount(id);

        //Verify
        Assertions.assertEquals(0, repository.getNumberOfAccounts());
    }

    @Test
    void successfulGetNumberOfAccounts() {
        //Setup
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
}