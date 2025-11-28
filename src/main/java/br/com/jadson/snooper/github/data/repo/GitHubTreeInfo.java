package br.com.jadson.snooper.github.data.repo;

import java.util.List;

public class GitHubTreeInfo {
    public String sha;
    public List<TreeItem> tree;

    public static class TreeItem {
        public String path;
        public String type; // "blob" = arquivo, "tree" = diretório
        public long size;
    }
}
