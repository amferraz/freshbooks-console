package br.com.jusbrasil.freshbooks;

import com.freshbooks.model.Client;

public class Formatter {

  
  public static void output(Client c) {
    System.out.println("Client:");
    System.out.println("\tid \t:" + c.getId());
    if (c.getFirstName() != null) System.out.println("\tfirst_name \t:" + c.getFirstName());
    if (c.getLastName() != null) System.out.println("\tlast_name \t:" + c.getLastName());
    if (c.getEmail() != null) System.out.println("\temail \t:" + c.getEmail());
    if (c.getOrganization() != null) System.out.println("\torganization \t:" + c.getOrganization());
  }
  
}

