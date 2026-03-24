package com.example.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passHasher {
  public static void main(String[] args) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
      String pt = "````";
      String pass = encoder.encode(pt);
      System.out.println(pass+":");
    }
}
