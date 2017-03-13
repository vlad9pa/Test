package com.vlad9pa.tasktest.repository;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad9pa.tasktest.entity.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
Hardcoded json storage for contacts entity, files save like userId_contact_id.json.
Example 1_1.json mean that this is contact with id = 1 and userId = 1.
File contact_id.json in main dir, contains last id for auto incrementing id;
 */
@Profile("json")
@Repository
public class ContactJsonRepository implements JsonRepository<Contact, Long> {

    private final String pathToDirectory = PATH_TO_MAIN_DIR+"Contacts/";
    private Long lastId;
    private Long userId;

    @Override
    public void setLastId(Long lastId) {
        System.out.println(lastId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(PATH_TO_MAIN_DIR+"contact_id.json");
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id",lastId.toString());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            this.lastId = lastId;
        } catch (Exception e) {
            System.out.println("Unable to find"+e.getMessage());
        }
    }

    @Override
    public Long getLastId() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(PATH_TO_MAIN_DIR+"contact_id.json");
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
    public void save(Contact entity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            userId = entity.getUser().getId();
            lastId = getLastId();
            File pathToFile = new File(pathToDirectory);
            pathToFile.mkdirs();
            File jsonFile = new File(pathToDirectory+userId+"_"+(lastId).toString()+".json");
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("firstName",entity.getFirstName());
            jsonGenerator.writeStringField("lastName",entity.getLastName());
            jsonGenerator.writeStringField("middleName",entity.getMiddleName());
            jsonGenerator.writeStringField("email",entity.getEmail());
            jsonGenerator.writeStringField("mobileNumber",entity.getMobileNumber());
            jsonGenerator.writeStringField("homePhoneNumber",entity.getHomePhoneNumber());
            jsonGenerator.writeStringField("id",lastId.toString());
            jsonGenerator.writeStringField("user_id",entity.getUser().getId().toString());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            setLastId(++lastId);
        } catch (Exception e) {
            System.out.println("Unable to save"+e.getMessage());
        }

    }

    @Override
    public Contact getOne(Long id) {
        lastId = getLastId();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory jsonFactory = objectMapper.getJsonFactory();
            File jsonFile = new File(pathToDirectory+userId+"_"+id.toString()+".json");
            JsonParser p = jsonFactory.createParser(jsonFile);
            JsonToken t = p.nextToken();
            t = p.nextToken();
            t = p.nextToken();
            String firstName = p.getText();
            Contact contact = new Contact();
            contact.setFirstName(firstName);
            t = p.nextToken();
            t = p.nextToken();
            String lastName = p.getText();
            contact.setLastName(lastName);
            t = p.nextToken();
            t = p.nextToken();
            String middleName = p.getText();
            contact.setMiddleName(middleName);
            t = p.nextToken();
            t = p.nextToken();
            String email = p.getText();
            contact.setEmail(email);
            t = p.nextToken();
            t = p.nextToken();
            String mobileNumber = p.getText();
            contact.setMobileNumber(mobileNumber);
            t = p.nextToken();
            t = p.nextToken();
            String homePhoneNumber = p.getText();
            contact.setHomePhoneNumber(homePhoneNumber);
            t = p.nextToken();
            t = p.nextToken();
            String _id = p.getText();
            contact.setId(Long.valueOf(_id).longValue());
            p.nextToken();
            p.nextToken();
            String user_id = p.getText();
            contact.setUserID(Long.valueOf(user_id).longValue());

            p.close();
            return contact;
        } catch (IOException e) {
            System.out.println("Unable to find: "+e.getMessage());
        }
        return null;
    }

    public Set<Contact> getContactSet(Long userId){
        this.userId = userId;
        Set<Contact> contactSet = new HashSet<>();
        lastId = getLastId();
        for(Long i = 0L; i <= lastId; i++){
            if(getOne(i) != null){
                contactSet.add(getOne(i));
            }
        }
        return contactSet;
    }

    @Override
    public void update(Contact entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getJsonFactory();
        File pathToFile = new File(pathToDirectory);
        pathToFile.mkdirs();
        File jsonFile = new File(pathToDirectory+entity.getUserID().toString()+
                "_"+entity.getId()+".json");
        try {
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(jsonFile, JsonEncoding.UTF8);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("firstName",entity.getFirstName());
            jsonGenerator.writeStringField("lastName",entity.getLastName());
            jsonGenerator.writeStringField("middleName",entity.getMiddleName());
            jsonGenerator.writeStringField("email",entity.getEmail());
            jsonGenerator.writeStringField("mobileNumber",entity.getMobileNumber());
            jsonGenerator.writeStringField("homePhoneNumber",entity.getHomePhoneNumber());
            jsonGenerator.writeStringField("id",entity.getId().toString());
            jsonGenerator.writeStringField("user_id",entity.getUserID().toString());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            System.out.println("Unable to update:"+e.getMessage());
        }
    }

    @Override
    public void delete(Contact entity) {
        File pathToFile = new File(pathToDirectory);
        pathToFile.mkdirs();
        File jsonFile = new File(pathToDirectory+entity.getUserID()+"_"+entity.getId().toString()+".json");
        jsonFile.delete();
    }

    @Override
    public void deleteAll() {
        File mainFolder = new File(pathToDirectory);
        List<File> files = Arrays.asList(mainFolder.listFiles());
        for(File file : files){
            file.delete();
        }
        mainFolder.delete();
        File ids = new File(PATH_TO_MAIN_DIR+"contact_id.json");
        ids.delete();
    }
}