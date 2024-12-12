package com.vecnavelopers.dndbeyond.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vecnavelopers.dndbeyond.model.ClassDetails;
import com.vecnavelopers.dndbeyond.model.Spellcasting;
import com.vecnavelopers.dndbeyond.model.Proficiency;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DndApiService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    // This method uses the fetched data to create a ClassDetails object
    public ClassDetails getClassDetails(String className) {
        // Fetch class data from the API
        String classDataJson = getClassDetailsFromApi(className);

        // Parse the JSON data into a JsonNode
        JsonNode classData = parseJsonToNode(classDataJson);

        // Create a new ClassDetails object to hold the relevant data
        ClassDetails classDetails = new ClassDetails();

        // Extract class information from the API response
        if (classData != null) {
            classDetails.setClassName(classData.path("name").asText());
            classDetails.setHitDie(classData.path("hit_die").asInt());
            classDetails.setClassLevels(classData.path("class_levels"));

            // Parse spellcasting using the new method
            JsonNode spellcastingNode = classData.path("spellcasting");
            Spellcasting spellcasting = parseSpellcasting(spellcastingNode);
            classDetails.setSpellcasting(spellcasting);


            classDetails.setSpellcastingAbility(classData.path("spellcasting_ability").asText());
            classDetails.setSpells(classData.path("spells"));
            classDetails.setProficiencyChoices(classData.path("proficiency_choices"));

            // Parse proficiencies
            classDetails.setProficiencies(parseProficiencies(classData.path("proficiencies")));

            classDetails.setSavingThrows(classData.path("saving_throws"));
            classDetails.setSubclasses(classData.path("subclasses"));
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


    public List<Proficiency> parseProficiencies(JsonNode proficienciesNode) {
        List<Proficiency> proficiencies = new ArrayList<>();
        if (proficienciesNode != null && proficienciesNode.isArray()) {
            for (JsonNode proficiencyNode : proficienciesNode) {
                Proficiency proficiency = new Proficiency();
                proficiency.setIndex(proficiencyNode.path("index").asText());
                proficiency.setName(proficiencyNode.path("name").asText());
                proficiency.setUrl(proficiencyNode.path("url").asText());
                proficiencies.add(proficiency);
            }
        }
        return proficiencies;
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
}
