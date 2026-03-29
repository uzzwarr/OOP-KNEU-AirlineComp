public class Crew {
    private String chiefPilot;
    private String coPilot;
    private String radioOperator;

    public Crew(String chiefPilot, String coPilot, String radioOperator) {
        this.chiefPilot = chiefPilot;
        this.coPilot = coPilot;
        this.radioOperator = radioOperator;
    }

    public String getChiefPilot() {
        return chiefPilot;
    }


    public String getRadioOperator() {
        return radioOperator;
    }

    public void setChiefPilot(String chiefPilot) {
        this.chiefPilot = chiefPilot;
    }

    public void setCoPilot(String coPilot) {
        this.coPilot = coPilot;
    }

    public void setRadioOperator(String radioOperator) {
        this.radioOperator = radioOperator;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "chiefPilot='" + chiefPilot + '\'' +
                ", coPilot='" + coPilot + '\'' +
                ", radioOperator='" + radioOperator + '\'' +
                '}';
    }
}
