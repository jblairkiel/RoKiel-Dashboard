package custom_classes;

/**
 * Created by James on 1/28/2017.
 */

public class RestaurantSubmissionListItem {

    private int ID;
    private String RestaurantName;
    private String Location;
    private String Status;

     public RestaurantSubmissionListItem(int id, String resName, String resLocation, String resStatus){
        this.ID = id;
        this.RestaurantName = resName;
        this.Location = resLocation;
        this.Status = resStatus;
    }

    public int getID() {
        return ID;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public String getLocation() {
        return Location;
    }

    public String getStatus() {
        return Status;
    }
}
