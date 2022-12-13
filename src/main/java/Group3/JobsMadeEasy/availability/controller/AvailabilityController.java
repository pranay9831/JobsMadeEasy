package Group3.JobsMadeEasy.availability.controller;

import Group3.JobsMadeEasy.authentication.role.dao.IRoleDao;
import Group3.JobsMadeEasy.authentication.user.model.User;
import Group3.JobsMadeEasy.authentication.user.model.UserMapper;
import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;

import Group3.JobsMadeEasy.database.repository.DatabaseSetup;
import Group3.JobsMadeEasy.util.GenerateIdUtil;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Controller

public class AvailabilityController {
    private final IAvailabilityDao availabilityDao;
    private final DatabaseSetup databaseSetup;
    private final Connection connection;
    private final Statement statement;


    public AvailabilityController(IAvailabilityDao availabilityDao, DatabaseSetup databaseSetup) throws SQLException, IOException, ClassNotFoundException{
        this.availabilityDao = availabilityDao;
        this.databaseSetup = databaseSetup;
        this.connection= databaseSetup.getConnectionObject();
        this.statement=connection.createStatement();
    }


    @GetMapping("/availability_home_page")
    public String showHrHomePage(Model model)
    {
        return "availabilityHomePage";
    }


    @GetMapping("/add_availability_form")
    public String showAddAvailabilityForm(Model model)
    {
        Availability availability = new Availability();
        model.addAttribute("availability", availability);
        return "availability";
    }

    @PostMapping("/save/availability/{id}")
    public String createAvailability(@ModelAttribute Availability availability, @PathVariable int id) throws JobsMadeEasyException {

        if (availability == null)
        {
            throw new JobsMadeEasyException("no such availability found");
        }
        else
        {
            availability.setAvailabilityId(GenerateIdUtil.Object().generateRandomId());
            availability.setAvailableDays(availability.getAvailableDays());
            availability.setAvailableHours(availability.getAvailableHours());
            availability.setUserId(id);
        }
        try {
            String createUserQuery = availabilityDao.createAvailability(availability);
            int updatedRows = statement.executeUpdate(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            if(updatedRows > 0){
                return "availabilityHomePage";
            }
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
        }
        return "availability";

//        this.availabilityDao.createAvailability(availability);
//        return "index";
    }

    @GetMapping("/get_all_availability")
    public List<Availability> getAllAvailability() throws JobsMadeEasyException, SQLException {
        ResultSet rs = null;
        try {
            String viewAllAvailabilityQuery = availabilityDao.viewAllAvailability();
            rs = statement.executeQuery(viewAllAvailabilityQuery);
            List<Availability> availabilities = new LinkedList<>();
            return getAvailabilities(availabilities, rs);
        } catch (SQLException e) {
            throw new JobsMadeEasyException(e.getMessage());
        }finally {
            databaseSetup.closeDatabaseConnection();
            rs.close();
        }

//        List<Availability> availabilities= new ArrayList<>();
//        List<Availability> newList= this.availabilityDao.viewAllAvailability();
//        newList.forEach(x -> {availabilities.add(x);});
//        return availabilities;
    }

    @GetMapping("/get_availability/{id}")
    public List<Availability> getMyAvailability(@PathVariable int id) throws JobsMadeEasyException, SQLException {
        List<Availability> availabilities = null;
        ResultSet rs = null;
        if (id == 0) {
            throw new JobsMadeEasyException("No Availability found in DB");
        } else {
            try {
                String viewAvailabilityByIdQuery = availabilityDao.viewAvailabilityById(id);
                rs = statement.executeQuery(viewAvailabilityByIdQuery);
                availabilities = new LinkedList<>();
                return getAvailabilities(availabilities, rs);

            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                rs.close();
            }
        }
    }

    private List<Availability> getAvailabilities(List<Availability> availabilities, ResultSet rs) throws SQLException {
        while(rs.next()){
            int availabilityId = rs.getInt("availabilityId");
            int availableDays = rs.getInt("availableDays");
            String availableHours = rs.getString("availableHours");
            int userId = rs.getInt("userId");

            Availability availability = new Availability(availabilityId,availableDays,availableHours,userId);
            availabilities.add(availability);
        }
        return availabilities;
    }

    @DeleteMapping("/delete_availability/{id}")
    public boolean deleteAvailabilityById(@PathVariable int id) throws JobsMadeEasyException, SQLException {

            try {
                String deleteAvailabilityByIdQuery = availabilityDao.deleteAvailabilityById(id);
                statement.executeUpdate(deleteAvailabilityByIdQuery);
                return true;
            } catch (SQLException e) {
                throw new JobsMadeEasyException(e.getMessage());
            }finally {
                databaseSetup.closeDatabaseConnection();
                //statement.close();
            }
        }






//        try{
//            boolean b= this.availabilityDao.deleteAvailabilityById(id);
//        }
//
//        catch (IllegalArgumentException E){
//            System.out.println("illegal argument");
//
//        }
//        if (this.getMyAvailability(id).size() != 0)
//        {

//            return true;
//        }
//        else
//        {
//            throw new JobsMadeEasyException("No Availability found with given id.!!");
//        }
//        return true;


    @GetMapping("/view_all_availability")
    public String viewAllAvailability(Model model) throws SQLException, JobsMadeEasyException {
        List<Availability> availabilities = null;
        try {
            availabilities = getAllAvailability();
        } catch (JobsMadeEasyException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("availabilities", availabilities);
        return "viewAllAvailabilities";
    }



    @GetMapping("/view_my_availability/{id}")
    public String viewAvailabilityById(Model model, @PathVariable int id) throws JobsMadeEasyException, SQLException {

        List<Availability> availabilities = getMyAvailability(id);
        System.out.println(availabilities);
        model.addAttribute("availabilities", availabilities);
        return "viewMyAvailability";
    }

    @GetMapping("/delete_availability_by_id/{id}")
    public String deleteAvailabilityByUserId(Model model, @PathVariable int id) throws JobsMadeEasyException, SQLException {
        List<Availability> availability = getMyAvailability(id);
        System.out.println(availability);
        model.addAttribute("availability", availability);
        return "deleteAvailabilityById";
    }







}
