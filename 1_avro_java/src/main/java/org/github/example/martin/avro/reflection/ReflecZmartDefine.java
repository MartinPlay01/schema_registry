package org.github.example.martin.avro.reflection;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;

public class ReflecZmartDefine {
  public static void main(String[] args) {
    
    //here we use the reflection to determine the schema
    Schema schema = ReflectData.get().getSchema(ReflectedZmartCustomer.class);
    System.out.println("*** Schema = "+ schema.toString(true));
    
//      create a file of ReflectedZmartCustomer
    
    try {
      System.out.println("Writing customer-reflected-zmart.avro");
      File file = new File("1_avro_java/outputs/customer-reflected-zmart.avro");
      DatumWriter<ReflectedZmartCustomer> writer = new ReflectDatumWriter<>(ReflectedZmartCustomer.class);
      DataFileWriter<ReflectedZmartCustomer> out = new DataFileWriter<>(writer)
        .setCodec(CodecFactory.deflateCodec(9))
        .create(schema, file);
      
      out.append(new ReflectedZmartCustomer("3452345", "Martín", "Doe", "3452345g3", 45.4f, 52, "xxx-xxx-xxx-445", "visa"));
      out.close();
    }catch (IOException e){
      System.out.println("*** problema de escritura");
      e.printStackTrace();
    }

//    read from an avro into our Reflected class
//    open a file of ReflectedZmartCustomer - customer-reflected-zmart.avro
    try{
      System.out.println("Reading customer-reflected-zmart.avro");
      File file = new File("1_avro_java/outputs/customer-reflected-zmart.avro");
      DatumReader<ReflectedZmartCustomer> reader = new ReflectDatumReader<>(ReflectedZmartCustomer.class);
      DataFileReader<ReflectedZmartCustomer> in = new DataFileReader<>(file, reader);

//    read ReflectedZmartCustomer from a file & print them as JSON
      for (ReflectedZmartCustomer reflectedCustomerZ :in){
        System.out.println(reflectedCustomerZ.getInfoCustomer());
      }
//    close the input file
      in.close();
    } catch (IOException e){
      e.printStackTrace();
    }
    
    
    

  }
}
