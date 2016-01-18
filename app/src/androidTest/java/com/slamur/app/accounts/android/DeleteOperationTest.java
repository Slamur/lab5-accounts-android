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
public class DeleteOperationTest extends ActivityInstrumentationTestCase2 {

    double value;
    Account source, target;
    Category income, expense;

    public DeleteOperationTest() {
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
    public void testDeleteIncome() {
        assertNotNull(target);
        double startBalance = target.getBalance();

        Operation incomeOperation = OperationBaseDao.getInstance(getActivity()).addIncomeOperation(
                Calendar.getInstance().getTime(),
                income, value, target, "income");

        OperationBaseDao.getInstance(getActivity()).removeOperation(incomeOperation.getId());

        double endBalance = target.getBalance();
        assertEquals("add income:", 0, endBalance - startBalance, 0.01);
    }

    @Test
    public void testDeleteExpense() {
        assertNotNull(source);
        double startBalance = source.getBalance();

        Operation expenseOperation = OperationBaseDao.getInstance(getActivity()).addExpenseOperation(
                Calendar.getInstance().getTime(),
                expense, value, source, "expense");

        OperationBaseDao.getInstance(getActivity()).removeOperation(expenseOperation.getId());

        double endBalance = source.getBalance();
        assertEquals("add expense:", 0, startBalance - endBalance, 0.01);
    }

    @Test
    public void testDeleteRemittance() {

        assertNotNull(source);
        double startBalanceSource = source.getBalance();

        assertNotNull(target);
        double startBalanceTarget = target.getBalance();

        Operation remittanceOperation = OperationBaseDao.getInstance(getActivity()).addRemittanceOperation(
                Calendar.getInstance().getTime(),
                value, source, target, "remittance");

        OperationBaseDao.getInstance(getActivity()).removeOperation(remittanceOperation.getId());

        double endBalanceSource = source.getBalance();
        double endBalanceTarget = target.getBalance();

        assertEquals(0, startBalanceSource - endBalanceSource, 0.01);
        assertEquals(0, endBalanceTarget - startBalanceTarget, 0.01);
    }
}
