package com.bandlab.countrychooser;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import com.bandlab.defaultvaluespinner.DefaultValueSpinner;
import com.example.modulecountrychooser.R;

public class CountryChooser extends DefaultValueSpinner {

    private final Country[] countries;

    public CountryChooser(Context context) {
        this(context, null);
    }

    public CountryChooser(Context context, AttributeSet attrs) {
        super(context, attrs);
        final String[] codes = context.getResources().getStringArray(R.array.country_codes);
        countries = new Country[codes.length];
        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];
            countries[i] = Countries.getCountryByCode(context, Integer.parseInt(code));
        }
        ArrayAdapter<Country> adapter = new ArrayAdapter<>(getContext(), R.layout.country_item, countries);
        setDefaultValueAdapter(adapter, R.layout.country_item);
    }

    public final int getSelectedCountryCode() {
        Country country = (Country) getSelectedItem();
        return country.code;
    }

    public final void selectCountry(int code) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].code == code) {
                setSelection(i);
                return;
            }
        }
        selectDefault();
    }
}
