package com.mohyehia.onlinebanking.controllers;

import java.util.*;

import com.mohyehia.onlinebanking.utils.OnlineBankingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mohyehia.onlinebanking.entities.Account;
import com.mohyehia.onlinebanking.entities.User;
import com.mohyehia.onlinebanking.exceptions.AddAccountException;
import com.mohyehia.onlinebanking.services.framework.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController extends BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);
	private final AccountService accountService;
	private final MessageSource messageSource;
	
	@Autowired
	public AccountController(AccountService accountService, MessageSource messageSource) {
		this.accountService = accountService;
		this.messageSource = messageSource;
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
			throw new AddAccountException("Cannot add another account as there are 3 accounts for this user");
		}else {
			model.addAttribute("title", "Add new account");
			Set<String> accountTypes = new HashSet<>(Arrays.asList(OnlineBankingConstant.AC_ACCOUNT_TYPE, OnlineBankingConstant.AS_ACCOUNT_TYPE, OnlineBankingConstant.AT_ACCOUNT_TYPE));
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
	
	@PostMapping("/close")
	@ResponseBody
	public String closeAccount(@RequestParam("accountId") Long accountId) {
		LOG.info("Accessing function closeAccount with id =>" + accountId);
		Account account = accountService.findByIdAndUserId(accountId, getCurrentUser().getId());
		if(account == null){
			return messageSource.getMessage("ACCOUNT_NOT_BELONG_TO_USER", new Object[]{}, Locale.ENGLISH);
		}
		account.setActive(false);
		accountService.saveAccount(account);
		return "success";
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
		LOG.info(account.toString());
		List<String> errors = new ArrayList<>();
		if(account.getAccountType().equals(OnlineBankingConstant.AC_ACCOUNT_TYPE) && !account.getCreditType().equals(OnlineBankingConstant.AC_CREDIT_TYPE_DEBIT))
			errors.add("AC account type must be 'Debit'.");
		if(account.getAccountType().equals(OnlineBankingConstant.AT_ACCOUNT_TYPE) && !account.getCreditType().equals(OnlineBankingConstant.AT_CREDIT_TYPE_CREDIT))
			errors.add("AT account type must be 'Credit'.");
		if(account.getAccountType().equals(OnlineBankingConstant.AS_ACCOUNT_TYPE)) {
			if(!account.getCreditType().equals(OnlineBankingConstant.AC_CREDIT_TYPE_DEBIT) && !account.getCreditType().equals(OnlineBankingConstant.AT_CREDIT_TYPE_CREDIT))
				errors.add("AS account type must be Credit or Debit");
		}
		if(account.getBalance().intValue() < 500 || account.getBalance().intValue() > 5000)
			errors.add("Account balance must be between 500 and 5000 EGP.");
		return errors;
	}
}
