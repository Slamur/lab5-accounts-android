package com.slamur.app.accounts.android;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.slamur.app.accounts.android.activity.account.AccountListActivity;
import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.dao.operation.OperationBaseDao;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.Operation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class DeleteAccountTest extends ActivityInstrumentationTestCase2 {

    final static int SOURCE_INDEX = 0, TARGET_INDEX = 1;
    Account source, target;
    Category income, expense;

    public DeleteAccountTest() {
        super(AccountListActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        List<Account> accounts = AccountBaseDao.getInstance(getActivity()).getItems();

        source = accounts.get(SOURCE_INDEX);
        target = accounts.get(TARGET_INDEX);
    }

    @Test
    public void testDeleteAccount() {

        assertNotNull(source);
        double startBalanceSource = source.getBalance();

        assertNotNull(target);
        double startBalanceTarget = target.getBalance();

        List<Operation> operations = OperationBaseDao.getInstance(getActivity()).filterByAccount(source);

        List<Boolean> wasSourses = new ArrayList<>();
        for (Operation operation : operations) {
            wasSourses.add(operation.getSource() == source);
        }

        List<Boolean> wasTargets = new ArrayList<>();
        for (Operation operation : operations) {
            wasTargets.add(operation.getTarget() == source);
        }

        AccountBaseDao.getInstance(getActivity()).removeAccount(SOURCE_INDEX, target);

        double endBalanceSource = source.getBalance();
        double endBalanceTarget = target.getBalance();

        for (Operation operation : operations) {
            assertNotSame(source, operation.getSource());
            assertNotSame(source, operation.getTarget());
        }

        assertEquals(0, OperationBaseDao.getInstance(getActivity()).filterByAccount(source).size());

        for (int index = 0; index < operations.size(); ++index) {
            if (wasSourses.get(index)) {
                assertEquals(target, operations.get(index).getSource());
            }

            if (wasTargets.get(index)) {
                assertEquals(target, operations.get(index).getTarget());
            }
        }

        AccountBaseDao.getInstance(getActivity()).addAccount(source.getName(), source.getBalance(), source.getDescription());
    }
}
