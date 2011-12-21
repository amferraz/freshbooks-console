package com.freshbooks.console.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.freshbooks.console.CommandParser;
import com.freshbooks.console.Token;
import com.freshbooks.console.TokenType;


public class TestInvoiceCommandsParsing {

  @Test
  public void testSimpleList() {
    int i = 0;
    String command = "invoice.list";

    List<Token> tokens = CommandParser.parse(command);
    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
  }

  @Test
  public void testListRecurringId() {
    int i = 0;
    String command = "invoice.list -recurring_id 1 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_RECURRING_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testListClientId() {
    int i = 0;
    String command = "invoice.list  -client_id 23  ";

    List<Token> tokens = CommandParser.parse(command);
    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_CLIENT_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testListStatus() {
    int i = 0;
    String command = "invoice.list -status draft ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_STATUS, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }
  
  
  @Test
  public void testListDateFrom() {
    int i = 0;
    String command = "invoice.list -date_from 2009-01-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_DATE_FROM, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }
  
  @Test
  public void testListDateTo() {
    int i = 0;
    String command = "invoice.list -date_to 2009-12-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_DATE_TO, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }
  

  @Test
  public void testListUpdatedFrom() {
    int i = 0;
    String command = "invoice.list -updated_from 2009-01-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_FROM, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }

  @Test
  public void testListUpdatedTo() {
    int i = 0;
    String command = "invoice.list -updated_to 2009-12-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_TO, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }

  @Test
  public void testListPage() {
    int i = 0;
    String command = "invoice.list -page 1 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testListPerPage() {
    int i = 0;
    String command = "invoice.list -per_page 4 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PER_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testListFolder() {
    int i = 0;
    String command = "invoice.list -folder active ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_FOLDER, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testListNotes() {
    int i = 0;
    String command = "invoice.list -notes sometext ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_NOTES, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testDeleteId() {
    int i = 0;
    String command = "invoice.delete -id 23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_DELETE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testGetId() {
    int i = 0;
    String command = "invoice.get -id 23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_GET, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

//  @Test
//  public void testUpdateId() {
//    int i = 0;
//    String command = "invoice.update -id 23";
//
//    List<Token> tokens = CommandParser.parse(command);
//
//    assertEquals(TokenType.M_INVOICE_UPDATE, tokens.get(i++).getType());
//    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
//    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
//  }

//  @Test
//  public void testClientCreateEmailOrganization() {
//    int i = 0;
//    String command = "invoice.create -email myemail@domain.com -organization myorganization";
//
//    List<Token> tokens = CommandParser.parse(command);
//
//    assertEquals(TokenType.M_CLIENT_CREATE, tokens.get(i++).getType());
//    assertEquals(TokenType.KEY_EMAIL, tokens.get(i++).getType());
//    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
//    assertEquals(TokenType.KEY_ORGANIZATION, tokens.get(i++).getType());
//    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
//  }

  @Test
  public void testPaginatedList() {
    int i = 0;
    String command = "invoice.list  -updated_from 2009-01-01 00:00:00 -updated_to 2009-12-01" +
    		" 00:00:00 -page 1 -per_page 25 -folder active -notes email ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_FROM, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_TO, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PER_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_FOLDER, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_NOTES, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }
  
  
  @Test
  public void testUpdateDate() {
    int i = 0;
    String command = "invoice.update -id 23 -date 2007-06-23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_INVOICE_UPDATE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
  }

}
