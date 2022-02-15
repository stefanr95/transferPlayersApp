INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
                            
INSERT INTO klub(id, naziv, budzet) VALUE(1, 'Liverpool', 3000000);
INSERT INTO klub(id, naziv, budzet) VALUE(2, 'Bayern Munich', 12000000);
INSERT INTO klub(id, naziv, budzet) VALUE(3, 'Real Madrid', 15000000);
INSERT INTO klub(id, naziv, budzet) VALUE(4, 'Juventus', 5000000);

INSERT INTO igrac(id, ime_i_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) 
			VALUE(1, 'Cristiano Ronaldo', 'Napadac', 7, '1985-02-05', false, 4);
INSERT INTO igrac(id, ime_i_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) 
			VALUE(2, 'Mohamed Salah', 'Napadac', 11, '1992-06-15', true, 1);
INSERT INTO igrac(id, ime_i_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id) 
			VALUE(3, 'Luka Modric', 'Vezni', 10, '1985-09-09', true, 3);
INSERT INTO igrac(id, ime_i_prezime, pozicija, broj_dresa, datum_rodjenja, na_prodaju, klub_id)
			VALUE(4, 'Manuel Nojer', 'Golman', 1, '1986-03-27', false, 2);             