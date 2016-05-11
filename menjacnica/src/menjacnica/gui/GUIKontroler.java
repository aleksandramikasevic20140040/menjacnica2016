package menjacnica.gui;

import java.awt.EventQueue;

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
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void dodajKursProzor() {
		DodajKursGUI prozorDodajKurs = new DodajKursGUI(prozor);
		prozorDodajKurs.setLocationRelativeTo(prozor.getContentPane());
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
		prozorIzvrsiZamenu.setLocationRelativeTo(prozor.getContentPane());
		prozorIzvrsiZamenu.setVisible(true);
		valutaZameni = v;
		prozorIzvrsiZamenu.prikaziValutu(v.getProdajni(), v.getKupovni(), v.getSkraceniNaziv());
	}
	
	public static void obrisiKursProzor(Valuta v) {
		ObrisiKursGUI prozorObrisiKurs = new ObrisiKursGUI();
		prozorObrisiKurs.setLocationRelativeTo(prozor.getContentPane());
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
	
}
