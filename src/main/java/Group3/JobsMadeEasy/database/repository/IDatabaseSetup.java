package Group3.JobsMadeEasy.database.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseSetup {

    Connection getConnectionObject() throws IOException, ClassNotFoundException, SQLException;

    void closeDatabaseConnection();
}
