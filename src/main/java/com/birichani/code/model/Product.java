
 /*
  * Author     : birichani.code
  * Date         : 26/11/22  12:25
  * Project Name : childrenRESTfulAPI
  * */


 package com.birichani.code.model;

 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;

 import javax.persistence.*;
 import java.util.Date;
@Setter
@AllArgsConstructor
@Getter
 @Entity
 @Table(
         name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})}
 )
@NoArgsConstructor
public class Product {
     @Id
     @GeneratedValue(
             strategy = GenerationType.IDENTITY
     )
     private Long id;

     @Column(name = "name", nullable = false)
     private String name;
     @Column(name = "createdTime", nullable = false)
     private Date createdTime;


}
