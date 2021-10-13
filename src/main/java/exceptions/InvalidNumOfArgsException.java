package exceptions;

public class InvalidNumOfArgsException extends Exception {
    public InvalidNumOfArgsException() {
        System.err.println("Args must be valid \n" +
                "First parameter : organizationName(String)\n" +
                "Second parameter : numOfMostForkedRepos(int)\n" +
                "Third parameter : numOfTopContributors");
    }
}
