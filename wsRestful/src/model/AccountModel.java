package model;

import java.util.ArrayList;
import java.util.List;
import entities.Account;

public class AccountModel {

	private List<Account> accounts;

	public AccountModel() {
		this.accounts = new ArrayList<Account>();
		this.accounts.add(new Account("acc1", "123", new String[] { "superadmin", "admin", "employee" }));
		this.accounts.add(new Account("acc2", "123", new String[] { "admin", "employee" }));
		this.accounts.add(new Account("acc3", "123", new String[] { "employee" }));
	}

	public Account login(String username, String password) {
		for (Account account : this.accounts) {
			if (account.getUsername().equalsIgnoreCase(username) && account.getPassword().equalsIgnoreCase(password)) {
				return account;
			}
		}
		return null;
	}

}