package Group3.JobsMadeEasy.jobpost.repository;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import Group3.JobsMadeEasy.jobpost.model.JobPostMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JobPostRepositoryImp extends JdbcDaoSupport implements IJobPostRepository
{
    DataSource dataSource;

    public JobPostRepositoryImp(DataSource dataSource)
    {
        this.dataSource =  dataSource;
    }

    @PostConstruct
    private void initialize()
    {
        setDataSource(dataSource);
    }

    @Override
    public JobPost createJobPost(JobPost jobPost)
    {
        String sql = "INSERT INTO jobPost " +
                "(jobPostId, jobTitle, salary, jobType, jobDescription, jobLocation, languageRequired)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]
                {
                jobPost.getJobPostId(), jobPost.getJobTitle(), jobPost.getSalary()
                , jobPost.getJobType(), jobPost.getJobDescription(), jobPost.getJobLocation()
                , jobPost.getLanguageRequirements()
                });
        return jobPost;
    }

    @Override
    public List<JobPost> viewAllJobs() {
        String sql= "SELECT * FROM jobPost";
        return getJdbcTemplate().query(
                sql,
                (rs, rowNum) ->
                        new JobPost(
                                rs.getInt("jobPostId"),
                                rs.getString("jobTitle"),
                                rs.getString("salary"),
                                rs.getString("jobType"),
                                rs.getString("jobDescription"),
                                rs.getString("jobLocation"),
                                rs.getString("languageRequired")
                        ));
}

    @Override
    public Optional<JobPost> viewJobById(int id)
    {
        String sql= "SELECT * FROM jobPost WHERE jobPostId= ?";
        return getJdbcTemplate().query(sql, new JobPostMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public boolean deleteJobById(int id)
    {
        String sql = "DELETE FROM jobPost WHERE jobPostId= ?";
        Object[] args = new Object[] {id};
        return getJdbcTemplate().update(sql,args) ==1;
    }
}
