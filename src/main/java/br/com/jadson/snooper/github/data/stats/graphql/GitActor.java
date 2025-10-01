package br.com.jadson.snooper.github.data.stats.graphql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitActor {
    public User user;
    public String name;
    public String email;
    public Date date;
    public String avatarUrl;
}