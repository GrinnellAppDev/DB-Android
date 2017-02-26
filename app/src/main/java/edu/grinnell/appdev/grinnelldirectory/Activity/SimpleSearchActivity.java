package edu.grinnell.appdev.grinnelldirectory.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.R;

public class SimpleSearchActivity extends AppCompatActivity {
  @BindView(R.id.query) private EditText mQueryEditText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_simple_search);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.search)
  private void seach(View view) {
    String queryText = mQueryEditText.getText().toString();
  }
}
