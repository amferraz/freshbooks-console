package br.com.jusbrasil.freshbooks;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.freshbooks.ApiConnection;
import com.freshbooks.ApiException;
import com.freshbooks.model.Client;
import com.freshbooks.model.Invoice;
import com.freshbooks.model.Recurring;
import com.freshbooks.model.Recurrings;
import com.inamik.utils.SimpleTableFormatter;
import com.inamik.utils.TableFormatter;

import br.com.jusbrasil.freshbooks.err.CommandSyntaxError;

public class CommandExecuter {

  // TODO: consume token returning specific data types

  private static ApiConnection con;

  public static void setConnection(ApiConnection con) {
    CommandExecuter.con = con;
  }

  public static void execute(List<Token> tokens) throws CommandSyntaxError,
      ApiException, IOException, ParseException {

    // empty commmands
    if (!tokens.iterator().hasNext()) {
      return;
    }

    Token current = tokens.iterator().next();

    switch (current.getType()) {

    case M_CLIENT_GET:
      client_get(tokens);
      break;

    case M_CLIENT_LIST:
      client_list(tokens);
      break;

    case M_INVOICE_LIST:
      invoice_list(tokens);
      break;

    case M_INVOICE_DELETE:
      invoice_delete(tokens);
      break;

    case M_INVOICE_UPDATE:
      invoice_update(tokens);
      break;
      
    case M_RECURRING_LIST:
      recurring_list(tokens);
      break;

    default:
      System.out.println("Command not found.");
      break;
    }
  }

