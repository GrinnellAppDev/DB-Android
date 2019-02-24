package edu.grinnell.appdev.grinnelldirectory.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.grinnell.appdev.grinnelldirectory.DBAPICaller;
import edu.grinnell.appdev.grinnelldirectory.R;
import edu.grinnell.appdev.grinnelldirectory.adapters.SearchResultsAdapter;
import edu.grinnell.appdev.grinnelldirectory.interfaces.DbSearchCallback;
import edu.grinnell.appdev.grinnelldirectory.interfaces.SearchCaller;
import edu.grinnell.appdev.grinnelldirectory.models.Person;
import edu.grinnell.appdev.grinnelldirectory.models.Query;
import java.util.List;
import okhttp3.ResponseBody;

public class ResultsActivity extends AppCompatActivity implements DbSearchCallback {

    @BindView(R.id.results_recycler_view) RecyclerView resultsRecyclerView;
    @BindView(R.id.connection_progress) ProgressBar progressBar;
    @BindView(R.id.message) TextView errorMessage;
    @BindView(R.id.retry_button) Button retryButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        search();
    }

    @OnClick(R.id.retry_button)
    void search() {
        errorMessage.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        Query query = getIntent().getParcelableExtra(Query.QUERY_KEY);
        SearchCaller api = new DBAPICaller(this);
        api.search(query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override public void onSuccess(List<Person> people) {
        SearchResultsAdapter adapter = new SearchResultsAdapter(this, people);
        resultsRecyclerView.setAdapter(adapter);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultsRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override public void onServerError(int code, ResponseBody error) {
        progressBar.setVisibility(View.GONE);
        errorMessage.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        errorMessage.setText(R.string.server_failure);
    }

    @Override public void onNetworkError(String errorMessageText) {
        progressBar.setVisibility(View.GONE);
        errorMessage.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        errorMessage.setText(R.string.no_connection);
    }
}
