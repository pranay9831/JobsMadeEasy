package Group3.JobsMadeEasy.availability.dao;

import Group3.JobsMadeEasy.authentication.role.model.Role;
import Group3.JobsMadeEasy.availability.model.Availability;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class AvailabilityMockDB {
    private final List<Availability> mockDB;

    public AvailabilityMockDB() {
        mockDB = new ArrayList<>();
        mockDB.add(new Availability(1,2, "Monday 4", 5));
        mockDB.add(new Availability(2, 1, "Monday 5", 3));
    }

//    public String createAvailability(Availability availability) {
//        Optional<Role> roleDB = getRole(role.getRoleId());
//        if(roleDB == null){
//            return false;
//        }
//        return true;
//    }
//
//    public Optional<Role> getRole(int id) {
//        ListIterator<Role> iterator = mockDB.listIterator();
//        while (iterator.hasNext()){
//            Role currentRole = iterator.next();
//            if(currentRole.getRoleId() == id){
//                return Optional.of(currentRole);
//            }
//        }
//        return null;
//    }
//
//    public List<Role> viewAllRoles(){
//        return mockDB;
//    }
//
//    public boolean deleteRoleById(int id) {
//        Optional<Role> role = getRole(id);
//        if(role == null){
//            return false;
//        }
//        return true;
//    }

}
