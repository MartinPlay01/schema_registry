package org.github.example.martin.avro.specific;

import com.zmart.customer.Customer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class SpecificRecord {
  
  public static void main(String[] args) {
    
    // step 1: create specific record
    Customer.Builder customerBuilder = Customer.newBuilder();
    customerBuilder.setClientId("3552354");
    customerBuilder.setFirstName("Pablo");
    customerBuilder.setLastName("Doe");
    customerBuilder.setProductId("345345");
    customerBuilder.setPrice(24.5f);
    customerBuilder.setQuantity(45);
    customerBuilder.setCredictCard("xxx-xxx-xxx-524");
    customerBuilder.setEntityFinance("visa");
    Customer lala= customerBuilder.build();
  
    System.out.println(lala.toString());
    
    // step 2: write to file
    
    final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
    try(DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)){
      dataFileWriter.create(lala.getSchema(), new File("customer-zmart-specific.avro"));
      dataFileWriter.append(lala);
      System.out.println("Succesfully write customer-zmart-specific.avro");
    }catch(IOException e){
      e.printStackTrace();
    }
    
    // step 3: read from file
    final File file = new File("customer-zmart-specific.avro");
    final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
    final DataFileReader<Customer> dataFileReader;
    
    try {
      dataFileReader = new DataFileReader<>(file, datumReader);
      
      while (dataFileReader.hasNext()){
        
        Customer iteration = dataFileReader.next();
        System.out.println(iteration.toString());
      }
      
    }catch (IOException e){
      System.out.println("Problema en la lectura del fichero customer-zmart-specific.avro");
      e.printStackTrace();
    }
    // step 4: Interpret
    
    
  }
}
