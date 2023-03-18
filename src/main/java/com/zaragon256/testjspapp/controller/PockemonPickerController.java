package com.zaragon256.testjspapp.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaragon256.testjspapp.dto.Pokemon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class PockemonPickerController {

    @GetMapping({"", "/pokemon"})
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="pikachu") String name) {

        model.addAttribute("name", name);

        String pokeApiUrl = "https://pokeapi.co/api/v2/pokemon/";

        try {
            URL url = new URL(pokeApiUrl+name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept","application/json");
            if (conn.getResponseCode() != 200){
                throw new RuntimeException("failed : http error code: " + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            StringBuilder jsonString = new StringBuilder();
            while((output = br.readLine()) != null){
                //System.out.println(output);
                jsonString.append(output);
            }
            conn.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Pokemon pokemon = objectMapper.readValue(jsonString.toString(), Pokemon.class);
            //Pokemon object
            //System.out.println("name of pkm: " + pokemon.getName());
            model.addAttribute("pokemon", pokemon);

        }catch (Exception e){
            System.out.println("Error: " + e);
        }



        return "pokemon";
    }

}
