package org.bill.demoproject.beans;

import java.io.Serializable;

public class OptionEntity implements Serializable {
    private String chooseTerm;

    public String getChooseTerm() {
        return chooseTerm;
    }

    public void setChooseTerm(String chooseTerm) {
        this.chooseTerm = chooseTerm;
    }

    @Override
    public String toString() {
        return "OptionEntity{" +
                "chooseTerm='" + chooseTerm + '\'' +
                '}';
    }
}
