package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String mail;
  private int filter_level;

  protected Users() {}

  /**
   * コンストラクタ.
   * 
   * @param name ユーザ名
   */
  public Users(String name,String mail,int filter_level) {
    this.name = name;
    this.mail = mail;
    this.filter_level = filter_level;
  }

  /*public Users(String name) {
    this.name = name;
  }*/

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMail() {
    return mail;
  }

  public int getfilter_level() {
    return filter_level;
  }

  @Override
  public String toString() {
    return String.format("{id:%d,name:%s}", id, name);
  }
}