package kapistelykirjasto.dao;

import java.util.ArrayList;

import kapistelykirjasto.dao.models.BookModel;
import kapistelykirjasto.dao.models.VideoModel;
import kapistelykirjasto.util.Result;

public interface BookDao {

    public Result<String, Integer> createBook(String title, String comment, String author, String ISBN);

    public ArrayList<BookModel> getBooks();

    public boolean deleteBook(int id);

    public boolean editBook(int id, String title, String comment, String author, String ISBN);

    public boolean markBookAsRead(int id);

    public ArrayList<BookModel> getReadBooks();

    public ArrayList<BookModel> getNotReadBooks();

    public void close();
}
