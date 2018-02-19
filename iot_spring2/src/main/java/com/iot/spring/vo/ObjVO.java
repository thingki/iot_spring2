package com.iot.spring.vo;

public class ObjVO {

   private String id;
   private String text;
   private String type;
   private String conType;
   
   public String getConType() {
      return conType;
   }
   public void setConType(String conType) {
      this.conType = conType;
   }
   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getText() {
      return text;
   }
   public void setText(String text) {
      this.text = text;
   }
   @Override
   public String toString() {
      return "ObjVO [id=" + id + ", text=" + text + ", type=" + type + ", conType=" + conType + "]";
   }
   
}