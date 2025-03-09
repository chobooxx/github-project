package jablonski.jakub.client;

import jablonski.jakub.model.Branch;
import jablonski.jakub.model.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;


@ApplicationScoped
@RegisterRestClient(configKey = "github-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GitHubClient {

    @GET
    @Path("/users/{username}/repos")
    List<Repository> getRepositories(@PathParam("username") String username);

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    List<Branch> getBranches(@PathParam("owner") String owner, @PathParam("repo") String repo);
}