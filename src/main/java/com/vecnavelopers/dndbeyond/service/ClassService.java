package com.vecnavelopers.dndbeyond.service;

import org.springframework.stereotype.Service;
import com.vecnavelopers.dndbeyond.model.ClassDetails;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {
    public List<ClassDetails> getClassDescriptions() {
        List<ClassDetails> classDetailsList = new ArrayList<>();

        ClassDetails warrior = new ClassDetails();
        warrior.setClassName("Warrior");
        warrior.setDescription("A fierce fighter skilled in combat.");
        warrior.setImageUrl("/images/warrior.png");  // Assuming you have the image in a static folder

        ClassDetails mage = new ClassDetails();
        mage.setClassName("Mage");
        mage.setDescription("A master of magical spells and arcane arts.");
        mage.setImageUrl("/images/mage.png");

        ClassDetails rogue = new ClassDetails();
        rogue.setClassName("Rogue");
        rogue.setDescription("A stealthy and agile character who excels in trickery.");
        rogue.setImageUrl("/images/rogue.png");

        ClassDetails cleric = new ClassDetails();
        cleric.setClassName("Cleric");
        cleric.setDescription("A holy character who supports allies with healing powers.");
        cleric.setImageUrl("/images/cleric.png");

        // Add to the list
        classDetailsList.add(warrior);
        classDetailsList.add(mage);
        classDetailsList.add(rogue);
        classDetailsList.add(cleric);

        return classDetailsList;
    }
}