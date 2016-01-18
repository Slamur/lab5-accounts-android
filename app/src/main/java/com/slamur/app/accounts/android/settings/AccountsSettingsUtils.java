package com.slamur.app.accounts.android.settings;

import android.content.Context;

import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
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
    public static final String CATEGORY_DAO_KEY = "Income category dao key", DEFAULT_CATEGORY_DAO = GsonUtils.toJson(new Category[0]);
    public static final String OPERATION_DAO_KEY = "Operation dao key", DEFAULT_OPERATION_DAO = GsonUtils.toJson(new Operation[0]);

    public static List<Account> getAccounts(Context context) {
        String accountsString = getStringValue(context, ACCOUNT_DAO_KEY, DEFAULT_ACCOUNT_DAO);
        Account[] accounts = GsonUtils.fromJson(accountsString, Account[].class);
        return asList(accounts);
    }

    public static List<Category> getCategories(Context context) {
        String categoriesString = getStringValue(context, CATEGORY_DAO_KEY, DEFAULT_CATEGORY_DAO);
        Category[] categories = GsonUtils.fromJson(categoriesString, Category[].class);
        return asList(categories);
    }

    public static List<Operation> getOperations(Context context) {
        String operationsString = getStringValue(context, OPERATION_DAO_KEY, DEFAULT_OPERATION_DAO);
        Operation[] operations = GsonUtils.fromJson(operationsString, Operation[].class);
        return asList(operations);
    }
}
