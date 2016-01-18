package com.slamur.app.accounts.android;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.slamur.app.accounts.android.activity.account.AccountListActivity;
import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.dao.category.CategoryBaseDao;
import com.slamur.app.accounts.android.dao.operation.OperationBaseDao;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.OperationType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class AddOperationTest extends ActivityInstrumentationTestCase2 {

    double value;
    Account source, target;
    Category income, expense;

    public AddOperationTest() {
        super(AccountListActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        List<Account> accounts = AccountBaseDao.getInstance(getActivity()).getItems();

        source = accounts.get(0);
        target = accounts.get(1);

        income = CategoryBaseDao.getInstance(getActivity()).filterCategoriesOfType(OperationType.INCOME).get(0);
        expense = CategoryBaseDao.getInstance(getActivity()).filterCategoriesOfType(OperationType.EXPENSE).get(0);

        value = 100500;
    }

    @Test
    public void testAddIncome() {
        assertNotNull(target);
        double startBalance = target.getBalance();

        Operation incomeOperation = OperationBaseDao.getInstance(getActivity()).addIncomeOperation(
                Calendar.getInstance().getTime(),
                income, value, target, "income");

        assertNotNull(incomeOperation);

        assertEquals(value, incomeOperation.getValue(), 0);
        assertEquals(target, incomeOperation.getTarget());
        assertEquals(income, incomeOperation.getCategory());

        double endBalance = target.getBalance();
        assertEquals("add income:", value, endBalance - startBalance, 0.01);

        OperationBaseDao.getInstance(getActivity()).removeOperation(incomeOperation.getId());
    }

    @Test
    public void testAddExpense() {
        assertNotNull(source);
        double startBalance = source.getBalance();

        Operation expenseOperation = OperationBaseDao.getInstance(getActivity()).addExpenseOperation(
                Calendar.getInstance().getTime(),
                expense, value, source, "expense");

        assertNotNull(expenseOperation);

        assertEquals(value, expenseOperation.getValue(), 0);
        assertEquals(source, expenseOperation.getSource());
        assertEquals(expense, expenseOperation.getCategory());

        double endBalance = source.getBalance();
        assertEquals("add expense:", value, startBalance - endBalance, 0.01);

        OperationBaseDao.getInstance(getActivity()).removeOperation(expenseOperation.getId());
    }

    @Test
    public void testAddRemittance() {

        assertNotNull(source);
        double startBalanceSource = source.getBalance();

        assertNotNull(target);
        double startBalanceTarget = target.getBalance();

        Operation remittanceOperation = OperationBaseDao.getInstance(getActivity()).addRemittanceOperation(
                Calendar.getInstance().getTime(),
                value, source, target, "remittance");

        assertNotNull(remittanceOperation);

        assertEquals(value, remittanceOperation.getValue(), 0);
        assertEquals(source, remittanceOperation.getSource());
        assertEquals(target, remittanceOperation.getTarget());

        double endBalanceSource = source.getBalance();
        double endBalanceTarget = target.getBalance();

        assertEquals(value, startBalanceSource - endBalanceSource, 0.01);
        assertEquals(value, endBalanceTarget - startBalanceTarget, 0.01);

        OperationBaseDao.getInstance(getActivity()).removeOperation(remittanceOperation.getId());
    }
}
