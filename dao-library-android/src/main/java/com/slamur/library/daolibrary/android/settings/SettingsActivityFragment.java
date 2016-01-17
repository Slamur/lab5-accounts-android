package com.slamur.library.daolibrary.android.settings;

import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public abstract class SettingsActivityFragment extends PreferenceFragment {

    /**
     * Should be addPreferencesFromResource(R.xml.pref_general);
     */
    protected abstract void addPreferencesOnCreate();

    protected abstract void initPreferences();

    protected Activity mActivity;

    protected <PreferenceType extends Preference> PreferenceType findPreference(int resId) {
        return (PreferenceType)findPreference(
                getString(resId)
        );
    }

    protected void setDefaultEditTextPreferenceOnChangeListener(int resId, final String key) {
        final EditTextPreference editTextPreference = findPreference(resId);

        editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    SettingsUtils.setStringValue(mActivity, key, (String) newValue);
                    editTextPreference.setSummary(newValue.toString());
                    return true;
                }
        });

        editTextPreference.setSummary(SettingsUtils.getStringValue(mActivity, key, ""));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mActivity = getActivity();

        addPreferencesOnCreate();

        initPreferences();
    }
}
