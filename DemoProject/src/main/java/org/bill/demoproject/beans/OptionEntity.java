package org.bill.demoproject.beans;

import java.io.Serializable;

public class OptionEntity implements Serializable {
    private String chooseTerm;
    private String fraction;


    public String getChooseTerm() {
        return chooseTerm;
    }

    public void setChooseTerm(String chooseTerm) {
        this.chooseTerm = chooseTerm;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "chooseTerm='" + chooseTerm + '\'' +
                ", fraction='" + fraction + '\'' +
                '}';
    }
}
