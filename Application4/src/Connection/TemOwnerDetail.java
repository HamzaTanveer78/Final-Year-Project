package Connection;

import javafx.beans.property.StringProperty;

public class TemOwnerDetail {
    private final StringProperty UserName;
    private final StringProperty FirstName;
    private final StringProperty LastName;
    private final StringProperty CNIC;
    private final StringProperty DateOfBirth;
    private final StringProperty Address;
    private final StringProperty Status;
    private final StringProperty Province;
    private final StringProperty Tehsil;
    private final StringProperty District;
    private final StringProperty Area;
    private final StringProperty PhoneNo;
    private final StringProperty EmergencyPhoneNo;
    private final StringProperty Gender;
    private final StringProperty FatherName;
    private final StringProperty FatherCNIC;

    public TemOwnerDetail(StringProperty userName, StringProperty firstName, StringProperty lastName, StringProperty CNIC, StringProperty dateOfBirth, StringProperty address, StringProperty status, StringProperty province, StringProperty tehsil, StringProperty district, StringProperty area, StringProperty phoneNo, StringProperty emergencyPhoneNo, StringProperty gender, StringProperty fatherName, StringProperty fatherCNIC) {
        UserName = userName;
        FirstName = firstName;
        LastName = lastName;
        this.CNIC = CNIC;
        DateOfBirth = dateOfBirth;
        Address = address;
        Status = status;
        Province = province;
        Tehsil = tehsil;
        District = district;
        Area = area;
        PhoneNo = phoneNo;
        EmergencyPhoneNo = emergencyPhoneNo;
        Gender = gender;
        FatherName = fatherName;
        FatherCNIC = fatherCNIC;
    }
    public String getUserName() {
        return UserName.get();
    }

    public StringProperty userNameProperty() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName.set(userName);
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public String getLastName() {
        return LastName.get();
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public String getCNIC() {
        return CNIC.get();
    }

    public StringProperty CNICProperty() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC.set(CNIC);
    }

    public String getDateOfBirth() {
        return DateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.DateOfBirth.set(dateOfBirth);
    }

    public String getAddress() {
        return Address.get();
    }

    public StringProperty addressProperty() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address.set(address);
    }

    public String getStatus() {
        return Status.get();
    }

    public StringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getProvince() {
        return Province.get();
    }

    public StringProperty provinceProperty() {
        return Province;
    }

    public void setProvince(String province) {
        this.Province.set(province);
    }

    public String getTehsil() {
        return Tehsil.get();
    }

    public StringProperty tehsilProperty() {
        return Tehsil;
    }

    public void setTehsil(String tehsil) {
        this.Tehsil.set(tehsil);
    }

    public String getDistrict() {
        return District.get();
    }

    public StringProperty districtProperty() {
        return District;
    }

    public void setDistrict(String district) {
        this.District.set(district);
    }

    public String getArea() {
        return Area.get();
    }

    public StringProperty areaProperty() {
        return Area;
    }

    public void setArea(String area) {
        this.Area.set(area);
    }

    public String getPhoneNo() {
        return PhoneNo.get();
    }

    public StringProperty phoneNoProperty() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.PhoneNo.set(phoneNo);
    }

    public String getEmergencyPhoneNo() {
        return EmergencyPhoneNo.get();
    }

    public StringProperty emergencyPhoneNoProperty() {
        return EmergencyPhoneNo;
    }

    public void setEmergencyPhoneNo(String emergencyPhoneNo) {
        this.EmergencyPhoneNo.set(emergencyPhoneNo);
    }

    public String getGender() {
        return Gender.get();
    }

    public StringProperty genderProperty() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender.set(gender);
    }

    public String getFatherName() {
        return FatherName.get();
    }

    public StringProperty fatherNameProperty() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        this.FatherName.set(fatherName);
    }

    public String getFatherCNIC() {
        return FatherCNIC.get();
    }

    public StringProperty fatherCNICProperty() {
        return FatherCNIC;
    }

    public void setFatherCNIC(String fatherCNIC) {
        this.FatherCNIC.set(fatherCNIC);
    }
}
