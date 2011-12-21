package br.com.jusbrasil.freshbooks;

public class Token {

  private String value;
  private TokenType type;

  public Token(String value, TokenType type) {
    this.value = value;
    this.type = type;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the type
   */
  public TokenType getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(TokenType type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return "{ " + this.value + " : " +  this.type.toString() + " }";
  }

}
