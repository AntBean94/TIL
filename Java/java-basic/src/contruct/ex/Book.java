package contruct.ex;

public class Book {
    String title; // 제목
    String author; //저자
    int page; //페이지 수

    Book() {
        this("", "");
    }

    Book(String title, String author) {
        this(title, author, 1);
    }

    Book(String title, String author, int page) {
        this.title = title;
        this.author = author;
        this.page = page;
    }

    public void displayInfo() {
        System.out.println("title = " + title + ", author = " + author + ", page = " + page);
    }
}
