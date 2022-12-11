package Group3.JobsMadeEasy.authentication.role.controller;


import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.authentication.role.model.RoleMapper;
import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoleController {

    private final IRoleDao roleDao;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;

    public RoleController(IRoleDao roleDao, DatabaseSetup databaseSetup) throws SQLException, IOException, ClassNotFoundException {
        this.roleDao = roleDao;
        this.databaseSetup = databaseSetup;
        this.connection = databaseSetup.getConnectionObject();
        this.statement = connection.createStatement();
    }

    @PostMapping("/add_role")
    public boolean createRole(@ModelAttribute Role role) throws JobsMadeEasyException {
        if (role == null) {
            throw new JobsMadeEasyException("user register details not found..");
        } else {
            role.setRoleId(role.getRoleId());
            role.setRoleName(role.getRoleName());
        }
        try {
            String createRoleQuery = roleDao.createRole(role);
            int updatedRows = statement.executeUpdate(createRoleQuery, Statement.RETURN_GENERATED_KEYS);
            if(updatedRows > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
        }
        return true;
    }

    @GetMapping("/get_role_by_id/{id}")
    public Optional<Role> getRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        if (id == 0) {
            throw new JobsMadeEasyException("No role found in DB");
        } else {
            try {
                String getRoleByIdQuery = roleDao.getRole(id);
                rs = statement.executeQuery(getRoleByIdQuery);
                if(rs.next()){
                    return Optional.ofNullable(new RoleMapper().mapRow(rs, rs.getRow()));
                }
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                rs.close();
            }
        }
        return null;
    }

    @GetMapping("/get_all_roles")
    public List<Role> getAllRoles() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String getRolesQuery = roleDao.viewAllRoles();
            rs = statement.executeQuery(getRolesQuery);
            List<Role> roles = new LinkedList<>();
            while(rs.next()){
                int i = rs.getInt("roleId");
                String roleName = rs.getString("roleName");
                Role role = new Role(i,roleName);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }
    }

    @DeleteMapping("/delete_role/{id}")
    public boolean deleteRoleById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        if (this.getRoleById(id).isPresent())
        {
            try {
                String deleteRoleByIdQuery = roleDao.deleteRoleById(id);
                statement.executeUpdate(deleteRoleByIdQuery);
                return true;
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                statement.close();
            }
        }
        else
        {
            throw new JobsMadeEasyException("No job post found with given id.!!");
        }
    }

}
