package palmar.com.glucotor.vo;

import java.util.Date;

/**
 * Created by Nicolas on 2/6/2018.
 */

public class PersonDateInsulinDosage {
    private String personId;
    private Date recordDate;
    private String dosageCode;
    private double glucoseReading;
    private String insulinType1;
    private double amount1;
    private String insulinType2;
    private double amount2;

    private String insulinType3;
    private double amount3;
    private String insulinType4;
    private double amount4;
    private String insulinType5;
    private double amount5;

    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public Date getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
    public String getDosageCode() {
        return dosageCode;
    }
    public void setDosageCode(String dosageCode) {
        this.dosageCode = dosageCode;
    }
    public String getInsulinType1() {
        return insulinType1;
    }
    public void setInsulinType1(String insulinType1) {
        this.insulinType1 = insulinType1;
    }
    public double getAmount1() {
        return amount1;
    }
    public void setAmount1(double amount1) {
        this.amount1 = amount1;
    }
    public String getInsulinType2() {
        return insulinType2;
    }
    public void setInsulinType2(String insulinType2) {
        this.insulinType2 = insulinType2;
    }
    public double getAmount2() {
        return amount2;
    }
    public void setAmount2(double amount2) {
        this.amount2 = amount2;
    }
    public String getInsulinType3() {
        return insulinType3;
    }
    public void setInsulinType3(String insulinType3) {
        this.insulinType3 = insulinType3;
    }
    public double getAmount3() {
        return amount3;
    }
    public void setAmount3(double amount3) {
        this.amount3 = amount3;
    }
    public String getInsulinType4() {
        return insulinType4;
    }
    public void setInsulinType4(String insulinType4) {
        this.insulinType4 = insulinType4;
    }
    public double getAmount4() {
        return amount4;
    }
    public void setAmount4(double amount4) {
        this.amount4 = amount4;
    }
    public String getInsulinType5() {
        return insulinType5;
    }
    public void setInsulinType5(String insulinType5) {
        this.insulinType5 = insulinType5;
    }
    public double getAmount5() {
        return amount5;
    }
    public void setAmount5(double amount5) {
        this.amount5 = amount5;
    }
    public double getGlucoseReading() {
        return glucoseReading;
    }
    public void setGlucoseReading(double glucoseReading) {
        this.glucoseReading = glucoseReading;
    }

    public String getFormattedInsulinDosage() {
        String formattedInsulin = "";
        if (insulinType1 == null || insulinType1.trim().length() == 0) {
            return formattedInsulin;
        }
        formattedInsulin = amount1+insulinType1;
        if (insulinType2 != null && insulinType2.trim().length() > 0) {
            formattedInsulin += "+" + amount2+insulinType2;
        }
        if (insulinType3 != null && insulinType3.trim().length() > 0) {
            formattedInsulin += "+" + amount3+insulinType3;
        }
        if (insulinType4 != null && insulinType4.trim().length() > 0) {
            formattedInsulin += "+" + amount4+insulinType4;
        }
        if (insulinType5 != null && insulinType5.trim().length() > 0) {
            formattedInsulin += "+" + amount5+insulinType5;
        }
        return formattedInsulin;
    }
}

