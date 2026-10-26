package Model;
import java.sql.Date;

public class StaffModel {
        private int staffId;
        private String staffName;
        private String email;
        private String username;
        private String password;
        private String phoneNumber;
        private Date joiningDate;
        private String status;

    public StaffModel() {}

        
        
        public StaffModel(int staffId, String staffName, String email, String username, String password,
                String phoneNumber, Date joiningDate, String status) {
            this.staffId = staffId;
            this.staffName = staffName;
            this.email = email;
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.joiningDate = joiningDate;
            this.status = status;
        }


        public int getStaffId() {
            return staffId;
        }


        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }


        public String getStaffName() {
            return staffName;
        }


        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }


        public String getEmail() {
            return email;
        }


        public void setEmail(String email) {
            this.email = email;
        }


        public String getUsername() {
            return username;
        }


        public void setUsername(String username) {
            this.username = username;
        }


        public String getPassword() {
            return password;
        }


        public void setPassword(String password) {
            this.password = password;
        }


        public String getPhoneNumber() {
            return phoneNumber;
        }


        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }


        public Date getJoiningDate() {
            return joiningDate;
        }


        public void setJoiningDate(Date joiningDate) {
            this.joiningDate = joiningDate;
        }


        public String getStatus() {
            return status;
        }


        public void setStatus(String status) {
            this.status = status;
        }
}
