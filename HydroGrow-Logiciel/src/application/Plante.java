package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Plante {
    private String nom;
    private double humiditeRecommandee;
    private double temperatureRecommandee;

    public Plante(String nomPlante, double humiditeRecommandee2, double temperatureRecommandee2) {
        this.nom = nomPlante;
        // Récupération des valeurs d'humidité et de température recommandées à partir
        // du fichier CSV
        String csvFile = "C:\\Users\\mrcan\\OneDrive\\Bureau\\HydroGrow\\HydroGrow-Logiciel\\data\\plantes.csv";
        String line = "";
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] plante = line.split(cvsSplitBy);
                if (plante[0].equals(nomPlante)) {
                    humiditeRecommandee = Double.parseDouble(plante[1]);
                    temperatureRecommandee = Double.parseDouble(plante[2]);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }

    public double getHumiditeRecommandee() {
        return humiditeRecommandee;
    }

    public double getTemperatureRecommandee() {
        return temperatureRecommandee;
    }

    public static Plante getPlanteFromCSV(String nomPlante) {

        String csvFile = "C:\\Users\\mrcan\\OneDrive\\Bureau\\HydroGrow\\HydroGrow-Logiciel\\data\\plantes.csv";
        String line = "";
        String cvsSplitBy = ";";

        // Lire le fichier CSV
        try {
            Scanner scanner = new Scanner(new File(csvFile));
            scanner.nextLine(); // Ignorer la première ligne (les noms des colonnes)

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] valeurs = ligne.split(";");
                String nom = valeurs[0];
                double humiditeRecommandee = Double.parseDouble(valeurs[1]);
                double temperatureRecommandee = Double.parseDouble(valeurs[2]);

                // Comparer le nom de la plante
                if (nom.equalsIgnoreCase(nomPlante)) {
                    return new Plante(nom, humiditeRecommandee, temperatureRecommandee);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // La plante n'a pas été trouvée
        return null;
    }
}
