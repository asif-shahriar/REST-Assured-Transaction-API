import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRunner {
    Transactions transactions;

    @Test(priority = 0, description = "Valid login")
    public void doLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.userLogin();
    }

    @Test(priority = 1, description = "Login with invalid email")
    public void invalidEmailLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.invalidEmail();
    }

    @Test(priority = 2, description = "Login with invalid password")
    public void invalidPassLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.invalidPassword();
    }

    @Test(priority = 3, description = "Show all user and customer list")
    public void displayList() throws IOException {
        transactions = new Transactions();
        transactions.callingList();
    }

    @Test(priority = 4, description = "Show customers of a particular role")
    public void displayByRole() throws IOException {
        transactions = new Transactions();
        transactions.listByRole();
    }

    @Test(priority = 5, description = "Search user by id")
    public void userSearch() throws IOException {
        transactions = new Transactions();
        transactions.searchUser();
    }

    @Test(priority = 6, description = "Generating random user from https://randomuser.me/api")
    public void userGenerate() throws ConfigurationException, IOException {
        transactions = new Transactions();
        transactions.generateUser();
    }

    @Test(priority = 7, description = "Adding the random generated user to the list")
    public void userCreate() throws IOException {
        transactions = new Transactions();
        transactions.createUser();
    }

    @Test(priority = 8, description = "Generating random user from https://randomuser.me/api")
    public void userGenerateAgain() throws ConfigurationException, IOException {
        transactions = new Transactions();
        transactions.generateUser();
    }

    @Test(priority = 9, description = "Creating an agent")
    public void agentCreate() throws IOException {
        transactions = new Transactions();
        transactions.createAgent();
    }

    @Test(priority = 10, description = "Updating a user info")
    public void userUpdate() throws IOException {
        transactions = new Transactions();
        transactions.updateUser();
    }

    @Test(priority = 11, description = "Show an agent balance")
    public void showBalance() throws IOException {
        transactions = new Transactions();
        transactions.agentBalance();
    }

    @Test(priority = 12, description = "Money deposit from SYSTEM")
    public void moneyDeposit() throws IOException {
        transactions = new Transactions();
        transactions.deposit();
    }

    @Test(priority = 13, description = "Deposit with insufficient balance")
    public void insufDepostit() throws IOException {
        transactions = new Transactions();
        transactions.depositInsufBalance();
    }

    @Test(priority = 14, description = "Checking user balance after deposit")
    public void balanceUser() throws IOException {
        transactions = new Transactions();
        transactions.userBalance();
    }

    @Test(priority = 15, description = "Sending money from one user to another")
    public void moneySend() throws IOException {
        transactions = new Transactions();
        transactions.sendMoney();
    }

    @Test(priority = 16, description = "Checking balance after sendmoney")
    public void balanceUser2() throws IOException {
        transactions = new Transactions();
        transactions.userBalance2();
    }

    @Test(priority = 17, description = "Cash out")
    public void moneyCashOut() throws IOException {
        transactions = new Transactions();
        transactions.cashOut();
    }

    @Test(priority = 18, description = "Show transaction list of a specific account")
    public void showTransactionAccount() throws IOException {
        transactions = new Transactions();
        transactions.showTransactionList();
    }

    @Test(priority = 19, description = "Show transaction list of a specific transaction id")
    public void showTransactionID() throws IOException {
        transactions = new Transactions();
        transactions.showTransactionListid();
    }

    @Test(priority = 20, description = "Show all transaction history")
    public void showAllTransactionID() throws IOException {
        transactions = new Transactions();
        transactions.showWholeTransactionList();
    }
}
