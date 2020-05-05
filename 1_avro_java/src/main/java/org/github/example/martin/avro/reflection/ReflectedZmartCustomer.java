package org.github.example.martin.avro.reflection;

public class ReflectedZmartCustomer {
  
  /**
   * client Id of Customer - private para ser accesible solo desde la clase vehículos
   */
  private String client_id;
  /**
   * First Name of Customer
   */
  private String first_name;
  /**
   * Last Name of Customer
   */
  private String last_name;
  /**
   * id of product
   */
  private String product_id;
  /**
   * price of product in euro
   */
  private float price;
  /**
   * quantity of products in units
   */
  private int quantity;
  /**
   * credict card number in format xxx-xxx-xxx-945
   */
  private String credict_card;
  /**
   * name entity finance of credict card
   */
  private String entity_finance;
  
  public ReflectedZmartCustomer(){}
  
  
  //  constructor method
  public ReflectedZmartCustomer(String client_id, String first_name, String last_name, String product_id, float price, int quantity, String credict_card, String entity_finance) {
    //class field = parameter
    this.client_id = client_id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.product_id = product_id;
    this.price = price;
    this.quantity = quantity;
    this.credict_card = credict_card;
    this.entity_finance = entity_finance;
  }
  
  
  public String getClient_id() {
    return client_id;
  }
  
  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }
  
  public String getFirst_name() {
    return first_name;
  }
  
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  
  public String getLast_name() {
    return last_name;
  }
  
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }
  
  public String getProduct_id() {
    return product_id;
  }
  
  public void setProduct_id(String product_id) {
    this.product_id = product_id;
  }
  
  public float getPrice() {
    return price;
  }
  
  public void setPrice(float price) {
    this.price = price;
  }
  
  public int getQuantity() {
    return quantity;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public String getCredict_card() {
    return credict_card;
  }
  
  public void setCredict_card(String credict_card) {
    this.credict_card = credict_card;
  }
  
  public String getEntity_finance() {
    return entity_finance;
  }
  
  public void setEntity_finance(String entity_finance) {
    this.entity_finance = entity_finance;
  }
  
  public String getInfoCustomer() {
    return "El cliente es " + this.first_name + " " + this.last_name + " con Id " + this.client_id  + " ha comprado el producto con Id " + this.product_id
      + " y el monto de lo comprado es " + (this.price * this.quantity) + "€, con la tarjeta " + this.credict_card;
  }
  
}
