package Group3.JobsMadeEasy.authentication.applicant.repository.registration;

import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@Repository
public class ApplicantRegistrationRepositoryImp extends JdbcDaoSupport implements IApplicantRegistrationRepository {

    DataSource dataSource;

    @Autowired
    public ApplicantRegistrationRepositoryImp(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean createApplicant(Applicant applicant) {
        String sql = "INSERT INTO applicant " +
                "(applicantId, firstName, lastName, phoneNumber, emailId, password, city, province, address, postalCode)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[]{
                applicant.getApplicantId(), applicant.getFirstName(), applicant.getLastName()
                , applicant.getPhoneNumber(), applicant.getEmailId(), applicant.getPassword()
                , applicant.getCity(), applicant.getProvince(), applicant.getAddress()
                , applicant.getPostalCode()
        });
        return true;
    }

}
