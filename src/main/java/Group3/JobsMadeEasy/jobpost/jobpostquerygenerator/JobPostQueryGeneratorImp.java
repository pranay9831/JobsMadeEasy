package Group3.JobsMadeEasy.jobpost.jobpostquerygenerator;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import org.springframework.stereotype.Repository;
import static Group3.JobsMadeEasy.jobpost.jobpostquerygenerator.JobPostConstant.*;

@Repository
public class JobPostQueryGeneratorImp implements IJobPostQueryGenerator {
    private static JobPostQueryGeneratorImp obj;

    public JobPostQueryGeneratorImp() {
    }

    public static JobPostQueryGeneratorImp getInstance() {
        if (obj == null) {
            obj = new JobPostQueryGeneratorImp();
        }
        return obj;
    }

    @Override
    public String createJobPost(JobPost jobPost) {
        return "INSERT INTO " + JOB_POST +
                "(" + JOB_POST_ID + "," + JOB_TITLE + ","
                + SALARY + "," + JOB_TYPE + ","
                + JOB_DESCRIPTION + ","
                + JOB_LOCATION + "," + LANGUAGE_REQUIREMENTS + ")" +
                "VALUES (" +
                "\"" + jobPost.getJobPostId() + "\"," +
                "\"" + jobPost.getJobTitle() + "\"," +
                "\"" + jobPost.getSalary() + "\"," +
                "\"" + jobPost.getJobType() + "\"," +
                "\"" + jobPost.getJobDescription() + "\"," +
                "\"" + jobPost.getJobLocation() + "\"," +
                "\"" + jobPost.getLanguageRequirements() + "\"" +
                ");";
    }

    @Override
    public String viewAllJobs() {
        return "SELECT * FROM " + JOB_POST + ";";
    }

    @Override
    public String viewJobById(int id) {
        return "SELECT * FROM " + JOB_POST + " WHERE " + JOB_POST_ID + " = " + id + ";";
    }

    @Override
    public String deleteJobById(int id) {
        return "DELETE FROM " + JOB_POST + " WHERE " + JOB_POST_ID + " = " + id + ";";
    }
}

