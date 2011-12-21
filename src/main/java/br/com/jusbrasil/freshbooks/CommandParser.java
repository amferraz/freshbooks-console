package br.com.jusbrasil.freshbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A class for parsing the commands 
 * <p>
 * Goshme Soluções para a Internet LTDA<br />
 * Projeto JusBrasil -
 * <a href="http://www.jusbrasil.com.br/">http://www.jusbrasil.com.br/</a>
 * </p>
 * @since $Date$
 * @version CVS $Revision$
 * @author Anderson Caco Marques Ferraz -
 * <a href="mailto:caco@jusbrasil.com.br>caco@jusbrasil.com.br</a>
 */
public class CommandParser {

  public static List<Token> parse(String command){
    ArrayList<Token> tokens = new ArrayList<Token>();
    
    
    Pattern integerNumber = Pattern.compile("[0-9]*");
    Pattern realNumber    = Pattern.compile("[0-9]+(.[0-9][0-9]?)?");
    
    //these two guys need to improve
    Pattern date          = Pattern.compile("[0-9][0-9][0-9][0-9]-[0-1][0-9]-[0-9][0-9]");
    Pattern hour          = Pattern.compile("[0-9][0-9]:[0-9][0-9]:[0-9][0-9]");
    
    
    Map<String, TokenType> keysTokens = new HashMap<String, TokenType>();
    // methods
    keysTokens.put("client.list"   , TokenType.M_CLIENT_LIST);
    keysTokens.put("client.delete" , TokenType.M_CLIENT_DELETE);
    keysTokens.put("client.create" , TokenType.M_CLIENT_CREATE);
    keysTokens.put("client.update" , TokenType.M_CLIENT_UPDATE);
    keysTokens.put("client.get"    , TokenType.M_CLIENT_GET);
    keysTokens.put("invoice.list"  , TokenType.M_INVOICE_LIST);
    keysTokens.put("invoice.delete", TokenType.M_INVOICE_DELETE);
    keysTokens.put("invoice.get"   , TokenType.M_INVOICE_GET);
    keysTokens.put("invoice.update", TokenType.M_INVOICE_UPDATE);
    keysTokens.put("recurring.list", TokenType.M_RECURRING_LIST);

    // keys
    keysTokens.put("-id"          , TokenType.KEY_ID);
    keysTokens.put("-email"       , TokenType.KEY_EMAIL);
    keysTokens.put("-username"    , TokenType.KEY_USERNAME);
    keysTokens.put("-updated_from", TokenType.KEY_UPDATED_FROM);
    keysTokens.put("-updated_to"  , TokenType.KEY_UPDATED_TO);
    keysTokens.put("-page"        , TokenType.KEY_PAGINATION_PAGE);
    keysTokens.put("-per_page"    , TokenType.KEY_PAGINATION_PER_PAGE);
    keysTokens.put("-folder"      , TokenType.KEY_FOLDER);
    keysTokens.put("-notes"       , TokenType.KEY_NOTES);
    keysTokens.put("-organization", TokenType.KEY_ORGANIZATION);
    keysTokens.put("-recurring_id", TokenType.KEY_RECURRING_ID);
    keysTokens.put("-client_id"   , TokenType.KEY_CLIENT_ID);
    keysTokens.put("-status"      , TokenType.KEY_STATUS);
    keysTokens.put("-date_from"   , TokenType.KEY_DATE_FROM);
    keysTokens.put("-date_to"     , TokenType.KEY_DATE_TO);
    keysTokens.put("-date"        , TokenType.KEY_DATE);
    keysTokens.put("-show"        , TokenType.KEY_SHOW);
    keysTokens.put("-first_name"  , TokenType.KEY_FIRST_NAME);
    keysTokens.put("-last_name"   , TokenType.KEY_LAST_NAME);
    keysTokens.put("-username"    , TokenType.KEY_USERNAME);
    keysTokens.put("-password"    , TokenType.KEY_PASSWORD);
    keysTokens.put("-work_phone"  , TokenType.KEY_WORK_PHONE);
    keysTokens.put("-home_phone"  , TokenType.KEY_HOME_PHONE);
    keysTokens.put("-mobile"      , TokenType.KEY_MOBILE);
    keysTokens.put("-fax"         , TokenType.KEY_FAX);
    keysTokens.put("-p_street1"   , TokenType.KEY_PRIMARY_STREET_1);
    keysTokens.put("-p_street2"   , TokenType.KEY_PRIMARY_STREET_2);
    keysTokens.put("-p_city"      , TokenType.KEY_PRIMARY_CITY);
    keysTokens.put("-p_state"     , TokenType.KEY_PRIMARY_STATE);
    keysTokens.put("-p_country"   , TokenType.KEY_PRIMARY_COUNTRY);
    keysTokens.put("-p_code"      , TokenType.KEY_PRIMARY_CODE);
    keysTokens.put("-s_street1"   , TokenType.KEY_SECONDARY_STREET_1);
    keysTokens.put("-s_street2"   , TokenType.KEY_SECONDARY_STREET_2);
    keysTokens.put("-s_city"      , TokenType.KEY_SECONDARY_CITY);
    keysTokens.put("-s_state"     , TokenType.KEY_SECONDARY_STATE);
    keysTokens.put("-s_country"   , TokenType.KEY_SECONDARY_COUNTRY);
    keysTokens.put("-s_code"      , TokenType.KEY_SECONDARY_CODE);
    
    
    //breaking
    String parts[] = command.split("\\s+");
    for (String part : parts) {
      
      //yeeeeaaahhh...
      TokenType t = keysTokens.get(part); 
      if (t != null) {
        tokens.add(new Token(part, t));
      }
      
      //values
      else if (integerNumber.matcher(part).matches()) {
        tokens.add(new Token(part, TokenType.T_INTEGER));
      }
      else if (realNumber.matcher(part).matches()) {
        tokens.add(new Token(part, TokenType.T_REAL));
      }
      else if (date.matcher(part).matches()) {
        tokens.add(new Token(part, TokenType.T_DATE));
      }
      else if (hour.matcher(part).matches()) {
        tokens.add(new Token(part, TokenType.T_HOUR));
      }
      else if (!parts.equals("")) {
        tokens.add(new Token(part, TokenType.T_TEXT));
      }
      
    }
    
    return tokens;
  }
  
}
