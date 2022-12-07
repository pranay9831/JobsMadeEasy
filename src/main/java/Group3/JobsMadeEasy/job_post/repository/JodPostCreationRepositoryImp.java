package Group3.JobsMadeEasy.job_post.repository;

import Group3.JobsMadeEasy.job_post.model.JObPostMapper;
import Group3.JobsMadeEasy.job_post.model.JobPost;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JodPostCreationRepositoryImp extends JdbcDaoSupport implements IJobPostCreationRepository
{
    DataSource dataSource;

    public JodPostCreationRepositoryImp(DataSource dataSource)
    {
        this.dataSource =  dataSource;
    }

    @PostConstruct
    private void initialize()
    {
        setDataSource(dataSource);
    }

    @Override
    public boolean createJobPost(JobPost jobPost)
    {
        String sql = "INSERT INTO job_post " +
                "(job_post_id, job_title, salary, job_type, job_description, job_location, language_required)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        getJdbcTemplate().update(sql, new Object[]
                {
                jobPost.getJobPostId(), jobPost.getJobTitle(), jobPost.getSalary()
                , jobPost.getJobType(), jobPost.getJobDescription(), jobPost.getJobLocation()
                , jobPost.getLanguageRequirements()
                });
        return true;
    }

    @Override
    public List<JobPost> viewAllJobs() {
        String sql= "SELECT * FROM job_post";
        return getJdbcTemplate().query(
                sql,
                (rs, rowNum) ->
                        new JobPost(
                                rs.getInt("job_post_id"),
                                rs.getString("job_title"),
                                rs.getString("salary"),
                                rs.getString("job_type"),
                                rs.getString("job_description"),
                                rs.getString("job_location"),
                                rs.getString("language_required")
                        ));
}

    @Override
    public Optional<JobPost> viewAllJobsById(int id)
    {
        String sql= "SELECT * FROM job_post WHERE job_post_id= ?";
        return getJdbcTemplate().query(sql, new JObPostMapper(), id)
                .stream()
                .findFirst();
    }
}
