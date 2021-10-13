package writers;

import exceptions.NotImplementedException;
import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.util.List;

public class TxtWriter implements Writer{
    @Override
    public void writeRepositoriesInTheList(List<RepositoryDTO> repositoryDTOS, String organizationName) {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeContributorsInTheList(List<ContributorDTO> contributorDTOS, String organizationName) {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
    }
}
