package com.example.android.booklistapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RequestResult extends AppCompatActivity {
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private TextView noInternetConnection;
    private static final String API_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Result");
        setContentView(R.layout.list_view);
        String bookTitle = getIntent().getStringExtra("TITLE");
        String end = "&maxResults=25";
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        noInternetConnection = (TextView) findViewById(R.id.no_internet_connection);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        String booksUrl = API_REQUEST_URL + bookTitle.toLowerCase() + end;
        ListView listView = (ListView) findViewById(R.id.list);

        RequestAsyncTask task = new RequestAsyncTask();
        if(isConnected){
            task.execute(booksUrl);
        }else {
            progressBar.setVisibility(View.GONE);
            noInternetConnection.setText(getString(R.string.no_internet_connection));
        }
        adapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = adapter.getItem(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(book.getUrl()));
                if(i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

    }

    private class RequestAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            return Utils.fetchBookList(urls[0]);
        }

        protected void onPostExecute(List<Book> books) {
            adapter.clear();
            progressBar.setVisibility(View.GONE);
            if (books != null && !books.isEmpty()) {
                adapter.addAll(books);
            } else {
                noInternetConnection.setText(getString(R.string.no_data_found));
            }
        }
    }
}