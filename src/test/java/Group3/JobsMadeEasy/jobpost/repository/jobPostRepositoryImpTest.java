package Group3.JobsMadeEasy.jobpost.repository;

import Group3.JobsMadeEasy.jobpost.model.JobPost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class jobPostRepositoryImpTest {
    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    JobPostRepositoryImp jobPostRepositoryImp;

    @BeforeEach
    public void setup() {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void createJobPostTest()
    {
        final String sql = "INSERT INTO job_post " +
                "(job_post_id, job_title, salary, job_type, job_description, job_location, language_required)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        JobPost jobPost = new JobPost(11,"CSR","50,000","Full-Time","Great customer and provide best service",
                "Halifax","English");
        MapSqlParameterSource mapParameters = new MapSqlParameterSource();
        mapParameters.addValue("job_post_id",jobPost.getJobPostId());
        mapParameters.addValue("job_title",jobPost.getJobTitle());
        mapParameters.addValue("salary",jobPost.getSalary());
        mapParameters.addValue("job_type",jobPost.getJobType());
        mapParameters.addValue("job_description",jobPost.getJobDescription());
        mapParameters.addValue("job_location",jobPost.getJobLocation());
        mapParameters.addValue("language_required",jobPost.getLanguageRequirements());
        when(jdbcTemplate.update(Mockito.anyString(), Mockito.any(MapSqlParameterSource.class)))
                .thenReturn(1);
        jobPostRepositoryImp.createJobPost(jobPost);
        assertEquals(1,jdbcTemplate.update(sql,mapParameters));
    }

    @Test
    public void viewAllJobsTest()
    {
        List<JobPost> list = new ArrayList<JobPost>();
        JobPost jobPost1 = new JobPost(11,"CSR","50,000","Full-Time","Great customer and provide best service",
                "Halifax","English");
        JobPost jobPost2 = new JobPost(101,"Demo","20,000","Part-Time","Demo description",
                "Dartmouth","English, French");
        JobPost jobPost3 = new JobPost(1010,"CSR","100,000","Full-Time","Demo demo",
                "Tauro","English, French");
        list.add(jobPost1);
        list.add(jobPost2);
        list.add(jobPost3);
        when(jobPostRepositoryImp.viewAllJobs()).thenReturn(list);
        List<JobPost> jobPostList = jobPostRepositoryImp.viewAllJobs();
        assertEquals(3, jobPostList.size());
        verify(jobPostRepositoryImp, times(1)).viewAllJobs();
    }

    @Test
    public void viewJobByIdTest()
    {
        when(jobPostRepositoryImp.viewJobById(1011)).thenReturn(Optional.of(new JobPost(1011,"CSR","100,000","Full-Time","Demo demo",
                "Ontario","English, French")));
        Optional<JobPost> jobPost = jobPostRepositoryImp.viewJobById(1011);
        assertEquals(1011, jobPost.get().getJobPostId());
        assertEquals("CSR",jobPost.get().getJobTitle());
        assertEquals("100,000",jobPost.get().getSalary());
        assertEquals("Full-Time",jobPost.get().getJobType());
        assertEquals("Demo demo",jobPost.get().getJobDescription());
        assertEquals("Ontario",jobPost.get().getJobLocation());
        assertEquals("English, French",jobPost.get().getLanguageRequirements());
    }


    @Test
    public void deleteJobByIdTest() {
        when(jobPostRepositoryImp.deleteJobById(1)).thenReturn(true);
        boolean result = jobPostRepositoryImp.deleteJobById(1);
        assertEquals(result,true);
    }
}
