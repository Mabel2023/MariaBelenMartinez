package com.distribuida.servicios;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject
    DataSource ds;


    @Override
    public Book findById(Integer id) {
        Connection conex =null;
        Book book = null;

        try {
            conex = ds.getConnection();
            PreparedStatement prepara = conex.prepareStatement("SELECT * FROM books WHERE id = ?");
            prepara.setInt(1,id);
            ResultSet result = prepara.executeQuery();
            if (result.next()){
                book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("Author"));
                book.setPrice(result.getDouble("Price"));
                book.setIsbn(result.getString("Isbn"));
            }
            result.close();
            prepara.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                conex.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return book;
    }

    List<Book> libro = new ArrayList<>();
    @Override
    public List<Book> findAll() {
        Connection conex = null;
        Book book = null;
        try {
            conex = ds.getConnection();
            PreparedStatement prepara = conex.prepareStatement("SELECT * FROM books");
            ResultSet result = prepara.executeQuery();
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("Author"));
                book.setPrice(result.getDouble("Price"));
                book.setIsbn(result.getString("Isbn"));
                libro.add(book);
            }
            result.close();//cerrar recursos
            prepara.close();//cerrar recursos
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conex.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return libro;
    }

    @Override
    public void insert(Book book) {
        Connection conex = null;
        try{
            conex = ds.getConnection();
            PreparedStatement prepara = conex.prepareStatement("INSERT INTO books ((id, title, author, price, isbn) values (?,?,?,?,?)");
            prepara.setInt(1, book.getId());
            prepara.setString(2, book.getTitle());
            prepara.setString(3, book.getAuthor());
            prepara.setDouble(4, book.getPrice());

            prepara.executeUpdate();
            prepara.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                conex.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void update(Book book) {
        Connection conex = null;
        try{
            conex = ds.getConnection();
            PreparedStatement prepara = conex.prepareStatement("UPDATE books SET id = ?, title = ?, author = ?, price = ?, isbn = ?) values (?,?,?,?,?)");
            prepara.setInt(1, book.getId());
            prepara.setString(2, book.getTitle());
            prepara.setString(3, book.getAuthor());
            prepara.setDouble(4, book.getPrice());
            prepara.setString(5, book.getIsbn());
            prepara.executeUpdate();
            prepara.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                conex.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Connection conex = null;
        try{
            conex = ds.getConnection();
            PreparedStatement prepara = conex.prepareStatement("DELETE FROM books WHERE id = ?");
            prepara.setInt(1, id);
            prepara.executeUpdate();
            prepara.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                conex.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }
}



