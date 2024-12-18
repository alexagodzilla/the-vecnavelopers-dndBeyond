package com.vecnavelopers.dndbeyond.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vecnavelopers.dndbeyond.model.*;
import com.vecnavelopers.dndbeyond.repository.ClassExtraDetailsRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ClassExtraDetailsRepository classExtraDetailsRepository;

    @Autowired
    public ClassService(ClassExtraDetailsRepository classExtraDetailsRepository) {
        this.classExtraDetailsRepository = classExtraDetailsRepository;
    }

    // This method fetches the class data from the API and returns the raw JSON response
    public String getClassDetailsFromApi(String className) {
        // Define the API URL
        String url = "https://www.dnd5eapi.co/api/classes/" + className;

        // Create the request
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .build();

        // Make the API call and process the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Return the raw JSON response body as a string
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return "";  // Return empty string or handle error as needed
        }
    }

    // Use the fetched data to create a ClassDetails object
    public ClassDetails getClassDetails(String className) {
        // Fetch class data from the API
        String classDataJson = getClassDetailsFromApi(className);

        // Parse the JSON data into a JsonNode
        JsonNode classData = parseJsonToNode(classDataJson);

        // Create a new ClassDetails object to hold the relevant data
        ClassDetails classDetails = new ClassDetails();

        // Extract class information from the API response
        if (classData != null) {
            classDetails.setClassIndex(classData.path("index").asText());
            classDetails.setClassName(classData.path("name").asText());
            classDetails.setHitDie(classData.path("hit_die").asInt());
            classDetails.setClassLevels(classData.path("class_levels"));
            classDetails.setSubclasses(classData.path("subclasses"));

            // Parse Spellcasting
            JsonNode spellcastingNode = classData.path("spellcasting");
            Spellcasting spellcasting = parseSpellcasting(spellcastingNode);
            classDetails.setSpellcasting(spellcasting);
            classDetails.setSpellcastingAbility(classData.path("spellcasting_ability").asText());
            classDetails.setSpells(classData.path("spells"));

            // Parse Proficiency Choices
            JsonNode proficiencyChoicesNode = classData.path("proficiency_choices");
            List<String> proficiencyNames = new ArrayList<>();
            if (proficiencyChoicesNode != null && proficiencyChoicesNode.isArray()) {
                for (JsonNode proficiencyChoice : proficiencyChoicesNode) {
                    JsonNode optionsNode = proficiencyChoice.path("from").path("options");
                    if (optionsNode != null && optionsNode.isArray()) {
                        for (JsonNode option : optionsNode) {
                            JsonNode itemNode = option.path("item");
                            String proficiencyName = itemNode.path("name").asText();
                            proficiencyNames.add(proficiencyName);
                        }
                    }
                }
            }
            classDetails.setProficiencyNames(proficiencyNames);

            // Parse Proficiencies (and Saving Throws)
            parseProficienciesAndSavingThrows(classData, classDetails);

            // Parse Starting Equipment
            List<StartingEquipment> startingEquipmentList = parseStartingEquipment(classData.path("starting_equipment"));
            classDetails.setStartingEquipment(startingEquipmentList);

            // Parse Starting Equipment Options
            List<StartingEquipmentOption> startingEquipmentOptions = parseStartingEquipmentOptions(classData.path("starting_equipment_options"));
            classDetails.setStartingEquipmentOptions(startingEquipmentOptions);
        }

        // Fetch additional data from the class_extra_details table
        ClassExtraDetails extraDetails = classExtraDetailsRepository.findByClassName(className);

        if (extraDetails != null) {
            classDetails.setClassTagline(extraDetails.getClassTagline());
            classDetails.setClassFlavour(extraDetails.getClassFlavour());
            classDetails.setClassDescription(extraDetails.getClassDescription());
            classDetails.setClassPrimaryAbility(extraDetails.getClassPrimaryAbility());
        } else {
            System.out.println("No extra details found for class: " + className);
        }

        return classDetails;
    }

    // Helper method to parse spellcasting
    public Spellcasting parseSpellcasting(JsonNode spellcastingNode) {
        Spellcasting spellcasting = new Spellcasting();
        if (spellcastingNode != null) {
            JsonNode abilityNode = spellcastingNode.path("spellcasting_ability");
            spellcasting.setAbility(abilityNode.path("name").asText("None"));

            List<String> info = new ArrayList<>();
            for (JsonNode infoNode : spellcastingNode.path("info")) {
                info.add(infoNode.path("desc").asText());
            }
            spellcasting.setInfo(info);
        }
        return spellcasting;
    }

    // Helper method to parse and set Proficiencies and Saving Throws
    public void parseProficienciesAndSavingThrows(JsonNode classData, ClassDetails classDetails) {
        List<Proficiency> proficienciesList = new ArrayList<>();
        List<String> savingThrowsList = new ArrayList<>();

        JsonNode proficienciesNode = classData.path("proficiencies");
        if (proficienciesNode != null && proficienciesNode.isArray()) {
            for (JsonNode proficiencyNode : proficienciesNode) {
                String index = proficiencyNode.path("index").asText();
                Proficiency proficiency = new Proficiency();
                proficiency.setIndex(index);
                proficiency.setName(proficiencyNode.path("name").asText());
                proficiency.setUrl(proficiencyNode.path("url").asText());

                if (index.startsWith("saving-throw-")) {
                    String shorthand = index.replace("saving-throw-", "").toUpperCase();
                    savingThrowsList.add(shorthand);
                } else {
                    proficienciesList.add(proficiency);
                }
            }
        }

        classDetails.setProficiencies(proficienciesList);
        classDetails.setSavingThrows(savingThrowsList);
    }

    // Helper method to parse and set starting equipment
    private List<StartingEquipment> parseStartingEquipment(JsonNode startingEquipmentNode) {
        List<StartingEquipment> equipmentList = new ArrayList<>();
        if (startingEquipmentNode != null && startingEquipmentNode.isArray()) {
            for (JsonNode equipmentNode : startingEquipmentNode) {
                StartingEquipment equipment = new StartingEquipment();
                equipment.setName(equipmentNode.path("equipment").path("name").asText());
                equipment.setQuantity(equipmentNode.path("quantity").asInt());
                equipment.setUrl(equipmentNode.path("equipment").path("url").asText());
                equipmentList.add(equipment);
            }
        }
        return equipmentList;
    }

    // Helper method to parse Optional Starting Equipment
    private List<StartingEquipmentOption> parseStartingEquipmentOptions(JsonNode optionsNode) {
        List<StartingEquipmentOption> equipmentOptionsList = new ArrayList<>();
        if (optionsNode != null && optionsNode.isArray()) {
            for (JsonNode optionNode : optionsNode) {
                StartingEquipmentOption equipmentOption = new StartingEquipmentOption();
                equipmentOption.setDesc(optionNode.path("desc").asText());
                equipmentOption.setChoose(optionNode.path("choose").asInt());
                equipmentOption.setType(optionNode.path("type").asText());

                JsonNode fromNode = optionNode.path("from");
                if (fromNode != null) {
                    JsonNode optionsArrayNode = fromNode.path("options");
                    if (optionsArrayNode != null && optionsArrayNode.isArray()) {
                        List<StartingEquipmentOptionItem> optionItems = new ArrayList<>();
                        for (JsonNode itemNode : optionsArrayNode) {
                            StartingEquipmentOptionItem optionItem = new StartingEquipmentOptionItem();
                            JsonNode optionDetailsNode = itemNode.path("item").path("name");
                            if (!optionDetailsNode.isMissingNode()) {
                                optionItem.setName(optionDetailsNode.asText());
                                optionItem.setUrl(itemNode.path("item").path("url").asText());
                            }
                            optionItems.add(optionItem);
                        }
                        equipmentOption.setOptions(optionItems);
                    }
                }

                equipmentOptionsList.add(equipmentOption);
            }
        }
        return equipmentOptionsList;
    }


    // Helper method to parse JSON string into JsonNode
    private JsonNode parseJsonToNode(String jsonData) {
        try {
            return objectMapper.readTree(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // Handle error, could also throw a custom exception
        }
    }

    public List<ClassSummary> getAllClasses() {
        // Fetch all classes from the database
        List<ClassExtraDetails> extraDetailsList = classExtraDetailsRepository.findAll();

        // Create a list to hold the Class Summary objects
        List<ClassSummary> classSummaryList = new ArrayList<>();
        for (ClassExtraDetails classExtraDetails : extraDetailsList) {
            ClassSummary classSummary = new ClassSummary();
            classSummary.setClassName(classExtraDetails.getClassName());
            classSummary.setClassTagline(classExtraDetails.getClassTagline());
            classSummary.setClassIcon(classExtraDetails.getClassIcon());
            classSummaryList.add(classSummary);
        }
        return classSummaryList;
    }


}
