import java.io.*;
import java.net.*;
import java.sql.*;
public class ServeurFAC{
    public static void main(String[] args){
        try( Connection con = DriverManager.getConnection("jdbc:sqlite:src/db/GestionEtudiants.db")){
            if(con!=null){
                System.out.println("Connecte a la base de donnees avec succes");
            }
        }
        catch(SQLException ex1){
            System.out.println("Erreur dans la Base De Donnees "+ex1.getMessage());
        }
        int port = 6060;
        try {
            System.out.println("Bienvenue dans le serveur de l'INSAT");
            System.out.println("Connectez-vous en utilisant ces parametres : \nadresse = localhost || port = "+ port +" || mot de passe = insat2020 ");
            System.out.println("En attente du connexion ...");
            ServerSocket server = new ServerSocket(port);
            while (true) { // Permettre la connexion de plusieurs clients
                Socket connect = server.accept();
                Connection con = DriverManager.getConnection("jdbc:sqlite:src/db/GestionEtudiants.db");
                Statement stm = con.createStatement();
                System.out.println("[+] Connexion avec Succes , Port de client : "+ connect.getPort());
                DataInputStream in = new DataInputStream(connect.getInputStream());
                DataOutputStream out = new DataOutputStream(connect.getOutputStream());
                while (true) { // Permettre au client de faire plusieurs operations
                    int operation = in.readInt();
                    switch (operation) {
                        case 1: // Operation d'ajout d'un etudiant
                            String ninscri = in.readUTF();
                            String nom = in.readUTF();
                            String prenom = in.readUTF();
                            String email = in.readUTF();
                            String filiere = in.readUTF();
                            String classe = in.readUTF();
                            String insertion = "INSERT INTO Etudiant(NumInscription,Nom,Prenom,Email,Classe,FiliereEtud)\n"
                            +"Values('"+ninscri+"','"+nom+"','"+prenom+"','"+email+"','"+classe+"','"+filiere+"');";
                            String test = "SELECT * FROM Etudiant"+" WHERE NumInscription = '"+ninscri+"';";
                            ResultSet rs = stm.executeQuery(test);
                            if(!rs.next()){ // On verifie s'il existe un etudiant avec le meme num_ins
                                stm.executeUpdate(insertion);
                                System.out.println("[+] "+nom+" est ajouté avec succes");
                                out.writeInt(1);
                            }
                            else{
                                out.writeInt(0);
                            }
                            break;
                        case 2 : // Operation de supression d'un etudiant
                        	String NumInscri = in.readUTF();
                        	System.out.println(NumInscri);
                        	String Suppression = "DELETE FROM Etudiant WHERE Numinscription = '"+NumInscri+"';";
                            String test1 = "SELECT * FROM Etudiant"+" WHERE NumInscription = '"+NumInscri+"';";
                            ResultSet rs1 = stm.executeQuery(test1);
                            if(rs1.next()){ 
                                stm.executeUpdate(Suppression);
                                System.out.println("[+] "+"Suppression avec succées");
                                out.writeInt(1);
                            }
                            else {
                            	out.writeInt(0);
                            }
                             break;
                        case 3: // Operation d'affichage d'un etudiant
                            String NumInscri2 = in.readUTF();
                            String affichage = "SELECT Nom,Prenom,Email,Classe,FiliereEtud FROM Etudiant WHERE NumInscription = '"+NumInscri2+"';";
                            ResultSet rs2 = stm.executeQuery(affichage);
                            if(rs2.next()){
                                out.writeInt(1);
                                out.writeUTF(rs2.getString(1));
                                out.writeUTF(rs2.getString(2));
                                out.writeUTF(rs2.getString(3));
                                out.writeUTF(rs2.getString(4));
                                out.writeUTF(rs2.getString(5));
                            }
                            else {
                                System.out.println("[+] "+"Affichage avec succées");
                                out.writeInt(0);
                            }
                            break;
                        case 4: // Operation de modification des infos. d'un etudiant
                            String ninscri4 = in.readUTF();
                            String nom4 = in.readUTF();
                            String prenom4 = in.readUTF();
                            String email4 = in.readUTF();
                            String filliere4 = in.readUTF();
                            String classe4 = in.readUTF();
                            String modification = "UPDATE Etudiant SET Nom = '"+ nom4 + "', Prenom = '" + prenom4 +"', Email = '" + email4 +"', FiliereEtud = '"+ filliere4 + "', Classe = '"+ classe4 +"' WHERE NumInscription = '"+ninscri4+"';";
                            String test2 = "SELECT * FROM Etudiant"+" WHERE NumInscription = '"+ninscri4+"';";
                            ResultSet rs3 = stm.executeQuery(test2);
                            if (rs3.next()) {
                                stm.executeUpdate(modification);
                                System.out.println("[+] "+"Modification avec Succees");
                                out.writeInt(1);
                            }else {
                                out.writeInt(0);
                            }
                            break;
                    }
                }
            }

        }
        catch(IOException ex1){
            System.out.println("Le port est deja utilise !!");
            System.out.println(ex1.getMessage());
        }
        catch(SQLException ex2){
            System.out.println("Erreur dans la base de donnees");
            System.out.println(ex2.getMessage());
        }
    }
}
