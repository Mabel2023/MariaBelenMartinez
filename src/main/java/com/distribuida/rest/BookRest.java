package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private BookService service2;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)//formato de entrega de datos
    public Book findById (@PathParam("id") Integer id){
        return service2.findById(id);
    }

    @GET
    public List<Book> findAll(){
        return service2.findAll();
    }

    @POST
    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert (Book book){
        service2.insert(book);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update (Book book){
        service2.update(book);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete (@PathParam("id") Integer id){
        service2.delete(id);
    }

}
