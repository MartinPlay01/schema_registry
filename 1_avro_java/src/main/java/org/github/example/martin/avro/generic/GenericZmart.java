package org.github.example.martin.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class GenericZmart {
  
  
  public static void main(String[] args) {
    
    // step 0: define schema
  
    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse("{\n" +
      "     \"type\": \"record\",\n" +
      "     \"namespace\": \"com.zmart.customer\",\n" +
      "     \"name\": \"Customer\",\n" +
      "     \"doc\": \"Avro Schema for our ZMart's Customer\",     \n" +
      "     \"fields\": [\n" +
      "       { \"name\": \"client_id\", \"type\": \"string\", \"doc\": \"client Id of Customer\" },\n" +
      "       { \"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\n" +
      "       { \"name\": \"last_name\", \"type\": \"string\", \"doc\": \"Last Name of Customer\" },\n" +
      "       { \"name\": \"product_id\", \"type\": \"string\", \"doc\": \"id of product\" },\n" +
      "       { \"name\": \"price\", \"type\": \"float\", \"doc\": \"price of product in euro\" },\n" +
      "       { \"name\": \"quantity\", \"type\": \"int\", \"doc\": \"quantity of products in units\" },\n" +
      "       { \"name\": \"credit_card\", \"type\": \"string\", \"doc\": \"credict card number in format xxx-xxx-xxx-945\" },\n" +
      "       { \"name\": \"entity_finance\", \"type\": \"string\", \"default\": \"unknown\", \"doc\": \"name entity finance of credict card\" }\n" +
      "     ]\n" +
      "}");
//    System.out.println(schema);
    // step 1: create generic record
  
    GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
    customerBuilder.set("client_id", "23542345");
    customerBuilder.set("first_name", "Martín");
    customerBuilder.set("last_name", "Izquierdo");
    customerBuilder.set("product_id", "235457");
    customerBuilder.set("price", 23.5f);
    customerBuilder.set("quantity", 3);
    customerBuilder.set("credit_card", "xxx-xxx-xxx-678");
    customerBuilder.set("entity_finance", "master-card");
    GenericData.Record customer = customerBuilder.build();
  
    System.out.println(customer);
  
    GenericRecordBuilder customerBuilderWithDefault = new GenericRecordBuilder(schema);
    customerBuilderWithDefault.set("client_id", "23542345");
    customerBuilderWithDefault.set("first_name", "Elena");
    customerBuilderWithDefault.set("last_name", "Doe");
    customerBuilderWithDefault.set("product_id", "34555");
    customerBuilderWithDefault.set("price", 43.5f);
    customerBuilderWithDefault.set("quantity", 4);
    customerBuilderWithDefault.set("credit_card", "xxx-xxx-xxx-897");
    GenericData.Record customerDefault = customerBuilderWithDefault.build();
  
    System.out.println(customerDefault);
    
    // step 2: write that generic record to a file
    
        // writing to a file
    final DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
    try(DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)){
      dataFileWriter.create(customer.getSchema(), new File("1_avro_java/outputs/customer-zmart-generic.avro"));
      dataFileWriter.append(customer);
      System.out.println("Written customer-zmart-generic.avro");
    } catch (IOException e){
      System.out.println("Couldn't write file");
    }
    // step 3: read a generic record from a file
    
    final File file = new File("1_avro_java/outputs/customer-zmart-generic.avro");
    final DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();
    GenericRecord customerRead;
    try(DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)) {
  
      // step 4: interpret as a generic record
      customerRead = dataFileReader.next();
      System.out.println("Succesfully read avro file");
      System.out.println(customerRead.toString());
    }catch(IOException e){
      System.out.println();
      e.printStackTrace();
    }

    
    
    
  }
  
}
