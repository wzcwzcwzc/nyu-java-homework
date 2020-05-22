package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Barry
 */
public class Bobsledder extends Sledder {

    private double bobsledderRecord;

    Bobsledder(String name, int age, String sledderColor, double bobsledderRecord){
        super(name, age, sledderColor);
        this.bobsledderRecord = bobsledderRecord;
    }

    public double getBobsledderRecord() {
        return bobsledderRecord;
    }

    public void setBobsledderRecord(double bobsledderRecord) {
        this.bobsledderRecord = bobsledderRecord;
    }

    public boolean equals(Sledder s){
        boolean res = false;
        if(this.getClass().equals(s.getClass())){
            res = super.getName().equals(s.getName())
                    && super.getAge() == s.getAge()
                    && super.getSledderColor().equals(s.getSledderColor())
                    && this.getBobsledderRecord() == ((Bobsledder) s).getBobsledderRecord();
        }
        return res;
    }
}
