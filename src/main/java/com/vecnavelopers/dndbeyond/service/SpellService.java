package com.vecnavelopers.dndbeyond.service;

import com.vecnavelopers.dndbeyond.model.Spell;
import okhttp3.OkHttpClient;

import org.springframework.stereotype.Service;
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

import java.io.IOException;
import java.util.List;

@Service
public class SpellService {

    private final OkHttpClient client;

    public SpellService() {
        this.client = new OkHttpClient().newBuilder().build();
    }

    public List<Spell> getSpellsForClass(String className) throws IOException {
        String url = "https://www.dnd5eapi.co/api/classes/" + className + "/spells";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Assuming the response body is in the expected format
            String jsonResponse = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonResponse);
            JsonNode resultsNode = jsonNode.get("results");

            // Map the results to Spell objects, only including Level 0 and Level 1 spells
            List<Spell> spells = new ArrayList<>();
            for (JsonNode spellNode : resultsNode) {
                int level = spellNode.get("level").asInt();
                if (level == 0 || level == 1) {
                    Spell spell = new Spell(
                            spellNode.get("name").asText(),
                            level,
                            spellNode.get("url").asText()
                    );
                    spells.add(spell);
                }
            }

            return spells;
        }
    }
}
