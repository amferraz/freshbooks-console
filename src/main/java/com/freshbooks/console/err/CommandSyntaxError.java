package com.freshbooks.console.err;

public class CommandSyntaxError extends Exception {
  
  public CommandSyntaxError(String message) {
    super (message);
  }

}
