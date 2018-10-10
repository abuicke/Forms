package ie.moses.forms.util;

import android.support.annotation.NonNull;

import ie.moses.forms.util.Validator;

public interface FormFieldValidator<T> extends Validator<T> {

    @NonNull
    String getErrorMessage();

}
