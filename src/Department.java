public enum Department {
    CSE("Computer Science and Enigineering"),
    ECE("Electronics"),
    MECH("Mechanical"),
    CIVIL("Civil"),
    BIO("BioTech");

    private final String fullName;

    Department(String fullName){
        this.fullName = fullName;

    }

    public String getFullName(){
        return fullName;
    }
    
    
}
