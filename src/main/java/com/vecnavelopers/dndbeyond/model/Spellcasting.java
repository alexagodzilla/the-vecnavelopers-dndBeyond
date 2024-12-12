package com.vecnavelopers.dndbeyond.model;

import java.util.List;

public class Spellcasting {
    private String ability;
    private List<String> info;

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
