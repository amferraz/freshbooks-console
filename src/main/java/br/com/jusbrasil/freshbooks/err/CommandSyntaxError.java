package br.com.jusbrasil.freshbooks.err;

public class CommandSyntaxError extends Exception {
  
  public CommandSyntaxError(String message) {
    super (message);
  }

}
