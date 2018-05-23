package recursos;

import java.text.NumberFormat;
import java.util.Locale;

public class teste {

  public static void main(String[] args) {
    System.out.println(isCurrencyFormat("R$ 1.000,00"));
    System.out.println(formataPorra("1.20"));
  }

  static boolean isLessThenThreeHundred(String s) {
    return s.matches("[^1-9]*[12]?[0-9]{1,2}[^0-9]*");
  }
  
  static boolean isCurrencyFormat(String s) {
    return s.matches("(^R\\$\\s*(\\d{1,3}\\.)?(\\d{3}\\.)*(\\d{3},\\d{2})\\s*$)|(^R\\$\\s*\\d{1,3},\\d{2}\\s*$)");
  }
  
  static String formataPorra (String s) {
    NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(Locale.getDefault());
    formatoMoeda.setMaximumFractionDigits(2);
    formatoMoeda.setMinimumFractionDigits(2);
    return formatoMoeda.format(Double.parseDouble(s));
  }

}
