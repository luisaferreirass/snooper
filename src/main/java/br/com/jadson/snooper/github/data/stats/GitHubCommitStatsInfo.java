package br.com.jadson.snooper.github.data.stats;

import java.util.Date;

public class GitHubCommitStatsInfo {
    public String sha;
    public String nodeId;
    public String url;
    public int commentCount;
    public String authorName;
    public String authorEmail;
    public String authorLogin;
    public Date authorDate;
    public String committerName;
    public String committerEmail;
    public String committerLogin;
    public Date committerDate;
    public String message;
    public CommitStats commitStats;
    public int changedFiles;
}