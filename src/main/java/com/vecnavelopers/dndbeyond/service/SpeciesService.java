package com.vecnavelopers.dndbeyond.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vecnavelopers.dndbeyond.model.SpeciesDetails;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class SpeciesService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        }

        return speciesDetails;
    }
}