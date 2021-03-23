package ru.koryaking.rest;

import ru.koryaking.service.ProductRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepr> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepr findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepr product) throws IllegalAccessException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepr product) throws IllegalAccessException;

    @DELETE
    @Path("/{id}")
    void deleteById(Long id);
}