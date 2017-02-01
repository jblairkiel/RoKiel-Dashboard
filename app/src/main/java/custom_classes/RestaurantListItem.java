package custom_classes;

/**
 * Created by James on 1/28/2017.
 */

public class RestaurantListItem {

    private int ID;
    private String RestaurantName;
    private String Address;
    private String Type;
    private String Price;
    private String SpinnerGroup;

     public RestaurantListItem(int id, String resName, String resAddress, String resType, String resPrice, String resSpinnerGroup){
        this.ID = id;
        this.RestaurantName = resName;
        this.Address = resAddress;
        this.Type = resType;
        this.Price = resPrice;
        this.SpinnerGroup = resSpinnerGroup;
    }

    public int getID() {
        return ID;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public String getAddress() {
        return Address;
    }

    public String getType() {
        return Type;
    }

    public String getPrice() {
        return Price;
    }

    public String getSpinnerGroup() {
        return SpinnerGroup;
    }
}
