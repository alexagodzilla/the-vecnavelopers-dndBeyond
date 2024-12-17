package com.vecnavelopers.dndbeyond.model;

import java.util.List;

public class StartingEquipmentOption {
    private String desc;
    private int choose;
    private String type;
    private List<StartingEquipmentOptionItem> options;

    // Getters and setters

    public String getDesc() { return desc; }

    public void setDesc(final String desc) { this.desc = desc; }

    public int getChoose() { return choose; }

    public void setChoose(final int choose) { this.choose = choose; }

    public String getType() { return type; }

    public void setType(final String type) { this.type = type; }

    public List<StartingEquipmentOptionItem> getOptions() { return options; }

    public void setOptions(List<StartingEquipmentOptionItem> options) { this.options = options; }
}
