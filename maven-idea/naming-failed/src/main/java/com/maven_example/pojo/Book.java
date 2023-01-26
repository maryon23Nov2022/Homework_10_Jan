package com.maven_example.pojo;

public class Book{
    Integer id;
    String bookName, author, description;
    Integer surplus;
    String classification, type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Book(Integer id, String bookName, String author, String description, Integer surplus, String classification, String type) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.surplus = surplus;
        this.classification = classification;
        this.type = type;
    }

    public Book(){}
}
