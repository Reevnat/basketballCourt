public class court{
    private volatile boolean leaveCourt;
    private String name;

    void occupy(){
        while(!getLeaveCourt()){
            System.out.println(name + " are occupying the court ");
        }
        System.out.println(name + " have left court");
    }

    void leaveCourt(){
        setLeaveCourt(true);
    }

    void playOnCourt(String name){
        this.name = name;
        setLeaveCourt(false);
        occupy();
    }

    private  void setLeaveCourt(boolean decision){
        this.leaveCourt = decision;
    }

    private  boolean getLeaveCourt(){
        return leaveCourt;
    }
}
