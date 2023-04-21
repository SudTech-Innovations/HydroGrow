package application;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.commons.csv.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MonJFrame extends JFrame {

	public MonJFrame() {
		// Titre de la fenêtre
		super("HydroGrow Interface");

		// Taille de la fenêtre
		setSize(500, 500);

		// Action lors de la fermeture de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialisation des composants
		init();

		// Affichage de la fenêtre
		setVisible(true);

	}

	public void init() {
		// setLayout(null);
		String csvFile = ClassLoader.getSystemClassLoader().getResource("data/plantes.csv").getFile();
		
		// Création d'une JTable pour afficher les données du CSV
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 270, 450, 150);

		// JLabel pour la sélection de plante
		JLabel labelPlante = new JLabel("Plante : ");
		labelPlante.setBounds(20, 10, 70, 50); // Définissez les coordonnées et la taille du label

		// Création d'une combobox pour la sélection de plante
		String[] plantes = { "Tomates", "Carottes", "Salade", "Radis", "Poivrons" };
		JComboBox<String> comboBox = new JComboBox<>(plantes);
		comboBox.setBounds(120, 20, 150, 30);

		// JLabel pour la température
		JLabel labelTemp = new JLabel("Température : ");
		labelTemp.setBounds(20, 70, 100, 30);

		// JTextField pour la température
		JTextField tempField = new JTextField();
		tempField.setBounds(120, 70, 150, 30);

		// JLabel pour l'humidité
		JLabel labelHum = new JLabel("Humidité : ");
		labelHum.setBounds(20, 120, 100, 30);

		// JTextField pour l'humidité
		JTextField humField = new JTextField();
		humField.setBounds(120, 120, 150, 30);

		// JButton pour reset
		JButton resetButton = new JButton("Reset");
		resetButton.setBounds(20, 170, 100, 30);

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempField.setText(""); // Réinitialisation du champ de texte pour la température
				humField.setText(""); // Réinitialisation du champ de texte pour l'humidité
				// labelOutput.setText(""); // Réinitialisation du champ de texte pour
				// l'affichage de sortie
			}
		});

		// JButton pour valider
		JButton validButton = new JButton("Valider");
		validButton.setBounds(170, 170, 100, 30);

		// JLabel pour l'affichage de sortie
		JLabel labelOutput = new JLabel(" ");
		labelOutput.setBounds(20, 220, 250, 30);

		// Action pour le bouton Valider
		validButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Récupération des valeurs saisies
				String planteSelectionnee = comboBox.getSelectedItem().toString();
				double temperature = Double.parseDouble(tempField.getText());
				double humidite = Double.parseDouble(humField.getText());

				// Vérification des conditions optimales
				Plante plante = Index.getPlante(planteSelectionnee);
				Environnement env = new Environnement(temperature, humidite);
				boolean isOptimal = env.isOptimal(plante);

				// Affichage du résultat
				if (isOptimal) {
					labelOutput.setText("Les conditions sont optimales pour la plante sélectionnée !");
				} else {
					labelOutput.setText("Les conditions ne sont pas optimales pour la plante sélectionnée !");
				}
			}
		});

		// Ajout des éléments dans le JFrame
		add(labelPlante);
		add(comboBox);
		add(labelTemp);
		add(tempField);
		add(labelHum);
		add(humField);
		add(resetButton);
		add(validButton);
		add(labelOutput);

		// Affichage de la fenêtre
		setVisible(true);
	}

	public static void main(String[] args) {
		new MonJFrame();
	}
}
