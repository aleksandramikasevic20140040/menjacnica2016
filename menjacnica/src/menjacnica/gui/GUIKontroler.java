package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
	private static MenjacnicaGUI prozor;
	private static MenjacnicaInterface menjacnica;
	private static Valuta valutaObrisi;
	private static Valuta valutaZameni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prozor = new MenjacnicaGUI();
					prozor.setVisible(true);
					menjacnica = new Menjacnica();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void dodajKursProzor() {
		DodajKursGUI prozorDodajKurs = new DodajKursGUI();
		prozorDodajKurs.setLocationRelativeTo(null);
		prozorDodajKurs.setVisible(true);
	}
	
	public static void dodajKurs(String naziv, String skraceniNaziv, Object sifra, String prodajni, String kupovni, String srednji) {
		Valuta valuta = new Valuta();
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceniNaziv);
		valuta.setSifra((int) sifra);
		valuta.setProdajni(Double.parseDouble(prodajni));
		valuta.setKupovni(Double.parseDouble(kupovni));
		valuta.setSrednji(Double.parseDouble(srednji));
		
		menjacnica.dodajValutu(valuta);
		prozor.prikaziSveValute();
		
	}
	public static void izvrsiZamenuProzor(Valuta v) {
		IzvrsiZamenuGUI prozorIzvrsiZamenu = new IzvrsiZamenuGUI();
		prozorIzvrsiZamenu.setLocationRelativeTo(null);
		prozorIzvrsiZamenu.setVisible(true);
		valutaZameni = v;
		prozorIzvrsiZamenu.prikaziValutu(v.getProdajni(), v.getKupovni(), v.getSkraceniNaziv());
	}
	
	public static void obrisiKursProzor(Valuta v) {
		ObrisiKursGUI prozorObrisiKurs = new ObrisiKursGUI();
		prozorObrisiKurs.setLocationRelativeTo(null);
		prozorObrisiKurs.setVisible(true);
		valutaObrisi = v;
		prozorObrisiKurs.prikaziValutu(v.getNaziv(), v.getSkraceniNaziv(), v.getProdajni(), v.getKupovni(), v.getSrednji(), v.getSifra());
		
	}
	
	public static double  izvrsiZamenu(boolean prodaja, String iznos) {
		
		return menjacnica.izvrsiTransakciju(valutaZameni, prodaja, Double.parseDouble(iznos));
		
	}
	

	public static void obrisiKurs() {
		// TODO Auto-generated method stub
		menjacnica.obrisiValutu(valutaObrisi);
		prozor.prikaziSveValute();

	}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(prozor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				prozor.prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(prozor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	

	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(prozor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(prozor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static List<Valuta> vratiSveValute() {
		return menjacnica.vratiKursnuListu();
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(prozor.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
}
