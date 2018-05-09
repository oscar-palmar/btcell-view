package palmar.com.glucotor.vo;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Nicolas on 1/14/2018.
 */

public class DayRecord {
    private Date date;
    private double weakUpGlucose;
    private double amGlucose;
    private double pmGlucose;
    private double bedTimeGlucose;
    private Set<InsulinDosage> weakUpInsulin;
    private Set<InsulinDosage> lunchInsulin;
    private Set<InsulinDosage> dinnerInsulin;
    private Set<InsulinDosage> bedTimeInsulin;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeakUpGlucose() {
        return weakUpGlucose;
    }

    public void setWeakUpGlucose(double weakUpGlucose) {
        this.weakUpGlucose = weakUpGlucose;
    }

    public double getAmGlucose() {
        return amGlucose;
    }

    public void setAmGlucose(double amGlucose) {
        this.amGlucose = amGlucose;
    }

    public double getPmGlucose() {
        return pmGlucose;
    }

    public void setPmGlucose(double pmGlucose) {
        this.pmGlucose = pmGlucose;
    }

    public double getBedTimeGlucose() {
        return bedTimeGlucose;
    }

    public void setBedTimeGlucose(double bedTimeGlucose) {
        this.bedTimeGlucose = bedTimeGlucose;
    }

    public Set<InsulinDosage> getWeakUpInsulin() {
        return weakUpInsulin;
    }

    public void setWeakUpInsulin(Set<InsulinDosage> weakUpInsulin) {
        this.weakUpInsulin = weakUpInsulin;
    }

    public Set<InsulinDosage> getLunchInsulin() {
        return lunchInsulin;
    }

    public void setLunchInsulin(Set<InsulinDosage> lunchInsulin) {
        this.lunchInsulin = lunchInsulin;
    }

    public Set<InsulinDosage> getDinnerInsulin() {
        return dinnerInsulin;
    }

    public void setDinnerInsulin(Set<InsulinDosage> dinnerInsulin) {
        this.dinnerInsulin = dinnerInsulin;
    }

    public Set<InsulinDosage> getBedTimeInsulin() {
        return bedTimeInsulin;
    }

    public void setBedTimeInsulin(Set<InsulinDosage> bedTimeInsulin) {
        this.bedTimeInsulin = bedTimeInsulin;
    }

    private String getFormattedInsulin(Set<InsulinDosage> dosages) {
        if (dosages == null || dosages.isEmpty()) {
            return "";
        } else {
            String formattedInsulin = "";
            String separator = "";
            for (Iterator<InsulinDosage> iterator = dosages.iterator(); iterator.hasNext() ;  ) {
                InsulinDosage dosage = iterator.next();
                formattedInsulin += separator + Double.toString(dosage.getInsulinAmount()) + dosage.getInsulinType();
                separator = " + ";
            }
            return formattedInsulin;
        }
    }

    public String getFormattedWeakUpInsulin() {
        return getFormattedInsulin(weakUpInsulin);
    }
    public String getFormattedLunchInsulin() {
        return getFormattedInsulin(lunchInsulin);
    }
    public String getFormattedDinnerInsulin() {
        return getFormattedInsulin(dinnerInsulin);
    }
    public String getFormattedBedTimeInsulin() {
        return getFormattedInsulin(bedTimeInsulin);
    }

}
