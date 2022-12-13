package Group3.JobsMadeEasy.availability.controller;
import Group3.JobsMadeEasy.availability.dao.IAvailabilityDao;
import Group3.JobsMadeEasy.availability.model.Availability;
import Group3.JobsMadeEasy.availability.model.AvailabilityUserName;
import Group3.JobsMadeEasy.util.JobsMadeEasyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: It will handle all the Availability related requests.
 */
@Controller

public class AvailabilityController {


    private final Availability availability;


    private final AvailabilityUserName availabilityUserName;

    public AvailabilityController(IAvailabilityDao availabilityDao) {
        this.availability = new Availability(availabilityDao);
        this.availabilityUserName=new AvailabilityUserName(availabilityDao);
    }


    /**
     * @return It will return the availability home page.
     */
    @GetMapping("/availability_home_page")
    public String showHrHomePage()
    {
        return "availabilityHomePage";
    }

    /**
     * @param model :
     * @return It will return the availability home page.
     */
    @GetMapping("/add_availability_form")
    public String showAddAvailabilityForm(Model model)
    {
        Availability availability = new Availability();
        model.addAttribute("availability", availability);
        return "availability";
    }

    @PostMapping("/save/availability/{id}")
    public String createAvailability(@ModelAttribute Availability availability, @PathVariable int id) throws JobsMadeEasyException {

        return this.availability.createAvailability(availability, id);
    }

    @GetMapping("/get_all_availability")
    public List<AvailabilityUserName> getAllAvailability() throws JobsMadeEasyException, SQLException {
        return this.availability.getAllAvailability();

    }

    @GetMapping("/get_availability/{id}")
    public List<Availability> getMyAvailability(@PathVariable int id) throws JobsMadeEasyException, SQLException {

        return this.availability.getMyAvailability(id);
    }

    @DeleteMapping("/delete_availability/{id}")
    public boolean deleteAvailabilityById(@PathVariable int id) throws JobsMadeEasyException, SQLException {
            return this.availability.deleteAvailabilityById(id);

        }



    @GetMapping("/view_all_availability")
    public String viewAllAvailability(Model model) throws SQLException, JobsMadeEasyException {
        List<AvailabilityUserName> availabilities = null;
        try {
            availabilities = getAllAvailability();
            System.out.println(availabilities);
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
