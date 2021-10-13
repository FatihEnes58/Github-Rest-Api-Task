package business.concretes;

import business.abstracts.RepositoryManager;
import entities.Repository;
import exceptions.NotImplementedException;
import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.util.List;

public class BitbucketRepositoryManager implements RepositoryManager {
    @Override
    public List<RepositoryDTO> getMostForkedRepositoriesOfOrganization(int numOfQueriedTop, List<Repository> repositoryList, String organizationName) {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ContributorDTO> getTheTopContributorsOfRepo(List<Repository> repositoryList, int numOfQueriedTop) {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
