import business.abstracts.RepositoryManager;
import business.concretes.GithubRepositoryManager;

public class RepositoryManagerFactory {
    private static RepositoryManagerFactory factoryInstance;
    private RepositoryManager repositoryManager;

    public RepositoryManager getRepositoryManager() {
        if(repositoryManager == null)
        {
            repositoryManager = new GithubRepositoryManager();
        }
        return repositoryManager;
    }

    static {
        factoryInstance = new RepositoryManagerFactory();
    }

    public static RepositoryManagerFactory getFactoryInstance() {
        return factoryInstance;
    }

}
