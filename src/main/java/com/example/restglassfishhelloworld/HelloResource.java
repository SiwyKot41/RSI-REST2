package com.example.restglassfishhelloworld;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @GET
    @Path("/echo")
    @Produces("text/plain")
    public String echo() { return "ECHO"; }

    @GET
    @Path("/echo2/{param}")
    public String echo2(@PathParam("param") String name) {
        return "ECHO: " + name;
    }
}