package br.com.jadson.snooper.gitlab.data.repo;

public class GitLabTreeItem {
    public String  name;
    public String path;
    public String type; // "blob" = arquivo, "tree" = diretório
    public long size;

}
