// --== CS400 File Header Information ==--
// Name: Aryaman Agarwal
// Email: agarwal59@wisc.edu
// Team: GB
// Role: Data Wrangler 2
// TA: Dan
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.Scanner;

/**
 * Class to store data in a list. Data collected and organized by David Barsness.
 *  
 * @author Aryaman Agarwal and David Barsness
 *
 */
public class ContactTest {
	public ContactInfo[] list;

	public ContactTest() {
	    String data = "Aditya Akella, 6088900122, akella@cs.wisc.edu\r\n" + 
	        "Aws Albarghouthi, 6082627946, aws@cs.wisc.edu\r\n" + 
	        "Andrea Arpaci-Dusseau, 6082656013, dusseau@cs.wisc.edu\r\n" + 
	        "Remzi Arpaci-Dusseau, 6082637764, remzi@cs.wisc.edu\r\n" + 
	        "Joseph Austerweil, 6082629932, austerweil@wisc.edu\r\n" + 
	        "Eric Bach, 6082627797, bach@cs.wisc.edu\r\n" + 
	        "Suman Banerjee, 6082627387, suman@cs.wisc.edu\r\n" + 
	        "Paul Barford, 6082626609, pb@cs.wisc.edu\r\n" + 
	        "Nigel Boston, 6082634753, boston@cs.wisc.edu\r\n" + 
	        "Jin-Yi Cai, 6082623158, jyc@cs.wisc.edu\r\n" + 
	        "Rahul Chatterjee, 6082625306, rahul.chatterjee@wisc.edu\r\n" + 
	        "Shuchi Chawla, 6088900027, shuchi@wisc.edu\r\n" + 
	        "Mark Craven, craven@biostat.wisc.edu\r\n" + 
	        "Loris D'Antoni, 6082630475, loris@cs.wisc.edu\r\n" + 
	        "Gary Dahl, 6082629415, dahl@cs.wisc.edu\r\n" + 
	        "Deb Deppeler, 6082659452, deppeler@cs.wisc.edu\r\n" + 
	        "Colin Dewey, 6082637610, cdewey@cs.wisc.edu\r\n" + 
	        "Ilias Diakonikolas, 6082624694, ilias@cs.wisc.edu\r\n" + 
	        "Anhai Doan, 6082629759, anhai@cs.wisc.edu\r\n" + 
	        "Kassem Fawaz, 6088900529, kfawaz@wisc.edu\r\n" + 
	        "Michael Ferris, 6082624281, ferris@cs.wisc.edu\r\n" + 
	        "Anthony Gitter, 6083164442, gitter@biostat.wisc.edu\r\n" + 
	        "Michael Gleicher, 6082632874, gleicher@cs.wisc.edu\r\n" + 
	        "Mohit Gupta, 6088900124, mohitg@cs.wisc.edu\r\n" + 
	        "Beck Hasti, 6082632622, hasti@cs.wisc.edu\r\n" + 
	        "Mark D. Hill, 6082622196, markhill@cs.wisc.edu\r\n" + 
	        "Justin Hsu, 6082521965, justhsu@cs.wisc.edu\r\n" + 
	        "Yu Hen Hu, 6082626724, yuhen.hu@wisc.edu\r\n" + 
	        "Somesh Jha, 6082629519, jha@cs.wisc.edu\r\n" + 
	        "Deborah Joseph, 6082628022, joseph@cs.wisc.edu\r\n" + 
	        "Paris Koutris, 6082631965, paris@cs.wisc.edu\r\n" + 
	        "Kangwook Lee, 6082654841, kangwook.lee@wisc.edu\r\n" + 
	        "Tracy Lewis-Williams, 6088904351, tlewiswilliams@cs.wisc.edu\r\n" + 
	        "Yingyu Liang, 6082627784, yliang@cs.wisc.edu\r\n" + 
	        "Mikko Lipasti, 6082652639, mikko@engr.wisc.edu\r\n" + 
	        "Miron Livny, 6082620856, miron@cs.wisc.edu\r\n" + 
	        "Dieter van Melkebeek, 6082624196, dieter@cs.wisc.edu\r\n" + 
	        "Barton Miller, 6082633378, bart@cs.wisc.edu\r\n" + 
	        "Bilge Mutlu, 6082626635, bilge@cs.wisc.edu\r\n" + 
	        "C. David Page, 6083164687, page@biostat.wisc.edu\r\n" + 
	        "Jignesh Patel, 6082647308, jignesh@cs.wisc.edu\r\n" + 
	        "Parameswaran Ramanathan, 6082630557, parmesh@cs.wisc.edu\r\n" + 
	        "Martina Rau, 6082620833, marau@cs.wisc.edu\r\n" + 
	        "Teodoros Rekatsinas, 6082628737, thodrek@cs.wisc.edu\r\n" + 
	        "Thomas Reps, 6082622091, reps@cs.wisc.edu\r\n" + 
	        "Amos Ron, 6082626621, amos@cs.wisc.edu\r\n" + 
	        "Joshua San Miguel, 6088902635, jsanmiguel@cs.wisc.edu\r\n" + 
	        "Karthikeyan Sankaralingam, 6088900121, karu@cs.wisc.edu\r\n" + 
	        "Vadim Shapiro, 6082623591, shapiro@cs.wisc.edu\r\n" + 
	        "Eftychios Sifakis, 6082625083, sifakis@cs.wisc.edu\r\n" + 
	        "Matthew Sinclair, 6082637463, sinclair@cs.wisc.edu\r\n" + 
	        "Vikas Singh, 6082628875, vsingh@cs.wisc.edu\r\n" + 
	        "James Skrentny, 6082620191, skrentny@cs.wisc.edu\r\n" + 
	        "Gurindar Sohi, 6082627985, sohi@cs.wisc.edu\r\n" + 
	        "Michael Swift, 6088900131, swift@cs.wisc.edu\r\n" + 
	        "Grace Wahba, 6082623620, wahba@cs.wisc.edu\r\n" + 
	        "Jim Williams, 6082626181, jimw@cs.wisc.edu\r\n" + 
	        "Stephen Wright, 6082624838, swright@cs.wisc.edu\r\n" + 
	        "Jerry Zhu, 6088900129, jerryzhu@cs.wisc.edu";
	    Scanner sc = new Scanner(data);
	    int i = 0;
	    list = new ContactInfo[104];
	    while (sc.hasNextLine()) {
	      String contact = sc.nextLine();
	      String[] info = contact.split(", ");
	      ContactInfo person;
	      if (info.length < 3)
	        person = new ContactInfo(info[0], info[1]);
	      else 
	        person = new ContactInfo(info[0], info[1], info[2]);
	      list[i] = person;
	      ++i;
	    }
	    sc.close();
	}
}
