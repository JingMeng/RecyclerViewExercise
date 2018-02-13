package com.sinieco.recyclerviewexercise.textinputlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/9
 * Description:
 */

public class TextInputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinput);
        TextInputLayout input = (TextInputLayout) findViewById(R.id.text_input);
//        input.setCounterMaxLength(10);
//        input.setCounterEnabled(true);
        input.setErrorEnabled(true);
        input.getEditText().addTextChangedListener(new InputTextWatcher(input,"不能包含5"));

    }

    private class InputTextWatcher implements TextWatcher {
        private TextInputLayout inputLayout;
        private String errorMsg;
        public InputTextWatcher(TextInputLayout inputLayout,String errorMsg) {
            this.inputLayout = inputLayout;
            this.errorMsg = errorMsg;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(inputLayout.getEditText().getText().toString().contains("5")) {
                inputLayout.setError(errorMsg);
            }
        }
    }

    private void animotor(View view) {
        ViewCompat.animate(view).alpha(0);
    }
}