  // invoice list
  public static void invoice_list(List<Token> tokens)
      throws CommandSyntaxError, ApiException, IOException {

    Iterator<Token> it = tokens.iterator();

    consumeTokenOrError(it, TokenType.M_INVOICE_LIST, "Expected method name.");

    try {
      Iterable<Invoice> invoices = con.listInvoices(null, null, null, null,
          null);

      System.out.println(
          "ID\t"  + "REC\t" + "R$\t" + "DATE\t" + "PO\t" + "STATUS\t"
          + "CLIENT_ID\t" + "ORGANIZATION\t" + "LINK\tNUMBER");
      for (Invoice invoice : invoices) {
        System.out.println(
                invoice.getId() + "\t"
                + invoice.getRecurringId() + "\t"
                + invoice.getAmount() + "\t"
                + invoice.getDate().toString() + "\t"
                + invoice.getPoNumber() + "\t" 
                + invoice.getStatus() + "\t"
                + invoice.getId() + "\t"
                + invoice.getClientId() + "\t" + invoice.getOrganization() + "\t"
                + invoice.getLinks().getClientView() + "\t" 
                + invoice.getNumber()
                );
      }

    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

  }
  
  
  
  
  public static void recurring_list(List<Token> tokens)
      throws CommandSyntaxError, ApiException, IOException {

    Iterator<Token> it = tokens.iterator();

    consumeTokenOrError(it, TokenType.M_RECURRING_LIST, "Expected method name.");

    try {
     Iterable<Recurring> recurrings = con.listRecurrings(60, null);

      System.out.println(
                      "ID\t"
                    + "PO NUMBER\t"
//                    + "R$\t"
//                    + "DATE\t" 
//                    + "CLIENT_ID\t"
                    + "ORGANIZATION\t"
          );
      
      for (Recurring recurring : recurrings) {
        System.out.println(
                  recurring.getId() + "\t"
                  + recurring.getPoNumber() + "\t"
//                  + recurring.getAmount() + "\t"
//                  + recurring.getDate().toString() + "\t" 
//                  + recurring.getId() + "\t"
//                  + recurring.getClientId() + "\t" 
                  + recurring.getOrganization() + "\t"
              );
      }

    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

  }

  // client_get := M_CLIENT_GET KEY_ID T_INTEGER;
  public static void client_get(List<Token> tokens) throws CommandSyntaxError,
      ApiException, IOException {

    Iterator<Token> it = tokens.iterator();

    String val;
    val = consumeTokenOrError(it, TokenType.M_CLIENT_GET,
        "Expected method name.");
    val = consumeTokenOrError(it, TokenType.KEY_ID,
        "Expected '-id' after method name");
    val = consumeTokenOrError(it, TokenType.T_INTEGER,
        "Expected number after '-id'");

    try {
      Client c = con.getClient(Long.valueOf(val));
      Formatter.output(c);
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

  }

  // invoice_delete := M_INVOICE_DELETE KEY_ID T_INTEGER
  public static void invoice_delete(List<Token> tokens)
      throws CommandSyntaxError, ApiException, IOException {

    Iterator<Token> it = tokens.iterator();

    String val;
    val = consumeTokenOrError(it, TokenType.M_INVOICE_DELETE,
        "Expected method name.");
    val = consumeTokenOrError(it, TokenType.KEY_ID,
        "Expected '-id' after method name");
    val = consumeTokenOrError(it, TokenType.T_INTEGER,
        "Expected number after '-id'");

    try {
      con.deleteInvoice(Long.valueOf(val));
      System.out.println("Invoice deleted.");
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

  }

  public static void invoice_update(List<Token> tokens)
      throws CommandSyntaxError, ApiException, IOException, ParseException {

    Iterator<Token> it = tokens.iterator();

    String invoice_id;
    consumeTokenOrError(it, TokenType.M_INVOICE_UPDATE, "Expected method name.");
    consumeTokenOrError(it, TokenType.KEY_ID,
        "Expected '-id' after method name");
    invoice_id = consumeTokenOrError(it, TokenType.T_INTEGER,
        "Expected number after '-id'");

    consumeTokenOrError(it, TokenType.KEY_DATE, "Expected '-date' after id ");

    String date = consumeTokenOrError(it, TokenType.T_DATE,
        "Expected date after '-date'");

    // TODO add date to printing invoice

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    Invoice invoice = new Invoice();
    invoice.setId(Long.valueOf(invoice_id));
    invoice.setDate(sdf.parse(date));
    con.updateInvoice(invoice);
    System.out.println("Invoice updated.");

  }
  
  
  
  private static String evaluateClientValueByTokenType(TokenType t, Client c) throws CommandSyntaxError {
    
    //first_name -last_name -organization " +
//    "-email -username -password -work_phone -home_phone -mobile -fax" +
//    " -notes -p_street1 -p_street2 -p_city -p_state -p_country -p_code " +
//    "-s_street1 -s_street2 -s_city -s_state -s_country -s_code
    
    switch ( t ) {
        case KEY_ID : return String.valueOf(c.getId());
        case KEY_FIRST_NAME : return c.getFirstName();
        case KEY_LAST_NAME : return c.getLastName();
        case KEY_ORGANIZATION : return c.getOrganization();
        case KEY_EMAIL : return c.getEmail();
        case KEY_USERNAME : return c.getUsername();
        case KEY_PASSWORD : return c.getPassword();
        case KEY_WORK_PHONE : return c.getWorkPhone();
        case KEY_HOME_PHONE : return c.getHomePhone();
        case KEY_MOBILE : return c.getMobile();
        case KEY_FAX : return c.getFax();
        case KEY_NOTES : return c.getNotes();
        case KEY_PRIMARY_STREET_1 : return c.getStreet1();
        case KEY_PRIMARY_STREET_2 : return c.getStreet2();
        case KEY_PRIMARY_CITY: return c.getCity();
        case KEY_PRIMARY_STATE: return c.getState();
        case KEY_PRIMARY_COUNTRY: return c.getCountry();
        case KEY_PRIMARY_CODE : return c.getCode();
        case KEY_SECONDARY_STREET_1: return c.getSecondaryStreet1();
        case KEY_SECONDARY_STREET_2: return c.getSecondaryStreet2();
        case KEY_SECONDARY_CITY: return c.getSecondaryCity();
        case KEY_SECONDARY_STATE: return c.getSecondaryState();
        case KEY_SECONDARY_COUNTRY: return c.getSecondaryCountry();
        case KEY_SECONDARY_CODE: return c.getSecondaryCode();
        default : throw new CommandSyntaxError("Expected client values names ('-id', '-email' etc.))");
    }
  }
  

  private static TokenType consumeClientKeysTokenOrError(Iterator<Token> it,
      String errorMessage) throws CommandSyntaxError {
    
    TokenType t = it.next().getType();
    
      switch ( t ) {
        case KEY_ID :
        case KEY_FIRST_NAME :
        case KEY_LAST_NAME : 
        case KEY_ORGANIZATION : 
        case KEY_EMAIL :
        case KEY_USERNAME : 
        case KEY_PASSWORD : 
        case KEY_WORK_PHONE :
        case KEY_HOME_PHONE : 
        case KEY_MOBILE : 
        case KEY_FAX : 
        case KEY_NOTES : 
        case KEY_PRIMARY_STREET_1 : 
        case KEY_PRIMARY_STREET_2 : 
        case KEY_PRIMARY_CITY: 
        case KEY_PRIMARY_STATE: 
        case KEY_PRIMARY_COUNTRY: 
        case KEY_PRIMARY_CODE : 
        case KEY_SECONDARY_STREET_1:
        case KEY_SECONDARY_STREET_2: 
        case KEY_SECONDARY_CITY: 
        case KEY_SECONDARY_STATE: 
        case KEY_SECONDARY_COUNTRY:
        case KEY_SECONDARY_CODE: return t;
        default : throw new CommandSyntaxError("Expected client values names ('-id', '-email' etc.))");
    }

  }

  // client_list := M_CLIENT_GET KEY_ID T_INTEGER;
  public static void client_list(List<Token> tokens) throws CommandSyntaxError,
      ApiException, IOException {

    Iterator<Token> it = tokens.iterator();

    consumeTokenOrError(it, TokenType.M_CLIENT_LIST, "Expected method name.");
//    consumeTokenOrError(it, TokenType.KEY_SHOW, "Expected '-show'");
    
    
//    ArrayList<TokenType> keys = new ArrayList<TokenType>();
//    
//    while (it.hasNext()) {
//      keys.add(consumeClientKeysTokenOrError(it, "Expected client values names ('-id', '-email' etc.))"));
//    }
    
    
    
    try {
      Iterable<Client> clients = con.listClients(null, null, null);
      

      TableFormatter tf = new SimpleTableFormatter(true);
      
      //header
      tf.nextRow()
        .nextCell().addLine("ID")
        .nextCell().addLine("EMAIL")
        .nextCell().addLine("LAST_NAME");
          
      for (Client client : clients ) {
        tf.nextRow()
        .nextCell().addLine(String.valueOf(client.getId()))
        .nextCell().addLine(client.getEmail())
        .nextCell().addLine(client.getLastName());
      }
      
      printTable(tf);
      
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

  }

  private static void printTable(TableFormatter tf) {
    String[] table = tf.getFormattedTable();

    for (int i = 0, size = table.length; i < size; i++) {
      System.out.println((i + 1) + "\t" + table[i]);
    }
  }

  private static String consumeTokenOrError(Iterator<Token> it, TokenType type,
      String errorMessage) throws CommandSyntaxError {

    Token current = null;

    try {
      current = it.next();
    } catch (NoSuchElementException e) {
      throw new CommandSyntaxError("Unexpected end of command.");
    }

    if (current.getType() == type) {
      it.remove();
      return current.getValue();
    } else {
      throw new CommandSyntaxError(errorMessage);
    }

  }

  public static void execute(String command) throws CommandSyntaxError,
      ApiException, IOException, ParseException {
    List<Token> tokens = CommandParser.parse(command);
    CommandExecuter.execute(tokens);
  }

}
