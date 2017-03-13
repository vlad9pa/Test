package com.vlad9pa.tasktest.repository;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad9pa.tasktest.entity.Roles;
import com.vlad9pa.tasktest.entity.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    It's Hardcoded JsonStorage for User entity which can contain only one role.
    File user_id.json in main dir, contains last id for auto incrementing id;
 */
@Profile("json")
@Repository
public class UserJsonRepository implements JsonRepository<User,Long> {

    private final String pathToDirectory = PATH_TO_MAIN_DIR+"Users/";
    protected Long lastId;

    @Override
    public void setLastId(Long lastId) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(PATH_TO_MAIN_DIR+"user_id.json");
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", lastId.toString());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            this.lastId = lastId;
        } catch (IOException e) {
            System.out.println("Unable to find"+e.getMessage());
        }

    }

    @Override
    public Long getLastId() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(PATH_TO_MAIN_DIR+"user_id.json");
            JsonParser p = jsonFactory.createParser(jsonFile);
            JsonToken t = p.nextToken();
            t = p.nextToken();
            if ((t != JsonToken.FIELD_NAME) || !"id".equals(p.getCurrentName())) {
                // handle error
            }
            t = p.nextToken();
            if (t != JsonToken.VALUE_STRING) {
                // similarly
            }
            String id = p.getText();
            Long lastId = Long.valueOf(id).longValue();
            p.close();
            return lastId;
        } catch (Exception e) {
            System.out.println("Unable to find"+e.getMessage());
        }
        return 1L;
    }

    @Override
    public void save(User entity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            lastId = getLastId();
            File pathToFile = new File(pathToDirectory);
            pathToFile.mkdirs();
            File jsonFile = new File(pathToDirectory + (lastId).toString() + ".json");
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("username", entity.getUsername());
            jsonGenerator.writeStringField("password", entity.getPassword());
            jsonGenerator.writeStringField("id", lastId.toString());
            for (Roles role: entity.getRoles()) {
                jsonGenerator.writeStringField("Roles", role.toString());
            }
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            entity.setId(lastId);
            setLastId(++lastId);
        } catch (Exception e) {
            System.out.println("Unable to save"+e.getMessage());
        }
    }

    @Override
    public User getOne(Long id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(pathToDirectory + id.toString() + ".json");
            JsonParser p = jsonFactory.createParser(jsonFile);
            JsonToken t = p.nextToken();
            t = p.nextToken();
            p.nextToken();
            String username = p.getText();
            User user = new User();
            user.setUsername(username);
            user.setId(id);
            p.nextToken();
            p.nextToken();
            String password = p.getText();
            user.setPassword(password);
            p.nextToken();
            p.nextToken();
            String _id = p.getText();
            user.setId(Long.valueOf(_id).longValue());
            p.nextToken();
            p.nextToken();
            String role = p.getText();
            Set<Roles> roles = new HashSet<>();
            roles.add(Roles.valueOf(role));
            user.setRoles(roles);
            p.close();
            return user;
        } catch (Exception e) {
            System.out.println("Unable to find"+e.getMessage());
        }
        return null;
    }

    public User findByUsername(String username){
        lastId = getLastId();
        for(Long i = 1L; i <= lastId; i++){
            if(getOne(i) != null && getOne(i).getUsername().equals(username)){
                return getOne(i);
            }
        }
        return null;
    }



    @Override
    public void delete(User entity) {
        File pathToFile = new File(pathToDirectory);
        pathToFile.mkdirs();
        File jsonFile = new File(pathToDirectory+entity.getId().toString()+".json");
        jsonFile.delete();
    }

    @Override
    public void update(User entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getJsonFactory();
        File pathToFile = new File(pathToDirectory);
        pathToFile.mkdirs();
        File jsonFile = new File(pathToDirectory+(entity.getId()).toString()+".json");
        try {
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("username",entity.getUsername());
            jsonGenerator.writeStringField("password",entity.getPassword());
            jsonGenerator.writeStringField("id",entity.toString());
            jsonGenerator.writeStringField("role",entity.getRoles().toString());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (Exception e) {
            System.out.println("Unable to update"+e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        File mainFolder = new File(pathToDirectory);
        List<File> files = Arrays.asList(mainFolder.listFiles());
        for(File file : files){
            file.delete();
        }
        mainFolder.delete();
        File ids = new File(PATH_TO_MAIN_DIR+"user_id.json");
        ids.delete();
    }
}