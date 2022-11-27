package Group3.JobsMadeEasy.authentication.applicant.repository.login;

import Group3.JobsMadeEasy.authentication.applicant.model.Applicant;
import Group3.JobsMadeEasy.authentication.applicant.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ApplicantLoginRepositoryImp extends JdbcDaoSupport implements IApplicantLoginRepository{

    private final JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    @Autowired
    public ApplicantLoginRepositoryImp(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public Applicant checkLoginDetails(Login login) {
        System.out.println(login.getEmailId() + ">>>>" + login.getPassword());
        String sql = "SELECT * FROM applicant WHERE emailId = ? AND  password = ?";
        return getJdbcTemplate().queryForObject(sql, (rs, rwNumber) -> {
            Applicant applicant = new Applicant();
            applicant.setApplicantId(rs.getInt("applicantId"));
            applicant.setFirstName(rs.getString("firstName"));
            applicant.setLastName(rs.getString("lastName"));
            applicant.setPhoneNumber(rs.getString("phoneNumber"));
            applicant.setEmailId(rs.getString("emailId"));
            applicant.setPassword(rs.getString("password"));
            applicant.setCity(rs.getString("city"));
            applicant.setProvince(rs.getString("province"));
            applicant.setAddress(rs.getString("address"));
            applicant.setPostalCode(rs.getString("postalCode"));
            applicant.setApplicantStatus(rs.getBoolean("applicantStatus"));
            return applicant;
        },new Object[]{login.getEmailId(),login.getPassword()});
    }
}
