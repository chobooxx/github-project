package jablonski.jakub.resource;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jablonski.jakub.dto.RepositoryResponse;
import jablonski.jakub.service.GitHubService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/github")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GitHubResource {

    @Inject
    GitHubService gitHubService;

    @GET
    @Path("/repos/{username}")
    @Blocking
    public Uni<List<RepositoryResponse>> getRepositories(@PathParam("username") String username) {
        return Uni.createFrom().item(() -> gitHubService.getRepositories(username));
    }
}
