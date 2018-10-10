package ie.moses.forms.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import ie.moses.forms.R;

public class EmptyFormFieldValidator implements FormFieldValidator<CharSequence> {

    private static EmptyFormFieldValidator _sInstance;

    private Resources _resources;

    private EmptyFormFieldValidator(Context context) {
        _resources = context.getResources();
    }

    @Override
    public boolean isValid(CharSequence charSequence) {
        return charSequence.length() > 0;
    }

    @NonNull
    @Override
    public String getErrorMessage() {
        return _resources.getString(R.string.empty_form_field_error_message);
    }

    public static synchronized EmptyFormFieldValidator getInstance(Context context) {
        if (_sInstance == null) {
            _sInstance = new EmptyFormFieldValidator(context);
        }

        return _sInstance;
    }

}
