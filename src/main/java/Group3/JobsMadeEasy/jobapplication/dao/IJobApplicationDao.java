package Group3.JobsMadeEasy.jobapplication.dao;

import Group3.JobsMadeEasy.jobapplication.model.JobApplication;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IJobApplicationDao {

    String createJobApplication(JobApplication jobApplication) throws JobsMadeEasyException, SQLException;
    Optional<JobApplication> getJobApplicationById(int id) throws SQLException, JobsMadeEasyException;
    List<JobApplication> getJobApplication() throws JobsMadeEasyException, SQLException;
    boolean deleteJobApplicationById(int id) throws JobsMadeEasyException;

}
