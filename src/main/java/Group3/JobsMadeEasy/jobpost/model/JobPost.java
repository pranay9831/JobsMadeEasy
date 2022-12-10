package Group3.JobsMadeEasy.jobpost.model;

public class JobPost
{
    private int jobPostId;
    private String jobTitle;
    private String salary;
    private String jobType;
    private String jobDescription;
    private String jobLocation;
    private String languageRequirements;

    public JobPost(int jobPostId, String jobTitle, String salary, String jobType, String jobDescription,
                   String jobLocation, String languageRequirements)
    {
        this.jobPostId = jobPostId;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.jobType = jobType;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.languageRequirements = languageRequirements;
    }

    public JobPost()
    {

    }

    public int getJobPostId()
    {
        return jobPostId;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public String getSalary()
    {
        return salary;
    }

    public String getJobType()
    {
        return jobType;
    }

    public String getJobDescription()
    {
        return jobDescription;
    }

    public String getJobLocation()
    {
        return jobLocation;
    }

    public String getLanguageRequirements()
    {
        return languageRequirements;
    }

    public void setJobPostId(int jobPostId)
    {
        this.jobPostId = jobPostId;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public void setSalary(String salary)
    {
        this.salary = salary;
    }

    public void setJobType(String jobType)
    {
        this.jobType = jobType;
    }

    public void setJobDescription(String jobDescription)
    {
        this.jobDescription = jobDescription;
    }

    public void setJobLocation(String jobLocation)
    {
        this.jobLocation = jobLocation;
    }

    public void setLanguageRequirements(String languageRequirements) {
        this.languageRequirements = languageRequirements;
    }

    @Override
    public String toString() {
        return "JobPost{" +
                "jobPostId=" + jobPostId +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary='" + salary + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", languageRequirements='" + languageRequirements + '\'' +
                '}';
    }
}
