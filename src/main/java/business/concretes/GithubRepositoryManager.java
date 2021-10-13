package business.concretes;

import business.abstracts.RepositoryManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Contributor;
import entities.Follower;
import entities.Repository;
import transferOjects.ContributorDTO;
import transferOjects.RepositoryDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class GithubRepositoryManager implements RepositoryManager {


    private HttpResponse<String> getStringHttpResponseByUrl(final String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET().header("accept", "application/json")
                .uri(URI.create(url)).build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }


    private <T> T getObjectResponse(Class<T> clazz, String url) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getStringHttpResponseByUrl(url).body(), clazz);
    }


    private Repository[] getAllRepositoriesAsArray(final String POSTS_API_URL) {
        //final String POSTS_API_URL = "https://api.github.com/orgs/microsoft/repos";
        Repository[] githubRepositoryResponse = new Repository[1];
        try {
            githubRepositoryResponse = getObjectResponse(Repository[].class, POSTS_API_URL);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return githubRepositoryResponse;
    }


    private Contributor[] gettAllContributorsAsArray(final String POSTS_API_URL) {
        Contributor[] githubContributorResponse = new Contributor[1];
        try {
            githubContributorResponse = getObjectResponse(Contributor[].class, POSTS_API_URL);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return githubContributorResponse;
    }


    private Follower[] gettAllFollowersAsArray(final String POSTS_API_URL) {
        Follower[] githubFollowerResponse = new Follower[1];
        try {
            githubFollowerResponse = getObjectResponse(Follower[].class, POSTS_API_URL);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return githubFollowerResponse;
    }

    @Override
    public List<RepositoryDTO> getMostForkedRepositoriesOfOrganization(int numOfQueriedTop, List<Repository> repositoryList, String organizationName) {
        StringBuilder stringBuilder = new StringBuilder("https://api.github.com/orgs/");
        stringBuilder.append(organizationName);
        stringBuilder.append("/repos");
        Repository[] repositories = getAllRepositoriesAsArray(stringBuilder.toString());

        numOfQueriedTop = verifyNumOfQueriedTopForRepos(numOfQueriedTop, repositories);
        List<RepositoryDTO> repositoryDTOS = new ArrayList<>();
        List<Repository> unmodifiableCopyOfRepositoryArray = Arrays.stream(repositories).toList();
        List<Repository> modifiableCopyOfRepositoryArray = new ArrayList<>(unmodifiableCopyOfRepositoryArray);
        sortRepoListDescendingOrder(modifiableCopyOfRepositoryArray);

        addMostForkedReposOfOrganizationToRepoListAndRepoDtoList(numOfQueriedTop, repositoryList, repositoryDTOS, modifiableCopyOfRepositoryArray);
        return repositoryDTOS;
    }

    private void addMostForkedReposOfOrganizationToRepoListAndRepoDtoList(int numOfQueriedTop, List<Repository> repositoryList, List<RepositoryDTO> repositoryDTOS, List<Repository> modifiableCopyOfRepositoryArray) {
        for (int i = 0; i < numOfQueriedTop; i++) {
            Repository currentRepository = modifiableCopyOfRepositoryArray.get(i);
            repositoryList.add(currentRepository);
            RepositoryDTO repositoryDTO = new RepositoryDTO();
            repositoryDTO.repositoryName = currentRepository.name;
            repositoryDTO.description = currentRepository.description;
            repositoryDTO.forkQuantity = currentRepository.forks_count;
            repositoryDTO.url = currentRepository.url;
            repositoryDTOS.add(repositoryDTO);
        }
    }

    private int verifyNumOfQueriedTopForRepos(int numOfQueriedTop, Repository[] repositories) {
        if (numOfQueriedTop > repositories.length) {
            System.err.println("There is no " + numOfQueriedTop + " repositories in the list." +
                    "All of the repositories will be shown.");
            numOfQueriedTop = repositories.length;
        }
        return numOfQueriedTop;
    }

    private void sortRepoListDescendingOrder(List<Repository> modifiableCopyOfRepositoryArray) {
        modifiableCopyOfRepositoryArray.sort(new Comparator<Repository>() {
            @Override
            public int compare(Repository o1, Repository o2) {
                return Integer.compare(o2.forks_count, o1.forks_count);
            }
        });
    }

    @Override
    public List<ContributorDTO> getTheTopContributorsOfRepo(List<Repository> repositoryList, int numOfQueriedTop) {

        numOfQueriedTop = veirfyNumOfQueriedTopForContributors(repositoryList, numOfQueriedTop);

        List<ContributorDTO> contributorDTOS = new ArrayList<>();
        List<Repository> modifiableCopyOfRepositoryList = new ArrayList<>(repositoryList);

        addTopContributorsToContributorDtosList(numOfQueriedTop, contributorDTOS, modifiableCopyOfRepositoryList);

        return contributorDTOS;
    }

    private void addTopContributorsToContributorDtosList(int numOfQueriedTop, List<ContributorDTO> contributorDTOS, List<Repository> modifiableCopyOfRepositoryList) {
        for (Repository currentRepository : modifiableCopyOfRepositoryList) {
            Contributor[] contributorsOfCurrentOrganization = gettAllContributorsAsArray(currentRepository.contributors_url);
            sortContributorsDescendingOrder(contributorsOfCurrentOrganization);
            for (int j = 0; j < numOfQueriedTop; j++) {
                ContributorDTO contributorDTO = new ContributorDTO();
                contributorDTO.username = contributorsOfCurrentOrganization[j].login;
                contributorDTO.contributionQuantity = contributorsOfCurrentOrganization[j].contributions;
                contributorDTO.repositoryName = currentRepository.name;
                Follower[] followers = gettAllFollowersAsArray(contributorsOfCurrentOrganization[j].followers_url);
                contributorDTO.followerQuantity = followers.length;
                contributorDTOS.add(contributorDTO);
            }
        }
    }

    private int veirfyNumOfQueriedTopForContributors(List<Repository> repositoryList, int numOfQueriedTop) {
        int minNumOfContributorInQueriedRepos = getMinNumOfContributorInQueriedRepos(repositoryList);

        if (minNumOfContributorInQueriedRepos < numOfQueriedTop) {
            System.err.println("There is no " + numOfQueriedTop + " contributors in the list." +
                    "All of the contributers will be shown.");
            numOfQueriedTop = minNumOfContributorInQueriedRepos;
        }
        return numOfQueriedTop;
    }

    private int getMinNumOfContributorInQueriedRepos(List<Repository> repositoryList) {
        Contributor[] contributorsOfFirstRepo = gettAllContributorsAsArray(repositoryList.get(0).contributors_url);
        int minNumOfContributorInQueriedRepos = contributorsOfFirstRepo.length;
        for (int i = 1; i < repositoryList.size(); i++) {
            Contributor[] contributorsOfCurrentRepo = gettAllContributorsAsArray(repositoryList.get(i).contributors_url);
            int numOfContributorInCurrentRepo = contributorsOfCurrentRepo.length;
            if (numOfContributorInCurrentRepo < minNumOfContributorInQueriedRepos) {
                minNumOfContributorInQueriedRepos = numOfContributorInCurrentRepo;
            }
        }
        return minNumOfContributorInQueriedRepos;
    }

    private void sortContributorsDescendingOrder(Contributor[] contributorsOfCurrentOrganization) {
        Arrays.sort(contributorsOfCurrentOrganization, new Comparator<Contributor>() {
            @Override
            public int compare(Contributor o1, Contributor o2) {
                return Integer.compare(o2.contributions, o1.contributions);
            }
        });
    }
}
