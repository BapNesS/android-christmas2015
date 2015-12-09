package christmas2015.android.baptistecarlier.com.christmas2015;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BapNesS on 09/12/15.
 */
public class People {

    private String name;
    private String phoneNumber;

    private People offerTo;
    private List<People> cantOfferTo;

    public People(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.offerTo = null;
        this.cantOfferTo = new ArrayList<People>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public People getOfferTo() {
        return offerTo;
    }

    public void setOfferTo(People luckyOne) {
        this.offerTo = luckyOne;
    }

    public List<People> getCantOfferTo() {
        return cantOfferTo;
    }

    public void addCantOfferTo(People badLuckBrian) {
        if ( this.cantOfferTo == null ) {
            this.cantOfferTo = new ArrayList<People>();
        }
        if ( !this.cantOfferTo.contains(badLuckBrian) ) {
            this.cantOfferTo.add(badLuckBrian);
        }
    }
}
