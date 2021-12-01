package ru.hse.android.lesson1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());

        if (savedInstanceState != null)
        {
            web.restoreState(savedInstanceState);
        }
        else
        {
            web.loadUrl("https://ya.ru");
        }

        EditText text = findViewById(R.id.search_line);
        text.setText(web.getUrl());

        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> {
            web.loadUrl(text.getText().toString());
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        WebView webView = findViewById(R.id.web);
        webView.saveState(outState);
    }
}