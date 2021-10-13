package writers;

import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter implements Writer{
    @Override
    public void writeRepositoriesInTheList(List<RepositoryDTO> repositoryDTOS, String organizationName) {
        StringBuilder fileNameBuilder = new StringBuilder(organizationName);
        fileNameBuilder.append("_repos.csv");
        File file = new File(fileNameBuilder.toString());
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("***TOP REPOSITORIES***");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (RepositoryDTO repositoryDTO : repositoryDTOS) {
            try {
                bw.write(repositoryDTO.toString());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeContributorsInTheList(List<ContributorDTO> contributorDTOS, String organizationName) {
        StringBuilder fileNameBuilder = new StringBuilder(organizationName);
        fileNameBuilder.append("_users.csv");
        File file = new File(fileNameBuilder.toString());
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            bw.write("***TOP CONTRIBUTORS***");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ContributorDTO contributorDTO : contributorDTOS) {
            try {
                bw.write(contributorDTO.toString());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
