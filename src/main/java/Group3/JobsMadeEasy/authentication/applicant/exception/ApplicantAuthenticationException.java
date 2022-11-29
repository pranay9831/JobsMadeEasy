package Group3.JobsMadeEasy.authentication.applicant.exception;

public class ApplicantAuthenticationException extends Exception {

    private String message;

    public ApplicantAuthenticationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApplicantException{" +
                "message='" + message + '\'' +
                '}';
    }
}
