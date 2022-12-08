package Group3.JobsMadeEasy.authentication.user.model;

public class Login {

    private String emailId;
    private String password;

    public Login(){}

    public Login(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}