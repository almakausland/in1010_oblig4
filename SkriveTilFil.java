import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

class SkriveTilFil {

	public SkriveTilFil(
		Liste<Pasient> pasienter,
		Liste<Legemiddel> legemidler,
		Liste<Lege> leger,
		Liste<Resept> resepter) throws IOException{
		FileWriter fw = new FileWriter("output.txt", true);
		PrintWriter output = new PrintWriter(fw);

		pasienterTilFil(pasienter, output);
		legemidlerTilFil(legemidler, output);
		legerTilFil(leger, output);
		resepterTilFil(resepter, output);

		output.close();
		fw.close();
	}

	private void pasienterTilFil(Liste<Pasient> pasienter, PrintWriter output) {
		output.println("# Pasienter (navn, fnr)");
		String pasient;

		for (Pasient p : pasienter) {
			pasient = String.format("%s,%s", p.hentNavn(), p.hentFnr());
			output.println(pasient);
		}
	}

	private void legemidlerTilFil(Liste<Legemiddel> legemidler, PrintWriter output) {
		output.println("# Legemidler (navn,type,pris,virkestoff,[styrke]");
	}

	private void legerTilFil(Liste<Lege> leger, PrintWriter output) {
		output.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
	}

	private void resepterTilFil(Liste<Resept> resepter, PrintWriter output) {
		output.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit]");
	}
}