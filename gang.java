public class gang implements Runnable{
    private String name;
    private int turnId;
    private volatile boolean flagRaised;
    private volatile boolean threadRunning = true;
    court oneCourt;

    public gang(String name, int turnId, court oneCourt){
        this.name = name;
        this.turnId = turnId;
        this.oneCourt = oneCourt;

    }

    public void run(){
        System.out.println("Gang name is: " + getName());
        int i = 0;
        while(isThreadRunning()){
            if(i==0){
                System.out.println("Is the tread running: " + isThreadRunning() + " for " + getName());
                i++;
            }
            synchronized(oneCourt){
                if(isFlagRaised()) {
                    oneCourt.playOnCourt(getName());
                }
            }
        }
        System.out.println("End of thread");
    }

    public void startPlaying(){
        setFlagRaised(true);
    }
    public void stopPlaying(){
        setFlagRaised(false);
    }

    public boolean isLocked(){
        return Thread.holdsLock(oneCourt);
    }

    public String getName(){
        return name;
    }

    public boolean isFlagRaised() {
        return flagRaised;
    }

    public void setFlagRaised(boolean flagRaised) {
        this.flagRaised = flagRaised;
    }

    public boolean isThreadRunning() {
        return threadRunning;
    }

    public int getTurnId() {return turnId; }

    public void setTurnId(int turnId) {this.turnId = turnId; }

}
