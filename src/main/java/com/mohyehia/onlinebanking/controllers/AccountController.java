package com.mohyehia.onlinebanking.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.services.framework.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	private final AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping
	public String viewAccounts(Model model) {
		model.addAttribute("title", "Accounts");
		List<Account> accounts = getUserAccounts();
		LOG.info("Accounts size =>" + accounts.size());
		model.addAttribute("accountsSize", accounts.size());
		model.addAttribute("accounts", accounts);
		return "accounts/index";
	}
	
	@GetMapping("/add")
	public String viewAddAccount(Model model) {
		List<Account> accounts = getUserAccounts();
		if(accounts.size() == 3) {
			LOG.info("Found 3 accounts in db. redirecting to the error page");
			return "errors/addAccountError";
		}else {
			model.addAttribute("title", "Add new account");
			Set<String> accountTypes = new HashSet<>(Arrays.asList("AC", "AT", "AS"));
			accounts.forEach(account -> accountTypes.remove(account.getAccountType()));
			LOG.info("Available account types =>" + accountTypes.size());
			model.addAttribute("accountTypes", accountTypes);
			return "accounts/add";
		}	
	}
	
	@PostMapping("/add")
	public String saveAccount(Model model, RedirectAttributes attributes, @ModelAttribute("account") Account account) {
		List<String> errors = validateAccount(account);
		if(!errors.isEmpty()) {
			attributes.addFlashAttribute("errors", errors);
			return "redirect:/accounts/add";
		}else {
			account.setUserId(getCurrentUser().getId());
			LOG.info("Account => " + account);
			account = accountService.saveAccount(account);
			if(account == null) {
				LOG.error("account not added!");
				attributes.addFlashAttribute("error", "User has 3 accounts already and cannot add new account!");
				return "redirect:/accounts/add";
			}else {
				LOG.info(account + " added successfully!");
				attributes.addFlashAttribute("success", "New account added successfully!");
				return "redirect:/accounts";
			}
		}
	}
	
	@GetMapping("/{accountId}/edit")
	public String viewEditAccount(@PathVariable Long accountId) {
		return "accounts/edit";
	}
	
	@GetMapping("/{accountId}/delete")
	public String viewDeleteAccount(@PathVariable Long accountId) {
		return "accounts/delete";
	}
	
	@ModelAttribute("user")
	public User getPrincipal() {
		return getCurrentUser();
	}
	
	@ModelAttribute("account")
	public Account getAccount() {
		return new Account();
	}
	
	@ModelAttribute("accounts")
	public List<Account> getUserAccounts(){
		return accountService.findByUserId(getCurrentUser().getId());
	}
	
	private List<String> validateAccount(Account account){
		List<String> errors = new ArrayList<>();
		if(account.getAccountType().equals("AC") && !account.getCreditType().equals("Credit".trim()))
			errors.add("AC account type can not be Credit.");
		if(account.getAccountType().equals("AT") && !account.getCreditType().equals("Debit".trim()))
			errors.add("AT account type can not be Debit.");
		if(account.getAccountType().equals("AS")) {
			if(!account.getCreditType().equals("Credit".trim()) && !account.getCreditType().equals("Debit".trim()))
				errors.add("AS account type must be Credit or Debit");
		}
		if(account.getBalance().intValue() < 500 || account.getBalance().intValue() > 5000)
			errors.add("Account balance must be between 500 and 5000 EGP.");
		return errors;
	}
}
