package ie.moses.forms;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ie.moses.forms.util.FormFieldValidator;

public class FormFieldView extends FrameLayout {

    @BindView(R2.id.edit_text) EditText _editText;
    @BindView(R2.id.error_message) TextView _errorMessage;

    @Nullable private FormFieldValidator<CharSequence> _validator;

    public FormFieldView(@NonNull Context context) {
        this(context, null);
    }

    public FormFieldView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FormFieldView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.form_field_view, this);
        ButterKnife.bind(this);
        initAttrs(context, attrs);
    }

    public void setFormFieldValidator(@Nullable FormFieldValidator<CharSequence> validator) {
        _validator = validator;
    }

    public boolean validate() {
        CharSequence text = _editText.getText();
        boolean isValid = _validator == null || _validator.isValid(text);
        if (!isValid) {
            String errorMessage = _validator.getErrorMessage();
            _errorMessage.setText(errorMessage);
        }
        _errorMessage.setVisibility(isValid ? INVISIBLE : VISIBLE);

        return isValid;
    }

    public void setHint(CharSequence hint) {
        _editText.setHint(hint);
    }

    public String getText() {
        return _editText.getText().toString();
    }

    public void setText(CharSequence text) {
        _editText.setText(text);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        Resources.Theme theme = context.getTheme();
        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.FormFieldView, 0, 0);

        try {
            String hintText = a.getString(R.styleable.FormFieldView_hint);
            setHint(hintText);
        } finally {
            a.recycle();
        }
    }

}
