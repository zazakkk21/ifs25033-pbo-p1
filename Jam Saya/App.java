import java.util.Scanner;

public class App {
  private static final int MENIT_DALAM_SEHARI = 1440;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String inputJam = scanner.nextLine().trim();
    int totalMenitPerintah = 0;
    int pergantianHari = 0;

    if (isJamValid(inputJam)) {
      int totalMenitAwal = getTotalMenit(inputJam);
      int totalMenitSekarang = getTotalMenit(inputJam);
      while (true) {
        String inputPerintah = scanner.nextLine().trim();
        if (inputPerintah.equals("---")) {
          break;
        }

        if (isInputPerintahValid(inputPerintah)) {
          if (getPerintah(inputPerintah) == '+') {
            totalMenitSekarang += getN(inputPerintah);
            totalMenitPerintah += getN(inputPerintah);
            if (totalMenitSekarang >= MENIT_DALAM_SEHARI) {
              pergantianHari++;
              totalMenitSekarang -= MENIT_DALAM_SEHARI;
            }
          } else {
            totalMenitSekarang -= getN(inputPerintah);
            totalMenitPerintah -= getN(inputPerintah);
            if (totalMenitSekarang < 0) {
              pergantianHari++;
              totalMenitSekarang += MENIT_DALAM_SEHARI;
            }
          }
        } else {
          System.out.println("Perintah tidak valid");
        }
      }
      tampilkan(totalMenitAwal, totalMenitSekarang, totalMenitPerintah, pergantianHari);
    } else {
      System.out.println("Jam tidak valid");
    }

    scanner.close();
  }

  public static int getTotalMenit(String inputJam) {
    String[] arrJamMenit = inputJam.split("\\:");
    int jam = Integer.parseInt(arrJamMenit[0]);
    int menit = Integer.parseInt(arrJamMenit[1]);
    return (jam * 60) + menit;
  }

  public static boolean isJamValid(String inputJam) {
    boolean valid = true;
    try {
      String[] arrJamMenit = inputJam.split("\\:");
      if (arrJamMenit.length != 2) {
        return false;
      }
      int totalMenit = getTotalMenit(inputJam);
      if (totalMenit > (MENIT_DALAM_SEHARI - 1) || totalMenit < 0
          || Integer.parseInt(arrJamMenit[0]) > 23 || Integer.parseInt(arrJamMenit[1]) > 59
          || Integer.parseInt(arrJamMenit[0]) < 0 || Integer.parseInt(arrJamMenit[1]) < 0) {
        return false;
      }
    } catch (NumberFormatException e) {
      valid = false;
    }
    return valid;
  }

  public static boolean isInputPerintahValid(String perintah) {
    if (perintah.length() < 2) {
      return false;
    }
    boolean isValid = true;
    try {
      Integer.parseInt(perintah.substring(1));
      if (perintah.charAt(0) != '-' && perintah.charAt(0) != '+') {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
    return isValid;
  }

  public static int getN(String perintah) {
    return Integer.parseInt(perintah.substring(1));
  }

  public static char getPerintah(String perintah) {
    return perintah.charAt(0);
  }

  public static void tampilkan(int totalMenitAwal, int totalMenitSekarang, int totalMenitPerintah, int pergantianHari) {
    System.out.printf("Jam Awal: %02d:%02d\n", (totalMenitAwal / 60), (totalMenitAwal % 60));
    System.out.printf("Jam Akhir: %02d:%02d\n", (totalMenitSekarang / 60), (totalMenitSekarang % 60));
    if (totalMenitPerintah > 0) {
      System.out.printf("Total Menit: +%d\n", totalMenitPerintah);
    } else {
      System.out.printf("Total Menit: %d\n", totalMenitPerintah);
    }
    System.out.printf("Pergantian Hari: %d\n", pergantianHari);
  }
}