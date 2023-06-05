package com.example.restglassfishhelloworld;

import com.google.gson.Gson;
import model.Message;
import services.MessageService;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/messages")
@Singleton
public class MessageResource {
    private final MessageService messageService;
    public MessageResource() {
        messageService = new MessageService();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getText() {
        return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") Long messageId) {
        return messageService.getMessage(messageId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json")
    public String getTextJSON() {
        return new Gson().toJson(messageService.getAllMessages());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message createMessage(Message message) {
        return messageService.createMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message updateMessage(@PathParam("messageId") Long messageId, Message message) {
        return messageService.updateMessage(message, messageId);
    }

    @DELETE
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteMessage(@PathParam("messageId") Long messageId) {
        messageService.deleteMessage(messageId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/urlmessage/{messageId}")
    public Message getMessageDetailsWithQueryParam(@HeaderParam("Content-Type") String contentType, @QueryParam("name") String name, @PathParam("messageId") long id,
                                     @QueryParam("message") String message
    ) {
        Message message1 = new Message();
        message1.setName(contentType + " " + name);
        message1.setMessage(message);
        message1.setId(id);
        return message1;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/urlmessage")
    public Message getMessageDetailsWithMatrixParam(@HeaderParam("Content-Type") String contentType, @MatrixParam("name") String name,
                                     @MatrixParam("message") String message
    ) {
        Message message1 = new Message();
        message1.setName(name);
        message1.setMessage(message);
        message1.setId(5);
        return message1;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public Response test(@Context UriInfo uriInfo) {
        return Response.status(200).entity(uriInfo.getAbsolutePath().toString()).build();
    }
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("/info/{messageId}")
//    public Response getMessageDetailsWithHeaderParam(@HeaderParam("Content-Type") String contentType, @PathParam("messageId") long id,
//                                      @QueryParam("message") String message
//    ) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Content-type: ").append(contentType).append("\n");
//        builder.append("Id: ").append(id + "").append("\n");
//        builder.append("Message: ").append(message).append("\n");
//
//        return Response.status(200).entity(builder.toString()).build();
//    }

}
