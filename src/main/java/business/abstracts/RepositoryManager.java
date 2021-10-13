package business.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import entities.Contributor;
import entities.Follower;
import entities.Repository;
import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.net.http.HttpResponse;
import java.util.List;

public interface RepositoryManager {
    List<RepositoryDTO> getMostForkedRepositoriesOfOrganization(int numOfQueriedTop, List<Repository> repositoryList, String organizationName);
    List<ContributorDTO> getTheTopContributorsOfRepo(List<Repository> repositoryList, int numOfQueriedTop);
}
