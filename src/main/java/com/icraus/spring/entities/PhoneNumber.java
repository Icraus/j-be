package com.icraus.spring.entities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class PhoneNumber {
    private static Map<String, Map<String, String>> countriesMap;
    private String countryCode = "";
    private String number = "";
    private String country = "";
    private boolean valid = false;
    public static Map<String, String> getCountryByName(String name){
        return countriesMap.get(name);
    }

    public PhoneNumber(String number){
        setNumber(number);
    }

    private void validateNumber(String number) {
        for(String countryName : countriesMap.keySet()){
            var entry = countriesMap.get(countryName);
            var code = entry.get("code");
            var testCode = "(" + code + ")";
            if(number.startsWith(testCode)){
                var regex = entry.get("regex");
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                var match = pattern.matcher(number);
                valid = match.matches();
                country = countryName;
                countryCode = code;
                break;
            }
        }
    }

    public static void init(String testSampleFilePath) {
        JSONParser jsonParser = new JSONParser();
        Map<String, Map<String, String>> result =  new HashMap<>();
        try (FileReader reader = new FileReader(testSampleFilePath)) {
            JSONObject obj = (JSONObject)jsonParser.parse(reader);
            JSONObject countries  = (JSONObject) obj.get("countries");
            for(var val : countries.keySet()){
                Map<String, String> entry = new HashMap<>();
                var countryName = (String) val;
                var country = (JSONObject)countries.get(val);
                var countryRegex = (String)country.get("regex");
                var countryCode = (String)country.get("code");
                entry.put("regex", countryRegex);
                entry.put("code", countryCode);
                result.put(countryName, entry);
            }
            countriesMap = result;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setNumber(String number){
        this.number = number;
        this.countryCode = "";
        this.country = "";
        this.valid = false;
        validateNumber(number);
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public boolean isValid() {
        return valid;
    }
}
