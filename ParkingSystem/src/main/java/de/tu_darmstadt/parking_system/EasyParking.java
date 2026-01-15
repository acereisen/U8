package de.tu_darmstadt.parking_system;

public class EasyParking implements Strategy{
    private double duration;

    public EasyParking(double duration){
        this.duration = duration;
    }
    @Override
    public int calculation(){
        if(duration<5){
            return 0;
        }
        return (int) (2 * Math.ceil(duration/30));
    }

}
