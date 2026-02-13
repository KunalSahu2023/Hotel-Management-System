package hotel_management.model;

public class Staff {
        private int staffId;
        private String firstName;
        private String lastName;
        private String role;
        private String email;
        private String phoneNumber;

        public Staff() {}

        public Staff(int staffId, String firstName, String lastName, String role, String email, String phoneNumber) {
            this.staffId = staffId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.role = role;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName
                    + ", role=" + role + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
        }
    }

