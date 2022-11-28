
 /*
  * Author     : birichani.code
  * Date         : 26/11/22  13:02
  * Project Name : childrenRESTfulAPI
  * */


 package com.birichani.code.model;


 import lombok.Getter;

 import lombok.Setter;

 import javax.persistence.*;
 import java.util.Date;

 @Setter
 @Getter

 @Entity
 @Table(
         name = "spoilt_product", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
 )

         public class SpoiltItem {
     @Id
     @GeneratedValue(
             strategy = GenerationType.IDENTITY
     )
     private long id;
     private String name;
     private Date createdTime;

     public SpoiltItem(long id, String name, Date createdTime) {
         this.id = id;
         this.name = name;
         this.createdTime = createdTime;
     }

     public SpoiltItem() {
     }
 }
