/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.rest.domain.Customer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pumpkin
 */
@Path("customers")
public class CustomerResource {
    static private Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    static private AtomicInteger idCounter = new AtomicInteger();
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayCustomerHello() {
        return "Hello, customer";
    }
    
    
    @POST
    @Consumes("application/xml")
    public Customer createCustomer(Customer customer) {
        customer.id = idCounter.incrementAndGet();
        customerDB.put(customer.id, customer);
        return customer;
    }
    
    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Customer getCustomer(@PathParam("id") int id) {
        return customerDB.get(id);
    } 
    
    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateCustomer(@PathParam("id") int id, Customer update) {
        Customer current = customerDB.get(id);
        current.firstname = update.firstname;
        current.lastname = update.lastname;
        current.email = update.email;
        customerDB.put(current.id, current);
    } 
    
    @DELETE
    @Path("{id}")
    public void deleteCustomer(@PathParam("id") int id) {
        Customer current = customerDB.remove(id);
        if (current == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    } 
}
