import business.abstracts.RepositoryManager;
import entities.Repository;
import exceptions.InvalidNumOfArgsException;
import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;
import writers.Writer;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        if (isNumOfArgsInvalid(args)) {
            return;
        }
        RepositoryManager repositoryManager = RepositoryManagerFactory.getFactoryInstance().getRepositoryManager();
        List<Repository> repositoryListToGetContributors = new ArrayList<>();

        String organizationName = args[0];
        int numOfMostForkedRepos = Integer.parseInt(args[1]);
        int numOfTopContributors = Integer.parseInt(args[2]);

        List<RepositoryDTO> repositoryDTOS = repositoryManager.getMostForkedRepositoriesOfOrganization(numOfMostForkedRepos, repositoryListToGetContributors,organizationName);
        List<ContributorDTO> contributorDTOS = repositoryManager.getTheTopContributorsOfRepo(repositoryListToGetContributors, numOfTopContributors);

        Writer writer = WriterFactory.getFactoryInstance().getWriter();
        writer.writeRepositoriesInTheList(repositoryDTOS, organizationName);
        writer.writeContributorsInTheList(contributorDTOS, organizationName);
    }

    private static boolean isNumOfArgsInvalid(String[] args) {
        if (args.length != 3) {
            try {
                throw new InvalidNumOfArgsException();
            } catch (InvalidNumOfArgsException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

}
