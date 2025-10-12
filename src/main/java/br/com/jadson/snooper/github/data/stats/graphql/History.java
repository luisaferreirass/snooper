package br.com.jadson.snooper.github.data.stats.graphql;

import br.com.jadson.snooper.github.data.association.graphql.PageInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class History {
    public PageInfo pageInfo;
    public List<CommitStatsNode> nodes;
}