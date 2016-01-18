package com.slamur.app.accounts.android.activity.operation;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.slamur.app.accounts.android.R;
import com.slamur.app.accounts.android.dao.account.AccountBaseDao;
import com.slamur.app.accounts.android.dao.category.CategoryBaseDao;
import com.slamur.app.accounts.android.dao.operation.OperationActivityDao;
import com.slamur.app.accounts.android.dao.operation.OperationBaseDao;
import com.slamur.app.accounts.android.provider.operation.OperationActivityProvider;
import com.slamur.app.accounts.model.account.Account;
import com.slamur.app.accounts.model.category.Category;
import com.slamur.app.accounts.model.operation.Operation;
import com.slamur.app.accounts.model.operation.OperationType;
import com.slamur.library.daolibrary.android.AndroidUtils;
import com.slamur.library.daolibrary.android.activity.info.InfoItemActivityImpl;
import com.slamur.library.daolibrary.android.utils.DatetimeUtils;
import com.slamur.library.daolibrary.android.utils.SpinnerUtils;
import com.slamur.library.daolibrary.base.event.Event;
import com.slamur.library.daolibrary.base.provider.event.ItemLoadEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OperationInfoActivity extends InfoItemActivityImpl<Operation, OperationActivityDao, OperationActivityProvider>
implements OperationActivity {

    protected Spinner mTypeView;

    protected Date mDate;
    protected TextView mDateView;
    protected View mDateChooserView;

    protected EditText mValueView;

    protected Spinner mSourceView;
    protected Spinner mTargetView;

    protected EditText mDescriptionView;

    @Override
    protected void onInfoActivityCreate(Bundle savedInstanceState, int actionType) {
        mTypeView = AndroidUtils.findViewById(this, getTypeViewId());
        initTypeView();

        mDateView = AndroidUtils.findViewById(this, getDateViewId());

        mDateChooserView = AndroidUtils.findViewById(this, getDateChooserViewId());
        initDateChooserView();

        mValueView = AndroidUtils.findViewById(this, getValueViewId());
        mValueView.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mSourceView = AndroidUtils.findViewById(this, getSourceViewId());
        mTargetView = AndroidUtils.findViewById(this, getTargetViewId());

        mDescriptionView = AndroidUtils.findViewById(this, getDescriptionViewId());
    }

    protected void initTypeView() {
        ArrayAdapter<OperationType> typeAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(this, OperationType.values());

        mTypeView.setAdapter(typeAdapter);
        mTypeView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((OperationType) parent.getSelectedItem()) {
                    case INCOME:
                        setIncomeMode();
                        return;
                    case EXPENSE:
                        setExpenseMode();
                        return;
                    case REMITTANCE:
                        setReminttanceMode();
                        return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setIncomeMode();
            }
        });
    }

    protected void setIncomeMode() {
        ArrayAdapter<Category> incomeAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(
                this, CategoryBaseDao.getInstance(this).filterCategoriesOfType(OperationType.INCOME).toArray(new Category[0])
        );

        mSourceView.setAdapter(incomeAdapter);

        ArrayAdapter<Account> accountAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(
                this, AccountBaseDao.getInstance(this).getItems().toArray(new Account[0])
        );

        mTargetView.setAdapter(accountAdapter);
    }

    protected void setExpenseMode() {
        ArrayAdapter<Account> accountAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(
                this, AccountBaseDao.getInstance(this).getItems().toArray(new Account[0])
        );

        mSourceView.setAdapter(accountAdapter);

        ArrayAdapter<Category> expenseAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(
                this, CategoryBaseDao.getInstance(this).filterCategoriesOfType(OperationType.EXPENSE).toArray(new Category[0])
        );

        mTargetView.setAdapter(expenseAdapter);
    }

    protected void setReminttanceMode() {
        ArrayAdapter<Account> accountAdapter = SpinnerUtils.createSimpleSpinnerArrayAdapter(
                this, AccountBaseDao.getInstance(this).getItems().toArray(new Account[0])
        );

        // TODO check how it interracts
        mSourceView.setAdapter(accountAdapter);
        mTargetView.setAdapter(accountAdapter);
    }

    protected void setDate(Date date) {
        this.mDate = date;
        this.mDateView.setText(SimpleDateFormat.getInstance().format(mDate));
    }

    protected void initDateChooserView() {
        setDate(Calendar.getInstance().getTime());

        mDateChooserView.setOnClickListener(
                DatetimeUtils.getDatetimePickerClickListener(this, "Choose date and time",
                        new DatetimeUtils.DatetimePickerCallback() {
                            @Override
                            public void invoke(Date date) {
                                setDate(date);
                            }
                        })
        );
    }

    @Override
    protected int getAddItemViewId() {
        return R.id.activity_info_operation_add_button;
    }

    @IdRes
    protected int getTypeViewId() {
        return R.id.activity_info_operation_type_spinner;
    }

    @IdRes
    protected int getDateViewId() {
        return R.id.activity_info_operation_date_picker;
    }

    @IdRes
    protected int getDateChooserViewId() {
        return R.id.activity_info_operation_date_picker;
    }

    @IdRes
    protected int getValueViewId() {
        return R.id.activity_info_operation_value_edit;
    }

    @IdRes
    protected int getSourceViewId() {
        return R.id.activity_info_operation_source_spinner;
    }

    @IdRes
    protected int getTargetViewId() {
        return R.id.activity_info_operation_target_spinner;
    }

    @IdRes
    protected int getDescriptionViewId() {
        return R.id.activity_info_operation_description_edit;
    }

    @Override
    protected void disableInputs() {
        mTypeView.setEnabled(false);

        mDateView.setEnabled(false);
        mDateChooserView.setEnabled(false);

        mValueView.setEnabled(false);

        mSourceView.setEnabled(false);
        mTargetView.setEnabled(false);

        mDescriptionView.setEnabled(false);
    }

    @Override
    protected Operation createAndAddItem() {
        OperationType operationType = (OperationType) mTypeView.getSelectedItem();

        double value = Double.parseDouble(mValueView.getText().toString());
        String description = mDescriptionView.getText().toString();

        switch (operationType) {
            case INCOME:
                Category income = (Category) mSourceView.getSelectedItem();
                Account target = (Account) mTargetView.getSelectedItem();
                return  dao.addIncomeOperation(mDate, income, value, target, description);
            case EXPENSE:
                Account source = (Account) mSourceView.getSelectedItem();
                Category expense = (Category) mTargetView.getSelectedItem();
                return dao.addExpenseOperation(mDate, expense, value, source, description);
            case REMITTANCE:
                source = (Account) mSourceView.getSelectedItem();
                target = (Account) mTargetView.getSelectedItem();
                return dao.addRemittanceOperation(mDate, value, source, target, description);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_info_operation;
    }

    @Override
    protected OperationActivityProvider createProvider() {
        return OperationBaseDao.getInstance(this);
    }

    @Override
    protected OperationActivityDao createDao() {
        return new OperationActivityDao(this);
    }

    @Override
    public void onEvent(Event<Operation> event) {
        if (event instanceof ItemLoadEvent) {
            ItemLoadEvent<Operation> itemLoadEvent = event.toType();

            Operation operation = itemLoadEvent.getItem();

            mItemId = operation.getId();

            SpinnerUtils.selectItem(mTypeView, operation.getType());

            setDate(operation.getDate());

            mValueView.setText(String.format("%.2f", operation.getValue()));
            mDescriptionView.setText(operation.getDescription());

            switch (operation.getType()) {
                case INCOME:
                    SpinnerUtils.selectItem(mSourceView, operation.getCategory());
                    SpinnerUtils.selectItem(mTargetView, operation.getTarget());
                    break;
                case EXPENSE:
                    SpinnerUtils.selectItem(mSourceView, operation.getSource());
                    SpinnerUtils.selectItem(mTargetView, operation.getCategory());
                    break;
                case REMITTANCE:
                    SpinnerUtils.selectItem(mSourceView, operation.getSource());
                    SpinnerUtils.selectItem(mTargetView, operation.getTarget());
                    break;
            }
        }
    }
}
