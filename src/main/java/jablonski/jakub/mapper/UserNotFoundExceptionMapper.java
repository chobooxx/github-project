package jablonski.jakub.mapper;


import jablonski.jakub.dto.ErrorResponse;
import jablonski.jakub.dto.UserNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getStatusCode(),
                exception.getMessage()
        );

        return Response.status(exception.getStatusCode())
                .entity(errorResponse)
                .build();
    }
}