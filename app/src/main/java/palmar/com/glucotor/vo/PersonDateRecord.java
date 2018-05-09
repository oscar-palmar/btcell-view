package palmar.com.glucotor.vo;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Nicolas on 2/6/2018.
 */

public class PersonDateRecord implements Comparable<PersonDateRecord>{
    private Date recordDate;

    private double  beforeBreakfastGlucose;
    private PersonDateInsulinDosage beforeBreakfastInsulin;
    private double  beforeLunchGlucose;
    private PersonDateInsulinDosage beforeLunchInsulin;
    private double  beforeDinnerGlucose;
    private PersonDateInsulinDosage beforeDinnerInsulin;
    private double  beforeBedGlucose;
    private PersonDateInsulinDosage beforeBedInsulin;

    @Override
    public int compareTo(PersonDateRecord x) {
        return -this.getRecordDate().compareTo(x.getRecordDate());
    }

    public Date getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
    public double getBeforeBreakfastGlucose() {
        return beforeBreakfastGlucose;
    }
    public void setBeforeBreakfastGlucose(double beforeBreakfastGlucose) {
        this.beforeBreakfastGlucose = beforeBreakfastGlucose;
    }
    public PersonDateInsulinDosage getBeforeBreakfastInsulin() {
        return beforeBreakfastInsulin;
    }
    public void setBeforeBreakfastInsulin(PersonDateInsulinDosage beforeBreakfastInsulin) {
        this.beforeBreakfastInsulin = beforeBreakfastInsulin;
    }
    public double getBeforeLunchGlucose() {
        return beforeLunchGlucose;
    }
    public void setBeforeLunchGlucose(double beforeLunchGlucose) {
        this.beforeLunchGlucose = beforeLunchGlucose;
    }
    public PersonDateInsulinDosage getBeforeLunchInsulin() {
        return beforeLunchInsulin;
    }
    public void setBeforeLunchInsulin(PersonDateInsulinDosage beforeLunchInsulin) {
        this.beforeLunchInsulin = beforeLunchInsulin;
    }
    public double getBeforeDinnerGlucose() {
        return beforeDinnerGlucose;
    }
    public void setBeforeDinnerGlucose(double beforeDinnerGlucose) {
        this.beforeDinnerGlucose = beforeDinnerGlucose;
    }
    public PersonDateInsulinDosage getBeforeDinnerInsulin() {
        return beforeDinnerInsulin;
    }
    public void setBeforeDinnerInsulin(PersonDateInsulinDosage beforeDinnerInsulin) {
        this.beforeDinnerInsulin = beforeDinnerInsulin;
    }
    public double getBeforeBedGlucose() {
        return beforeBedGlucose;
    }
    public void setBeforeBedGlucose(double beforeBedGlucose) {
        this.beforeBedGlucose = beforeBedGlucose;
    }
    public PersonDateInsulinDosage getBeforeBedInsulin() {
        return beforeBedInsulin;
    }
    public void setBeforeBedInsulin(PersonDateInsulinDosage beforeBedInsulin) {
        this.beforeBedInsulin = beforeBedInsulin;
    }


}
