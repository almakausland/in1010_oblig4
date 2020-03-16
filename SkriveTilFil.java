import java.io.File;
import java.io.FileNotFoundException;
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
		FileWriter fw = new FileWriter(String.format("%s.txt", filnavn), false);
		PrintWriter output = new PrintWriter(fw);

		pasienterTilFil(pasienter, output);
		legemidlerTilFil(legemidler, output);
		legerTilFil(leger, output);
		resepterTilFil(resepter, output);

		output.close();
		fw.close();
	}

	private void pasienterTilFil(Liste<Pasient> pasienter, PrintWriter output) {
		output.print("# Pasienter (navn, fnr)");
		String pasient = null;

		for (Pasient p : pasienter) {
			pasient = String.format("\n%s,%s", p.hentNavn(), p.hentFnr());
			output.print(pasient);
		}
	}

	private void legemidlerTilFil(Liste<Legemiddel> legemidler, PrintWriter output) {
		output.print("\n# Legemidler (navn,type,pris,virkestoff,[styrke]");
		String legemiddel = null;

		for (Legemiddel lm : legemidler) {
			if (lm instanceof Vanlig) {
				legemiddel = String.format("\n%s,vanlig,%s,%s",
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
				legemiddel = String.format("\n%s,%s,%s,%s,%d",
					lm.hentNavn(),
					type,
					String.valueOf(lm.hentPris()),
					String.valueOf(lm.hentVirkestoff()),
					styrke);
			}
			output.print(legemiddel);
		}
	}

	private void legerTilFil(Liste<Lege> leger, PrintWriter output) {
		output.print("\n# Leger (navn,kontrollid / 0 hvis vanlig lege)");
		String lege = null;
		int kontrollId = 0;

		for (Lege l : leger) {
			if (l instanceof Spesialist) {
				kontrollId = ((Spesialist) l).hentKontrollID();
			} else {
				kontrollId = 0;
			}
			lege = String.format("\n%s,%d", l.hentLegeNavn(), kontrollId);
			output.print(lege);
		}
	}

	private void resepterTilFil(Liste<Resept> resepter, PrintWriter output) {
		output.print("\n# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
		String resept = null;

		// PResept og MillitaerResept arver fra HvitResept og maa dermed sjekkes foer HvitResept
		for (Resept r : resepter) {
			if (r instanceof PResept) {
				resept = String.format("\n%d,%s,%d,p",
					r.hentLegemiddel().hentId(),
					r.hentLege().hentLegeNavn(),
					r.hentPasientId());
			} else {
				String type;
				if (r instanceof MillitaerResept) {
					type = "millitaer";
				} else if (r instanceof HvitResept) {
					type = "hvit";
				} else {
					type = "blaa";
				}
				resept = String.format("\n%d,%s,%d,%s,%d",
					r.hentLegemiddel().hentId(),
					r.hentLege().hentLegeNavn(),
					r.hentPasientId(),
					type,
					r.hentReit());
			}
			output.print(resept);
		}
	}
}