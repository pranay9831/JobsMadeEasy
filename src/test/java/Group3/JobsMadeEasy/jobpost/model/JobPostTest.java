package Group3.JobsMadeEasy.jobpost.model;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

public class JobPostTest
{
    @Test
    public void testJobPostClass()
    {
        JobPost jobPost= Mockito.spy(new JobPost());
        jobPost.getJobPostId();
        jobPost.getJobTitle();
        jobPost.getSalary();
        jobPost.getJobType();
        jobPost.getJobDescription();
        jobPost.getJobLocation();
        jobPost.getLanguageRequirements();

        jobPost.setJobPostId(1);
        jobPost.setJobTitle("CSR");
        jobPost.setSalary("50,000");
        jobPost.setJobType("Full-Time");
        jobPost.setJobDescription("Work with customer and provide best service.");
        jobPost.setJobLocation("Halifax");
        jobPost.setLanguageRequirements("English, French");

        assertEquals(jobPost.getJobPostId(),1);
        assertEquals(jobPost.getJobTitle(),"CSR");
        assertEquals(jobPost.getSalary(),"50,000");
        assertEquals(jobPost.getJobType(),"Full-Time");
        assertEquals(jobPost.getJobDescription(),"Work with customer and provide best service.");
        assertEquals(jobPost.getJobLocation(),"Halifax");
        assertEquals(jobPost.getLanguageRequirements(),"English, French");
    }
}
