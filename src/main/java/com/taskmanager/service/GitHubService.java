package com.taskmanager.service;

import com.taskmanager.util.GitHubApiClient;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class GitHubService {
    private final GitHubApiClient gitHubApiClient;

    public GitHubService(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public String createRepository(String repoName, String description, boolean isPrivate) throws IOException {
        return gitHubApiClient.createRepo(repoName, description, isPrivate);
    }

    public String getRepository(String owner, String repoName) throws IOException {
        return gitHubApiClient.getRepo(owner, repoName);
    }

    public String deleteRepository(String owner, String repoName) throws IOException {
        return gitHubApiClient.deleteRepo(owner, repoName);
    }
}