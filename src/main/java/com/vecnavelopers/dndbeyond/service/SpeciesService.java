package com.vecnavelopers.dndbeyond.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vecnavelopers.dndbeyond.model.SpeciesDetails;
import com.vecnavelopers.dndbeyond.model.SpeciesExtraDetails;
import com.vecnavelopers.dndbeyond.model.SpeciesSummary;
import com.vecnavelopers.dndbeyond.repository.SpeciesExtraDetailsRepository;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class SpeciesService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final SpeciesExtraDetailsRepository speciesExtraDetailsRepository;

    @Autowired
    public SpeciesService(SpeciesExtraDetailsRepository speciesExtraDetailsRepository) {
        this.speciesExtraDetailsRepository = speciesExtraDetailsRepository;
    }

    // This method fetches the species data from the API and returns the raw JSON response
    public String getSpeciesDetailsFromApi(String speciesName) {
        // Define the API URL
        String url = "https://www.dnd5eapi.co/api/races/" + speciesName;

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

    // Use the fetched data to create a SpeciesDetails object
    public SpeciesDetails getSpeciesDetails(String speciesName) {
        // Fetch species data from the API
        String speciesDataJson = getSpeciesDetailsFromApi(speciesName);

        // Parse the JSON data into a JsonNode
        JsonNode speciesData = parseJsonToNode(speciesDataJson);

        // Create a new SpeciesDetails object to hold the relevant data
        SpeciesDetails speciesDetails = new SpeciesDetails();

        // Extract species information from the API response
        if (speciesData != null) {
            speciesDetails.setSpeciesName(speciesData.path("name").asText());
            speciesDetails.setSpeciesSpeed(speciesData.path("speed").asInt());
            speciesDetails.setSpeciesSize(speciesData.path("size").asText());
            speciesDetails.setSpeciesSizeDescription(speciesData.path("size_description").asText());
        }

        // Fetch additional data from the species_extra_details table
        SpeciesExtraDetails extraDetails = speciesExtraDetailsRepository.findBySpeciesName(speciesName);

        if (extraDetails != null) {
            speciesDetails.setSpeciesTagline(extraDetails.getSpeciesTagline());
            speciesDetails.setSpeciesFlavour(extraDetails.getSpeciesFlavour());
            speciesDetails.setSpeciesDescription(extraDetails.getSpeciesDescription());
            speciesDetails.setSpeciesImage(extraDetails.getSpeciesImage());
        }

        return speciesDetails;
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

    public List<SpeciesSummary> getAllSpecies() {
        // Fetch all species from the database
        List<SpeciesExtraDetails> extraDetailsList = speciesExtraDetailsRepository.findAll();

        // Create a list to hold the Species Summary objects
        List<SpeciesSummary> speciesSummaryList = new ArrayList<>();
        for (SpeciesExtraDetails speciesExtraDetails : extraDetailsList) {
            SpeciesSummary speciesSummary = new SpeciesSummary();
            speciesSummary.setSpeciesName(speciesExtraDetails.getSpeciesName());
            speciesSummary.setSpeciesTagline(speciesExtraDetails.getSpeciesTagline());
            speciesSummaryList.add(speciesSummary);
        }
        return speciesSummaryList;
    }
}