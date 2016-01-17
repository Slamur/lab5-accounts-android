package com.slamur.app.accounts.android.settings;

import android.content.Context;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.category.ExpenseCategory;
import com.slamur.app.accounts.model.category.IncomeCategory;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.library.daolibrary.android.settings.SettingsUtils;
import com.slamur.library.daolibrary.base.gson.GsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountsSettingsUtils extends SettingsUtils {

    protected static <ValueType> List<ValueType> asList(ValueType[] values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    public static final String ACCOUNT_DAO_KEY = "Account dao key", DEFAULT_ACCOUNT_DAO = GsonUtils.toJson(new Account[0]);
    public static final String INCOME_CATEGORY_DAO_KEY = "Income category dao key", DEFAULT_INCOME_CATEGORY_DAO = GsonUtils.toJson(new IncomeCategory[0]);
    public static final String EXPENSE_CATEGORY_DAO_KEY = "Expense category dao key", DEFAULT_EXPENSE_CATEGORY_DAO = GsonUtils.toJson(new ExpenseCategory[0]);
    public static final String OPERATION_DAO_KEY = "Operation dao key", DEFAULT_OPERATION_DAO = GsonUtils.toJson(new Operation[0]);

    public static List<Account> getAccounts(Context context) {
        String accountsString = getStringValue(context, ACCOUNT_DAO_KEY, DEFAULT_ACCOUNT_DAO);
        Account[] accounts = GsonUtils.fromJson(accountsString, Account[].class);
        return asList(accounts);
    }

    public static List<Category> getIncomeCategories(Context context) {
        String categoriesString = getStringValue(context, INCOME_CATEGORY_DAO_KEY, DEFAULT_INCOME_CATEGORY_DAO);
        Category[] categories = GsonUtils.fromJson(categoriesString, IncomeCategory[].class);
        return asList(categories);
    }

    public static List<Category> getExpenseCategories(Context context) {
        String categoriesString = getStringValue(context, EXPENSE_CATEGORY_DAO_KEY, DEFAULT_EXPENSE_CATEGORY_DAO);
        Category[] categories = GsonUtils.fromJson(categoriesString, ExpenseCategory[].class);
        return asList(categories);
    }

    public static List<Operation> getOperations(Context context) {
        String operationsString = getStringValue(context, OPERATION_DAO_KEY, DEFAULT_OPERATION_DAO);
        Operation[] operations = GsonUtils.fromJson(operationsString, Operation[].class);
        return asList(operations);
    }
}
