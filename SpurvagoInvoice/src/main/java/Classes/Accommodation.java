package Classes;

import java.math.BigDecimal;
import java.sql.Date;

public class Accommodation {

    private String firstName;
    private String lastName;
    private String roomLabel;
    private Date startDate;
    private Date endDate;
    private String pesel;
    private String price;

    public Accommodation(String firstName, String lastName, String roomLabel, Date startDate, Date endDate, String pesel, String price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomLabel = roomLabel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pesel = pesel;
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRoomLabel() {
        return roomLabel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPrice() {
        return price;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
