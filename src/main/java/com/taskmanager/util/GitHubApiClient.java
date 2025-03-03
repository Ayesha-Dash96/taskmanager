package com.taskmanager.util;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GitHubApiClient {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private final OkHttpClient client = new OkHttpClient();

    @Value("${github.token}") // Fetch from application.properties
    private String token;

    public String createRepo(String repoName, String description, boolean isPrivate) throws IOException {
        String url = GITHUB_API_URL + "/user/repos";
        String json = String.format("{\"name\":\"%s\", \"description\":\"%s\", \"private\":%b}", repoName, description, isPrivate);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, MediaType.get("application/json")))
                .header("Authorization", "token " + token)
                .header("Accept", "application/vnd.github.v3+json")
                .build();

        return executeRequest(request);
    }

    public String getRepo(String owner, String repoName) throws IOException {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", "token " + token)
                .header("Accept", "application/vnd.github.v3+json")
                .build();

        return executeRequest(request);
    }

    public String deleteRepo(String owner, String repoName) throws IOException {
        String url = GITHUB_API_URL + "/repos/" + owner + "/" + repoName;

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .header("Authorization", "token " + token)
                .header("Accept", "application/vnd.github.v3+json")
                .build();

        return executeRequest(request);
    }

    private String executeRequest(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request failed: " + response.code() + " - " + response.message());
            }
            return response.body() != null ? response.body().string() : "No Response Body";
        }
    }
}