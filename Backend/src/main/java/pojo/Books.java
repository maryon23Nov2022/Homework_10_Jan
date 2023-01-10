package pojo;

public class Books{
    private Integer id;
    private Integer surplus;
    private String bookName, author, description;

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

    public Books(Integer id, Integer surplus, String bookName, String author, String description) {
        this.id = id;
        this.surplus = surplus;
        this.bookName = bookName;
        this.author = author;
        this.description = description;
    }

    public Books(){}
}
