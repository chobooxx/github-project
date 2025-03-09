package jablonski.jakub.service;

import jablonski.jakub.client.GitHubClient;
import jablonski.jakub.dto.BranchResponse;
import jablonski.jakub.dto.RepositoryResponse;
import jablonski.jakub.dto.UserNotFoundException;
import jablonski.jakub.model.Branch;
import jablonski.jakub.model.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GitHubService {

    @RestClient
    GitHubClient gitHubClient;

    public List<RepositoryResponse> getRepositories(String username) {
        try {
            List<Repository> repositories = gitHubClient.getRepositories(username);

            if (repositories == null || repositories.isEmpty()) {
                throw new UserNotFoundException("User " + username + " not found.");
            }

            return repositories.stream()
                    .filter(repo -> !repo.isFork())
                    .map(repo -> new RepositoryResponse(
                        repo.getName(),
                        repo.getOwner().getLogin(),
                        getBranches(repo.getOwner().getLogin(), repo.getName())
                    ))
                    .collect(Collectors.toList());

        } catch (ClientWebApplicationException e) {
            if (e.getResponse().getStatus() == 404) {
                throw new UserNotFoundException("User " + username + " not found.");
            }
            throw new RuntimeException(e);
        }
    }

    private List<BranchResponse> getBranches(String owner, String repo) {
        List<Branch> branches = gitHubClient.getBranches(owner, repo);
        return branches.stream()
                .map(branch -> new BranchResponse(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
    }
}
