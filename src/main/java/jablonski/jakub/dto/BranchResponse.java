package jablonski.jakub.dto;

public class BranchResponse {
    private String name;
    private String commitSha;

    public BranchResponse() {
    }

    public BranchResponse(String name, String commitSha) {
        this.name = name;
        this.commitSha = commitSha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }

    @Override
    public String toString() {
        return "BranchResponse{name='" + name + "', commitSha='" + commitSha + "'}";
    }
}
