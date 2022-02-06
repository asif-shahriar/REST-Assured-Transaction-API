import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestRunner {
    Transactions transactions;

    @Test(enabled = false, description = "Valid login")
    public void doLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.userLogin();
    }

    @Test(enabled = false, description = "Login with invalid email")
    public void invalidEmailLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.invalidEmail();
    }

    @Test(enabled = false, description = "Login with invalid password")
    public void invalidPassLogin() throws IOException, ConfigurationException {
        transactions = new Transactions();
        transactions.invalidPassword();
    }

    @Test(enabled = false, description = "Show all user and customer list")
    public void displayList() throws IOException {
        transactions = new Transactions();
        transactions.callingList();
    }

    @Test(enabled = false, description = "Show customers of a particular role")
    public void displayByRole() throws IOException {
        transactions = new Transactions();
        transactions.listByRole();
    }

    @Test(enabled = false, description = "Search user by id")
    public void userSearch() throws IOException {
        transactions = new Transactions();
        transactions.searchUser();
    }

    @Test(enabled = false, description = "Generating random user from https://randomuser.me/api")
    public void userGenerate() throws ConfigurationException, IOException {
        transactions = new Transactions();
        transactions.generateUser();
    }

    @Test(enabled = false, description = "Adding the random generated user to the list")
    public void userCreate() throws IOException {
        transactions = new Transactions();
        transactions.createUser();
    }

    @Test(enabled = false, description = "Creating an agent")
    public void agentCreate() throws IOException {
        transactions = new Transactions();
        transactions.createAgent();
    }

    @Test(enabled = false, description = "Updating a user info")
    public void userUpdate() throws IOException {
        transactions = new Transactions();
        transactions.updateUser();
    }

    @Test(enabled = false, description = "Show an agent balance")
    public void showBalance() throws IOException {
        transactions = new Transactions();
        transactions.agentBalance();
    }

    @Test(enabled = false, description = "Money deposit from SYSTEM")
    public void moneyDeposit() throws IOException {
        transactions = new Transactions();
        transactions.deposit();
    }

    @Test(enabled = false, description = "Deposit with insufficient balance")
    public void insufDepostit() throws IOException {
        transactions = new Transactions();
        transactions.depositInsufBalance();
    }

    @Test(enabled = false, description = "Checking user balance after deposit")
    public void balanceUser() throws IOException {
        transactions = new Transactions();
        transactions.userBalance();
    }

    @Test(enabled = false, description = "Sending money from one user to another")
    public void moneySend() throws IOException {
        transactions = new Transactions();
        transactions.sendMoney();
    }

    @Test(enabled = false, description = "Checking balance after sendmoney")
    public void balanceUser2() throws IOException {
        transactions = new Transactions();
        transactions.userBalance2();
    }

    @Test(enabled = false, description = "Cash out")
    public void moneyCashOut() throws IOException {
        transactions = new Transactions();
        transactions.cashOut();
    }

    @Test(enabled = false, description = "Show transaction list of a specific account")
    public void showTransactionAccount() throws IOException {
        transactions = new Transactions();
        transactions.showTransactionList();
    }

    @Test(enabled = false, description = "Show transaction list of a specific transaction id")
    public void showTransactionID() throws IOException {
        transactions = new Transactions();
        transactions.showTransactionListid();
    }

    @Test(enabled = false, description = "Show all transaction history")
    public void showAllTransactionID() throws IOException {
        transactions = new Transactions();
        transactions.showWholeTransactionList();
    }
}
