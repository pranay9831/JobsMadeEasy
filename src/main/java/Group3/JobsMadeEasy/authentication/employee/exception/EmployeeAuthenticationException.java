package Group3.JobsMadeEasy.authentication.employee.exception;

public class EmployeeAuthenticationException extends Exception {

    private String message;

    public EmployeeAuthenticationException(String message) {
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
