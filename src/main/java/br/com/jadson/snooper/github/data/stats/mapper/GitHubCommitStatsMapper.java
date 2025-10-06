package br.com.jadson.snooper.github.data.stats.mapper;

import br.com.jadson.snooper.github.data.stats.CommitStats;
import br.com.jadson.snooper.github.data.stats.GitHubCommitStatsInfo;
import br.com.jadson.snooper.github.data.stats.graphql.CommitStatsNode;

public class GitHubCommitStatsMapper {

    public static GitHubCommitStatsInfo mapToCommitStatsInfo(CommitStatsNode node) {

        GitHubCommitStatsInfo commitStatsInfo = new GitHubCommitStatsInfo();
        CommitStats commitStats = new CommitStats();

        commitStatsInfo.sha = node.oid;
        commitStatsInfo.nodeId = node.id;
        commitStatsInfo.url = node.url;
        commitStatsInfo.commentCount = node.comments.totalCount;
        commitStatsInfo.message = node.message;

        commitStats.additions = node.additions;
        commitStats.deletions = node.deletions;
        commitStats.total = (node.additions + node.deletions);

        commitStatsInfo.commitStats = commitStats;
        commitStatsInfo.changedFiles = node.changedFiles;

        if (node.author != null) {
            commitStatsInfo.authorName = node.author.name;
            commitStatsInfo.authorEmail = node.author.email;
            commitStatsInfo.authorDate = node.author.date;
            if (node.author.user != null) {
                commitStatsInfo.authorLogin = node.author.user.login;
            }
        }

        if (node.committer != null) {
            commitStatsInfo.committerName = node.committer.name;
            commitStatsInfo.committerEmail = node.committer.email;
            commitStatsInfo.committerDate = node.committer.date;
            if (node.committer.user != null) {
                commitStatsInfo.committerLogin = node.committer.user.login;
            }
        }

        return  commitStatsInfo;
    }

}
