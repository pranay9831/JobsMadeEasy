package Group3.JobsMadeEasy.job_post.hr.repository;

import Group3.JobsMadeEasy.job_post.hr.model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class JodPostCreationRepositoryImp extends JdbcDaoSupport implements IJobPostCreationRepository
{
    private final JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    @Autowired
    public JodPostCreationRepositoryImp(JdbcTemplate jdbcTemplate, DataSource dataSource)
    {
        this.jdbcTemplate = jdbcTemplate;
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
}
