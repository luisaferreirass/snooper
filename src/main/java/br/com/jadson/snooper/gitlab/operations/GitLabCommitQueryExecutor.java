package br.com.jadson.snooper.gitlab.operations;

import br.com.jadson.snooper.github.data.commit.GitHubCommitInfo;
import br.com.jadson.snooper.gitlab.data.commit.GitLabCommitInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GitLabCommitQueryExecutor extends AbstractGitLabQueryExecutor {

    public GitLabCommitQueryExecutor() {

    }

    public GitLabCommitQueryExecutor(String gitlabURL, String gitlabToken) {
        this(gitlabURL, gitlabToken, false);
    }

    public GitLabCommitQueryExecutor(String gitlabURL, String gitlabToken, boolean disableSslVerification) {
        if (gitlabToken == null || gitlabToken.trim().equals("") ) {
            throw new RuntimeException("Invalid GitLab Token: " + gitlabToken);
        }
        if (gitlabURL == null || gitlabURL.trim().equals("") ) {
            throw new RuntimeException("Invalid GitLab URL: " + gitlabURL);
        }

        this.gitlabURL = gitlabURL;
        this.gitlabToken = gitlabToken;
        this.disableSslVerification = disableSslVerification;
    }

    /**
     * Return a list of commit form a repository on gitlab
     * @param repoFullName
     * @return
     */
    public List<GitLabCommitInfo> getCommits(String repoFullName) {
        int page = 1;
        String parameters = "";
        ArrayList allPull = new ArrayList();


        ResponseEntity result;
        do {
            if (this.queryParameters != null && !this.queryParameters.isEmpty()) {
                parameters = "?" + this.queryParameters + "with_stats=True&page=" + page + "&per_page=" + this.pageSize;
            } else {
                parameters = "?page=" + page + "&per_page=" + this.pageSize;
            }

            String query = getGitLabAPIURL() + encodeProjectName(repoFullName) + "/repository/commits" + parameters;

            // encode "/" in "%2F" supported
            URI uri = null;
            try {
                uri = new URI(query);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            System.out.println("Getting Next Commit: " + query);
            RestTemplate restTemplate = new RestTemplate();

            checkDisableSslVerification();


            HttpEntity entity = new HttpEntity(this.getDefaultHeaders());
            result = restTemplate.exchange(uri, HttpMethod.GET, entity, GitLabCommitInfo[].class);
            allPull.addAll(Arrays.asList((GitLabCommitInfo[])result.getBody()));
            ++page;
        } while(result != null && ((GitLabCommitInfo[])result.getBody()).length > 0 && !this.testEnvironment);

        return allPull;
    }

}
