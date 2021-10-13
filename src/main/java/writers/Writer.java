package writers;

import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.util.List;

public interface Writer {
    void writeRepositoriesInTheList(List<RepositoryDTO> repositoryDTOS, String organizationName);
    void writeContributorsInTheList(List<ContributorDTO> contributorDTOS, String organizationName);
}
