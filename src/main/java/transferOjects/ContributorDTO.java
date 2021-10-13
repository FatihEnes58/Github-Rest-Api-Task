package transferOjects;

public class ContributorDTO {
    public String repositoryName;
    public String username;
    public int contributionQuantity;
    public int followerQuantity;

    @Override
    public String toString() {
        return  "repositoryName = " + repositoryName +
                "; username = " + username +
                "; contributions = " + contributionQuantity +
                "; followers = " + followerQuantity;
    }
}
