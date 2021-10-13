package transferOjects;

public class RepositoryDTO {
    public String repositoryName;
    public String url;
    public String description;
    public int forkQuantity;

    @Override
    public String toString() {
        return  "repositoryName = " + repositoryName +
                "; fork = " + forkQuantity +
                "; url = " + url +
                "; description = " + description;
    }
}
