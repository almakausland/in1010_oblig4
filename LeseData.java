import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

class LeseData {
	//oppretter en lenkelister for de forskjellige objektene
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
		leger = new Lenkeliste<Lege>();
		resepter = new Lenkeliste<Resept>();

		File file = new File(filepath);
		Scanner scan = new Scanner(file);

		//sjekker foerste type data og setter peker til neste linje
		scan.next();
		String typeData = scan.next();
		scan.nextLine();
		while (scan.hasNext()) {
			String[] data = scan.nextLine().trim().split(",");

			switch(typeData) {
				case "Pasienter":
					//soerger for at vi har riktig mengde med argumenter
					if (data.length != 2) {
						break;
					}

					String pasientNavn = data[0];
					String fodselsnummer = data[1];

					Pasient pasient = new Pasient(pasientNavn, fodselsnummer);
					pasienter.leggTil(pasient);
					break;
				case "Legemidler":
					String type = data[1];
					//Sjekker for ugyldig type og lengde i henhold til hvilken type
					if (!(type.equals("narkotisk") || type.equals("vanedannende") || type.equals("vanlig"))
						|| (type.equals("vanlig") && data.length != 4)
						|| ((type.equals("narkotisk") || type.equals("vanedannende")) && data.length != 5)) {
						break;
					}

					String legemiddelNavn = data[0];
					double pris = Double.parseDouble(data[2]);
					double virkestoff = Double.parseDouble(data[3]);
					int styrke = 0;
					if (data.length == 5) {
						styrke = Integer.parseInt(data[4]);
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
					}
					legemidler.leggTil(legemiddel);
					break;
				case "Leger":
					//soerger for at vi har riktig mengde med argumenter
					if (data.length != 2) {
						break;
					}

					String legeNavn = data[0];
					int kontrollId = Integer.parseInt(data[1]);

					Lege lege;
					if (kontrollId != 0) {
						lege = new Spesialist(legeNavn, kontrollId);
					} else {
						lege = new Lege(legeNavn);
					}
					leger.leggTil(lege);
					break;
				/*
				case "Resepeter:":
					int legemiddelNr = Integer.parseInt(data[0]);
					String legeNavn = data[1];
					int pasientId = Integer.parseInt(data[2]);
					String typeResept = data[3];
					int reit = Integer.parseInt(data[4]);

					Resept resept;
					switch(typeResept) {
						case "hvit":
						case "blaa":
						case "millitaer":

					}
					*/
			}
			if (scan.hasNext("#")) {
				scan.next();
				typeData = scan.next();
				scan.nextLine();
			}
		}
		scan.close();
	}

	//for testing
	/*
	public static void main(String[] args) throws FileNotFoundException {
		String path = System.getProperty("user.dir") + File.separator + "inndata.txt";
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
	}
	*/
}