package Group3.JobsMadeEasy.jobpost.repository;
import Group3.JobsMadeEasy.jobpost.model.JobPost;
import org.springframework.stereotype.Repository;
import static Group3.JobsMadeEasy.jobpost.repository.JobPostDaoConstant.*;

@Repository
public class JobPostDaoImp implements IJobPostDao
{
    private static JobPostDaoImp obj;

    public JobPostDaoImp()
    {
    }

    public static JobPostDaoImp getInstance() {
        if (obj == null) {
            obj = new JobPostDaoImp();
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
    public String viewAllJobs()
    {
        return "SELECT * FROM " + JOB_POST + ";";
    }

    @Override
    public String viewJobById(int id)
    {
        return "SELECT * FROM "+JOB_POST+" WHERE "+JOB_POST_ID+" = " + id + ";";
    }

    @Override
    public String deleteJobById(int id) {
        return "DELETE FROM "+JOB_POST+" WHERE "+JOB_POST_ID+" = " + id + ";";
    }

//    @Override
//    public String updateJobPost(JobPost jobPost, int id) {
//        return "UPDATE "+ JOB_POST + " SET " +
//                JOB_TITLE + " = \"" + jobPost.getJobTitle() + "\", " +
//                SALARY + " = \"" + jobPost.getSalary() + "\", " +
//                JOB_TYPE + " = \"" + jobPost.getJobType() + "\" ," +
//                JOB_DESCRIPTION + " = \"" + jobPost.getJobDescription() + "\", " +
//                JOB_LOCATION + " = \"" + jobPost.getJobLocation() + "\", " +
//                LANGUAGE_REQUIREMENTS + " = \"" + jobPost.getLanguageRequirements() + "\" " +
//                "WHERE " + JOB_POST_ID + " = \"" + id + "\"";
//    }
}

