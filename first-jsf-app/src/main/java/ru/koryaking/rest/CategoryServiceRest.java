package ru.koryaking.rest;

import ru.koryaking.service.CategoryRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryRepr findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryRepr categoryRepr) throws IllegalAccessException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryRepr categoryRepr) throws IllegalAccessException;

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}