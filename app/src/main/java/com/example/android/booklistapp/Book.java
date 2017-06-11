package com.example.android.booklistapp;

class Book {
    private final String thumbnail;
    private final String title;
    private final StringBuilder author;
    private final String publisher;
    private final String pageCount;
    private final String url;

    Book(String thumbnail, String title, StringBuilder author, String publisher, String pageCount, String url) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.url = url;
    }

    String getThumbnail() {
        return thumbnail;
    }

    String getTitle() {
        return title;
    }

    StringBuilder getAuthor() {
        return author;
    }

    String getPublisher() {
        return publisher;
    }

    String getPageCount() {
        return pageCount;
    }

    String getUrl() {
        return url;
    }
}