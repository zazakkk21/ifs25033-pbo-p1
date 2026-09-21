import java.util.Scanner;

public class App {

  private static final String[] SIMBOL = {"PA", "T", "K", "P", "UTS", "UAS"};

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int bobotPartisipatif = scanner.nextInt();
    int bobotTugas = scanner.nextInt();
    int bobotKuis = scanner.nextInt();
    int bobotProyek = scanner.nextInt();
    int bobotUts = scanner.nextInt();
    int bobotUas = scanner.nextInt();
    scanner.nextLine();

    int[] bobot = {bobotPartisipatif, bobotTugas, bobotKuis, bobotProyek, bobotUts, bobotUas};
    int jumlahBobot = bobot(bobotKuis, bobotPartisipatif, bobotProyek, bobotTugas, bobotUas, bobotUts);
    if (jumlahBobot != 100) {
      System.out.println("Total bobot harus 100");
      scanner.close();
      return;
    }

    int[] totalBobotX = new int[6];
    int[] totalPerolehanX = new int[6];

    while (true) {
      String getInput = scanner.nextLine().trim();
      if (getInput.equals("---")) {
        break;
      }

      String[] arrString = getInput.split("\\|");
      if (arrString.length != 3) {
        System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
        continue;
      }

      for (int i = 0; i < 3; i++) {
        arrString[i] = arrString[i].trim();
      }

      int index = getIndexSimbol(arrString[0]);
      if (index == -1) {
        System.out.println("Simbol tidak dikenal");
        continue;
      }

      try {
        totalBobotX[index] += Integer.parseInt(arrString[1]);
        totalPerolehanX[index] += Integer.parseInt(arrString[2]);
      } catch (NumberFormatException e) {
        System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
        continue;
      }
    } // ===> end of while

    for (int i = 0; i < 6; i++) {
      totalPerolehanX[i] = clamp(totalPerolehanX[i], totalBobotX[i]);
    }

    int[] persentasePerolehanX = new int[6];
    for (int i = 0; i < 6; i++) {
      persentasePerolehanX[i] = perolehanX100(totalPerolehanX[i], totalBobotX[i]);
    }

    double[] kontribusiX = new double[6];
    for (int i = 0; i < 6; i++) {
      kontribusiX[i] = kontribusi(persentasePerolehanX[i], bobot[i]);
    }

    float nilaiAkhir = 0;
    for (int i = 0; i < 6; i++) {
      nilaiAkhir += kontribusiX[i];
    }

    String hasilAkhir = getGrade(nilaiAkhir);

    tampilkan(persentasePerolehanX, kontribusiX, nilaiAkhir, bobot, hasilAkhir);
    scanner.close();
  }

  public static int getIndexSimbol(String simbol) {
    for (int i = 0; i < SIMBOL.length; i++) {
      if (SIMBOL[i].equals(simbol)) {
        return i;
      }
    }
    return -1;
  }

  public static int bobot(int a, int b, int c, int d, int e, int f) {
    return a + b + c + d + e + f;
  }

  public static int perolehanX100(int perolehan, int bobot) {
    if (bobot == 0) {
      return 0;
    }
    return (perolehan * 100) / bobot;
  }

  public static double kontribusi(int perolehan, int bobot) {
    return (perolehan / 100.0) * bobot;
  }

  public static int clamp(int perolehan, int bobot) {
    int hasil = perolehan;
    if (hasil > bobot) {
      hasil = Math.min(perolehan, bobot);
    } else if (hasil < 0) {
      hasil = Math.max(0, perolehan);
    }
    return hasil;
  }

  public static String getGrade(double nilai) {
    String grade;
    if (nilai >= 79.5) {
      grade = "A";
    } else if (nilai >= 72) {
      grade = "AB";
    } else if (nilai >= 64.5) {
      grade = "B";
    } else if (nilai >= 57) {
      grade = "BC";
    } else if (nilai >= 49.5) {
      grade = "C";
    } else if (nilai >= 34) {
      grade = "D";
    } else {
      grade = "E";
    }
    return grade;
  }

  public static void tampilkan(int[] perolehan, double[] kontribusi, float nilaiAkhir, int[] bobot, String grade) {
    System.out.println("Perolehan Nilai:");
    System.out.printf(">> Partisipatif: %d/100 (%.2f/%d)\n", perolehan[0], kontribusi[0], bobot[0]);
    System.out.printf(">> Tugas: %d/100 (%.2f/%d)\n", perolehan[1], kontribusi[1], bobot[1]);
    System.out.printf(">> Kuis: %d/100 (%.2f/%d)\n", perolehan[2], kontribusi[2], bobot[2]);
    System.out.printf(">> Proyek: %d/100 (%.2f/%d)\n", perolehan[3], kontribusi[3], bobot[3]);
    System.out.printf(">> UTS: %d/100 (%.2f/%d)\n", perolehan[4], kontribusi[4], bobot[4]);
    System.out.printf(">> UAS: %d/100 (%.2f/%d)\n", perolehan[5], kontribusi[5], bobot[5]);

    System.out.printf("\n>> Nilai Akhir: %.2f\n", nilaiAkhir);
    System.out.printf(">> Grade: %s", grade);
  }
}