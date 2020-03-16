import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

class LeseData {
	// Oppretter en lenkelister for de forskjellige objektene
	private Liste<Pasient> pasienter;
	private Liste<Legemiddel> legemidler;
	private Liste<Lege> leger;
	private Liste<Resept> resepter;

	public Liste<Pasient> hentPasienter() {
		return pasienter;
	}
	public Liste<Legemiddel> hentLegemidler() {
		return legemidler;
	}
	public Liste<Lege> hentLeger() {
		return leger;
	}
	public Liste<Resept> hentResepter() {
		return resepter;
	}

	public LeseData(String filepath) throws FileNotFoundException {
		pasienter = new Lenkeliste<Pasient>();
		legemidler = new Lenkeliste<Legemiddel>();
		leger = new SortertLenkeliste<Lege>();
		resepter = new Lenkeliste<Resept>();

		File file = new File(filepath);
		Scanner scan = new Scanner(file);

		// Sjekker foerste type data og setter peker til neste linje
		scan.next();
		String typeData = scan.next();
		scan.nextLine();
		// Kjoerer while loop til vi har lest igjennom hele filen
		while (scan.hasNext()) {
			// Leser neste linje og legger den til en array
			String[] data = scan.nextLine().trim().split(",");

			switch(typeData) {
				case "Pasienter": {
					parsePasienter(data);
					break;
				}
				case "Legemidler": {
					parseLegemidler(data);
					break;
				}
				case "Leger": {
					parseLeger(data);
					break;
				}
				case "Resepter": {
					parseResepter(data);
					break;
				}
			}
			// Sjekker om neste linje er ny type objekt
			if (scan.hasNext("#")) {
				scan.next();
				typeData = scan.next();
				scan.nextLine();
			}
		}
		scan.close();
	}

	private void parsePasienter(String[] data) {
		// Soerger for at vi har riktig mengde med argumenter. Ignorerer ugyldige objekter
		if (data.length != 2) {
			return;
		}

		String pasientNavn = data[0];
		String fodselsnummer = data[1];

		Pasient pasient = new Pasient(pasientNavn, fodselsnummer);
		pasienter.leggTil(pasient);
	}

	private void parseLegemidler(String[] data) {
		if (data.length != 4 && data.length != 5) {
			return;
		}
		String legemiddelNavn = data[0];
		String type = data[1];
		// Sjekker for ugyldig type og lengde i henhold til hvilken type
		if (!(type.equals("narkotisk") || type.equals("vanedannende") || type.equals("vanlig"))
			|| (type.equals("vanlig") && data.length != 4)
			|| ((type.equals("narkotisk") || type.equals("vanedannende")) && data.length != 5)) {
			return;
		}

		double pris;
		double virkestoff;
		int styrke = 0;
		// Tester for ugyldig data
		try {
			pris = Double.parseDouble(data[2]);
			virkestoff = Double.parseDouble(data[3]);
			if (data.length == 5) {
				styrke = Integer.parseInt(data[4]);
			}
		} catch (NumberFormatException nfe) {
			return;
		}

		Legemiddel legemiddel = null;
		switch(type) {
			case "narkotisk":
				legemiddel = new Narkotisk(legemiddelNavn, pris, virkestoff, styrke);
				break;
			case "vanedannende":
				legemiddel = new Vanedannende(legemiddelNavn, pris, virkestoff, styrke);
				break;
			case "vanlig":
				legemiddel = new Vanlig(legemiddelNavn, pris, virkestoff);
				break;
		}
		legemidler.leggTil(legemiddel);

	}

	private void parseLeger(String[] data) {
		// Soerger for at vi har riktig mengde med argumenter
		if (data.length != 2) {
			return;
		}

		String legeNavn = data[0];
		int kontrollId;
		// Tester for ugylidg data
		try {
			kontrollId = Integer.parseInt(data[1]);
		} catch (NumberFormatException nfe) {
			return;
		}

		Lege lege;
		if (kontrollId != 0) {
			lege = new Spesialist(legeNavn, kontrollId);
		} else {
			lege = new Lege(legeNavn);
		}
		leger.leggTil(lege);
	}

	private void parseResepter(String[] data) {
		if (data.length != 4 && data.length != 5) {
			return;
		}
		String typeResept = data[3];
		String legeNavn = data[1];
		if (!(typeResept.equals("hvit") || typeResept.equals("blaa") || typeResept.equals("millitaer") || typeResept.equals("p"))
			|| (typeResept.equals("p") && data.length != 4)
			|| (!typeResept.equals("p") && data.length != 5)) {
			return;
		}

		int legemiddelNr;
		int pasientId;
		int reit = 0;
		try {
			legemiddelNr = Integer.parseInt(data[0]);
			pasientId = Integer.parseInt(data[2]);
			if (data.length == 5) {
				reit = Integer.parseInt(data[4]);
			}
		} catch (NumberFormatException nfe) {
			return;
		}

		// Sjekker om gitt legemiddel eller pasient eksisterer
		if ((legemiddelNr > legemidler.stoerrelse())
			|| (pasientId > pasienter.stoerrelse())
			|| (legemiddelNr < 0)
			|| (pasientId < 0)) {
			return;
		}
		Legemiddel legemiddel = legemidler.hent(legemiddelNr);
		Pasient pasient = pasienter.hent(pasientId);
		Resept resept = null;
		// Iterer over leger listen og finner fram til riktig lege. Hvis navnet ikke finnes skjer ingenting
		for (Lege lege : leger) {
			if (lege.hentLegeNavn().equals(legeNavn)) {
				try {
					switch(typeResept) {
						case "hvit":
							resept = lege.skrivHvitResept(legemiddel, pasient, reit);
							break;
						case "blaa":
							resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
							break;
						case "millitaer":
							resept = lege.skrivMilitaerResept(legemiddel, pasient, reit);
							break;
						case "p":
							resept = lege.skrivPResept(legemiddel, pasient);
							break;

					}
				} catch (UlovligUtskrift uu) {
					// melding brukes til debugging, la staa kommentert
					/*
					for (String d : data) {
						System.out.print(d + ",");
					}
					System.out.println("");
					*/
					return;
				}
				resepter.leggTil(resept);
				return;
			}
		}
	}

	// For testing
	/*
	public static void main(String[] args) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + File.separator + "myeInndata.txt";
		LeseData data = new LeseData(path);

		for (Pasient p : data.hentPasienter()) {
			System.out.printf("Navn: %s\nFnr: %s\n\n", p.hentNavn(), p.hentFnr());
		}
		
		for (Legemiddel l : data.hentLegemidler()) {
			System.out.println(l);
		}

		for (Lege l : data.hentLeger()) {
			System.out.println(l);
		}

		for (Resept r : data.hentResepter()) {
			System.out.println(r);
		}
	}
	*/
}