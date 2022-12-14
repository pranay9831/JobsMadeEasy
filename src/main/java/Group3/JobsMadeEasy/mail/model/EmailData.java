package Group3.JobsMadeEasy.mail.model;

import org.springframework.stereotype.Component;

@Component
public class EmailData {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
