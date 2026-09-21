import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String nim = scanner.nextLine().trim();
    int panjangNim = nim.length();

    if (panjangNim != 8) {
      System.out.println("NIM harus 8 karakter");
      scanner.close();
      return;
    }
    if (getProdi(nim).equals("x")) {
      System.out.println("Kode tidak tersedia");
      scanner.close();
      return;
    }
    tampilkan(nim);
    scanner.close();
  }

  public static String getProdi(String nim) {
    String prefixNim = nim.substring(0, 3);
    String prodi;
    switch (prefixNim) {
      case "11S": prodi = "Sarjana Informatika"; break;
      case "12S": prodi = "Sarjana Sistem Informasi"; break;
      case "13S": prodi = "Sarjana Teknik Elektro"; break;
      case "21S": prodi = "Sarjana Manajemen Rekayasa"; break;
      case "22S": prodi = "Sarjana Teknik Metalurgi"; break;
      case "31S": prodi = "Sarjana Teknik Bioproses"; break;
      case "32S": prodi = "Sarjana Bioteknologi"; break;
      case "114": prodi = "Diploma 4 Teknologi Rekayasa Perangkat Lunak"; break;
      case "113": prodi = "Diploma 3 Teknologi Informasi"; break;
      case "133": prodi = "Diploma 3 Teknologi Komputer"; break;
      default: return "x";
    }
    return prodi;
  }

  public static int getTahun(String nim) {
    String tahunString = "20" + nim.substring(3, 5);
    return Integer.parseInt(tahunString);
  }

  public static int getUrutan(String nim) {
    String urutanString = nim.substring(5, 8);
    return Integer.parseInt(urutanString);
  }

  public static void tampilkan(String nim) {
    String prodi = getProdi(nim);
    int tahun = getTahun(nim);
    int urutan = getUrutan(nim);

    System.out.printf("Informasi NIM %s: \n", nim);
    System.out.println(">> Program Studi: " + prodi);
    System.out.println(">> Angkatan: " + tahun);
    System.out.println(">> Urutan: " + urutan);
  }
}