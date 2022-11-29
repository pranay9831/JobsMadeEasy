package Group3.JobsMadeEasy.job_post.hr.exception;

public class JobPostException extends Exception
{
    private final String errorMessage;

    public JobPostException(String errorMessage)
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getMessage()
    {
        return errorMessage;
    }

    @Override
    public String toString()
    {
        return "JobPostException" +
                "message='" + errorMessage + '\'' +
                '}';
    }
}
