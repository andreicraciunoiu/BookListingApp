package com.example.android.booklistapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class BookAdapter extends ArrayAdapter<Book> {

    private static final String AUTHOR = "Author: ";
    private static final String PUBLISHER = "Publisher: ";
    private static final String PAGE_COUNT = "Page count: ";

    BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    private static class ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView author;
        TextView publisher;
        TextView pageCount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View bookList = convertView;
        if (bookList == null) {
            bookList = LayoutInflater.from(getContext()).inflate(R.layout.book_sample, parent, false);
            holder = new ViewHolder();
            holder.thumbnail = (ImageView) bookList.findViewById(R.id.thumbnail);
            holder.title = (TextView) bookList.findViewById(R.id.book_title);
            holder.author = (TextView) bookList.findViewById(R.id.author);
            holder.publisher = (TextView) bookList.findViewById(R.id.publisher);
            holder.pageCount = (TextView) bookList.findViewById(R.id.page_count);
            bookList.setTag(holder);

        } else {
            holder = (ViewHolder) bookList.getTag();
        }
        Book currentBook = getItem(position);
        Picasso.with(getContext()).load(currentBook.getThumbnail()).into(holder.thumbnail);
        holder.title.setText(currentBook.getTitle());
        holder.author.setText(AUTHOR + currentBook.getAuthor());
        holder.publisher.setText(PUBLISHER + currentBook.getPublisher());
        holder.pageCount.setText(PAGE_COUNT + currentBook.getPageCount());

        return bookList;
    }
}