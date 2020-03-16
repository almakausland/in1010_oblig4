import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

class SkriveTilFil {

	public SkriveTilFil(
		Liste<Pasient> pasienter,
		Liste<Legemiddel> legemidler,
		Liste<Lege> leger,
		Liste<Resept> resepter,
		String filnavn) throws IOException{
		FileWriter fw = new FileWriter(String.format("%s.txt", filnavn), true);
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
		String pasient = null;

		for (Pasient p : pasienter) {
			pasient = String.format("%s,%s", p.hentNavn(), p.hentFnr());
			output.println(pasient);
		}
	}

	private void legemidlerTilFil(Liste<Legemiddel> legemidler, PrintWriter output) {
		output.println("# Legemidler (navn,type,pris,virkestoff,[styrke]");
		String legemiddel = null;

		for (Legemiddel lm : legemidler) {
			if (lm instanceof Vanlig) {
				legemiddel = String.format("%s,vanlig,%s,%s",
					lm.hentNavn(),
					String.valueOf(lm.hentPris()),
					String.valueOf(lm.hentVirkestoff()));
			} else {
				String type;
				int styrke = 0;
				if (lm instanceof Narkotisk) {
					type = "narkotisk";
					styrke = ((Narkotisk) lm).hentNarkotiskStyrke();
				} else {
					type = "vanedannende";
					styrke = ((Vanedannende) lm).hentVanedannendeStyrke();
				}
				legemiddel = String.format("%s,%s,%s,%s,%d",
					lm.hentNavn(),
					type,
					String.valueOf(lm.hentPris()),
					String.valueOf(lm.hentVirkestoff()),
					styrke);
			}
			output.println(legemiddel);
		}
	}

	private void legerTilFil(Liste<Lege> leger, PrintWriter output) {
		output.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
	}

	private void resepterTilFil(Liste<Resept> resepter, PrintWriter output) {
		output.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit]");
		String resept = null;

		for (Resept r : resepter) {
			if (r instanceof PResept) {
				resept = String.format("%d,%s,%d,p",
					r.hentLegemiddel().hentId(),
					r.hentLege().hentLegeNavn(),
					r.hentPasientId());
			} else {
				String type;
				if (r instanceof HvitResept) {
					type = "hvit";
				} else if (r instanceof MillitaerResept) {
					type = "millitaer";
				} else {
					type = "blaa";
				}
				resept = String.format("%d,%s,%d,%s,%d",
					r.hentLegemiddel().hentId(),
					r.hentLege().hentLegeNavn(),
					r.hentPasientId(),
					type,
					r.hentReit());
			}
		}
		output.println(resept);
	}
}