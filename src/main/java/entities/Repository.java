package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    public String name;
    public String description;
    public String url;
    public int forks_count;
    public String contributors_url;
}
