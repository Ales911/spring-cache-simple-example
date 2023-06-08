package com.example.springcachesimpleexample;

import java.io.Serializable;

public class Book implements Serializable {
    
    private String isbn;
    private String author;
    private String title;
    
    public Book(String isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }
    
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "{\"isbn\":\"" + isbn + "\",\"title\":\"" +  title + "\",\"author\":\"" +  author +  "\"}";
    }    
}