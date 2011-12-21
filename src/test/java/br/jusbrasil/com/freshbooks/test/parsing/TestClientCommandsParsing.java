package br.jusbrasil.com.freshbooks.test.parsing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.jusbrasil.freshbooks.CommandParser;
import br.com.jusbrasil.freshbooks.Token;
import br.com.jusbrasil.freshbooks.TokenType;

public class TestClientCommandsParsing {

  @Test
  public void testSimpleClientList() {
    int i = 0;
    String command = "client.list";

    List<Token> tokens = CommandParser.parse(command);
    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
  }

  @Test
  public void testClientListId() {
    int i = 0;
    String command = "client.list -id 1 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientListEmail() {
    int i = 0;
    String command = "client.list  -email myemail@example.com  ";

    List<Token> tokens = CommandParser.parse(command);
    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_EMAIL, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testClientListUsername() {
    int i = 0;
    String command = "client.list -username myusername ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_USERNAME, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testClientListUpdatedFrom() {
    int i = 0;
    String command = "client.list -updated_from 2009-01-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_FROM, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }

  @Test
  public void testClientListUpdatedTo() {
    int i = 0;
    String command = "client.list -updated_to 2009-12-01 00:00:00 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_UPDATED_TO, tokens.get(i++).getType());
    assertEquals(TokenType.T_DATE, tokens.get(i++).getType());
    assertEquals(TokenType.T_HOUR, tokens.get(i++).getType());
  }

  @Test
  public void testClientListPage() {
    int i = 0;
    String command = "client.list -page 1 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientListPerPage() {
    int i = 0;
    String command = "client.list -per_page 4 ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PAGINATION_PER_PAGE, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientListFolder() {
    int i = 0;
    String command = "client.list -folder active ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_FOLDER, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testClientListNotes() {
    int i = 0;
    String command = "client.list -notes sometext ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_NOTES, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testClientDeleteId() {
    int i = 0;
    String command = "client.delete -id 23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_DELETE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientGetId() {
    int i = 0;
    String command = "client.get -id 23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_GET, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientUpdateId() {
    int i = 0;
    String command = "client.update -id 23";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_UPDATE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ID, tokens.get(i++).getType());
    assertEquals(TokenType.T_INTEGER, tokens.get(i++).getType());
  }

  @Test
  public void testClientCreateEmailOrganization() {
    int i = 0;
    String command = "client.create -email myemail@domain.com -organization myorganization";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_CREATE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_EMAIL, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ORGANIZATION, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
  }

  @Test
  public void testClientFullList() {
    int i = 0;
    String command = "client.list -email myemail@domain.com -username " +
    		"myusername -updated_from 2009-01-01 00:00:00 -updated_to 2009-12-01" +
    		" 00:00:00 -page 1 -per_page 25 -folder active -notes email ";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_EMAIL, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_USERNAME, tokens.get(i++).getType());
    assertEquals(TokenType.T_TEXT, tokens.get(i++).getType());
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
  public void testClientKeys() {
    int i = 0;
    String command = "client.list -show -first_name -last_name -organization " +
    		"-email -username -password -work_phone -home_phone -mobile -fax" +
    		" -notes -p_street1 -p_street2 -p_city -p_state -p_country -p_code " +
    		"-s_street1 -s_street2 -s_city -s_state -s_country -s_code";

    List<Token> tokens = CommandParser.parse(command);

    assertEquals(TokenType.M_CLIENT_LIST, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SHOW, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_FIRST_NAME, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_LAST_NAME, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_ORGANIZATION, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_EMAIL, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_USERNAME, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PASSWORD, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_WORK_PHONE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_HOME_PHONE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_MOBILE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_FAX, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_NOTES, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_STREET_1, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_STREET_2, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_CITY, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_STATE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_COUNTRY, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_PRIMARY_CODE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_STREET_1, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_STREET_2, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_CITY, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_STATE, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_COUNTRY, tokens.get(i++).getType());
    assertEquals(TokenType.KEY_SECONDARY_CODE, tokens.get(i++).getType());
    
//    assertEquals(TokenType.KEY_, tokens.get(i++).getType());
    
  }

}
