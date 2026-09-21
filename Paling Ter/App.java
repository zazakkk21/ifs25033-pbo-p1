import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class App {
  public static void main(String[] args) {
    ArrayList<Integer> listData = new ArrayList<>();
    HashMap<Integer, Integer> frekuensi = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    listData = getArray(scanner, listData);

    if (listData.isEmpty()) {
      System.out.println("Data kosong");
      scanner.close();
      return;
    }

    frekuensi = getFrekuensi(frekuensi, listData);
    Map.Entry<Integer, Integer> terbanyak = getTerbanyak(frekuensi);
    Map.Entry<Integer, Integer> tersedikit = getTersedikit(frekuensi);
    int tertinggi = getTertinggi(listData);
    int terendah = getTerendah(listData);
    int frekTertinggi = getFrekTertinggi(frekuensi, tertinggi);
    int frekTerendah = getFrekTerendah(frekuensi, terendah);
    int jumlahTertinggi = getJumlahTertinggi(frekTertinggi, tertinggi);
    int jumlahTerendah = getJumlahTerendah(frekTerendah, terendah);

    tampilkan(tertinggi, terendah, terbanyak, tersedikit, jumlahTertinggi, jumlahTerendah, frekTertinggi, frekTerendah);
    scanner.close();
  }

  public static ArrayList<Integer> getArray(Scanner scanner, ArrayList<Integer> listData) {
    while (true) {
      String inputString = scanner.nextLine().trim();
      if (inputString.equals("---")) {
        break;
      }
      try {
        int input = Integer.parseInt(inputString);
        listData.add(input);
      } catch (NumberFormatException e) {
        System.out.println("Input tidak valid, harus berupa angka");
      }
    }
    return listData;
  }

  public static int getTertinggi(ArrayList<Integer> listData) {
    int maksimum = listData.get(0);
    for (int i = 1; i < listData.size(); i++) {
      if (listData.get(i) > maksimum) {
        maksimum = listData.get(i);
      }
    }
    return maksimum;
  }

  public static int getTerendah(ArrayList<Integer> listData) {
    int minimum = listData.get(0);
    for (int i = 1; i < listData.size(); i++) {
      if (listData.get(i) < minimum) {
        minimum = listData.get(i);
      }
    }
    return minimum;
  }

  public static HashMap<Integer, Integer> getFrekuensi(HashMap<Integer, Integer> frekuensi, ArrayList<Integer> listData) {
    for (int data : listData) {
      if (frekuensi.containsKey(data)) {
        frekuensi.put(data, frekuensi.get(data) + 1);
      } else {
        frekuensi.put(data, 1);
      }
    }
    return frekuensi;
  }

  public static Map.Entry<Integer, Integer> getTerbanyak(HashMap<Integer, Integer> frekuensi) {
    Map.Entry<Integer, Integer> terbanyakKey = null;
    int terbanyak = 0;
    for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
      if (terbanyakKey == null || entry.getValue() > terbanyak
          || (entry.getValue() == terbanyak && entry.getKey() > terbanyakKey.getKey())) {
        terbanyak = entry.getValue();
        terbanyakKey = entry;
      }
    }
    return terbanyakKey;
  }

  public static Map.Entry<Integer, Integer> getTersedikit(HashMap<Integer, Integer> frekuensi) {
    Map.Entry<Integer, Integer> tersedikitKey = null;
    int tersedikit = Integer.MAX_VALUE;
    for (Map.Entry<Integer, Integer> entry : frekuensi.entrySet()) {
      if (tersedikitKey == null || entry.getValue() < tersedikit
          || (entry.getValue() == tersedikit && entry.getKey() < tersedikitKey.getKey())) {
        tersedikit = entry.getValue();
        tersedikitKey = entry;
      }
    }
    return tersedikitKey;
  }

  public static int getFrekTertinggi(HashMap<Integer, Integer> frekuensi, int tertinggi) {
    return frekuensi.get(tertinggi);
  }

  public static int getFrekTerendah(HashMap<Integer, Integer> frekuensi, int terendah) {
    return frekuensi.get(terendah);
  }

  public static int getJumlahTertinggi(int frekTertinggi, int tertinggi) {
    return frekTertinggi * tertinggi;
  }

  public static int getJumlahTerendah(int frekTerendah, int terendah) {
    return frekTerendah * terendah;
  }

  public static void tampilkan(int tertinggi, int terendah, Map.Entry<Integer, Integer> terbanyak,
      Map.Entry<Integer, Integer> tersedikit, int jumlahTertinggi, int jumlahTerendah,
      int frekTertinggi, int frekTerendah) {
    System.out.printf("Tertinggi: %d\n", tertinggi);
    System.out.printf("Terendah: %d\n", terendah);
    System.out.printf("Terbanyak: %d (%dx)\n", terbanyak.getKey(), terbanyak.getValue());
    System.out.printf("Tersedikit: %d (%dx)\n", tersedikit.getKey(), tersedikit.getValue());
    System.out.printf("Jumlah Tertinggi: %d * %d = %d\n", tertinggi, frekTertinggi, jumlahTertinggi);
    System.out.printf("Jumlah Terendah: %d * %d = %d\n", terendah, frekTerendah, jumlahTerendah);
  }
}