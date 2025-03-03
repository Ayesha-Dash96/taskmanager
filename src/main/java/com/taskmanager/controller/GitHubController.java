package com.taskmanager.controller;

import com.taskmanager.service.GitHubService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/github")
public class GitHubController {
    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PostMapping("/create")
    public String createRepo(@RequestBody Map<String, Object> payload) throws IOException {
        return gitHubService.createRepository(
                (String) payload.get("name"),
                (String) payload.get("description"),
                (boolean) payload.get("private")
        );
    }

    @GetMapping("/repo/{owner}/{repoName}")
    public String getRepo(@PathVariable String owner, @PathVariable String repoName) throws IOException {
        return gitHubService.getRepository(owner, repoName);
    }

    @DeleteMapping("/repo/{owner}/{repoName}")
    public String deleteRepo(@PathVariable String owner, @PathVariable String repoName) throws IOException {
        return gitHubService.deleteRepository(owner, repoName);
    }
}
