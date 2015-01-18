import java.sql.*;
import java.util.Properties;
import java.util.Scanner; 

public class Project {

	final static String url= "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	//mega join for search final static String 
	
    public static void main(String args[]) throws SQLException {
    	Login(); 
        System.out.println("THANK YOU FOR USING THE MOVIE DATABASE!");   
    }
    
    public static void Login() throws SQLException { 
    	
    	Properties props = new Properties();	
 	    props.setProperty("user", "smahmoo4");
 	    props.setProperty("password", "supralover11");
 	  
 	    Connection conn = DriverManager.getConnection(url,props);
 	    System.out.println("*********");
	    System.out.println("* LOGIN *");
	    System.out.println("*********");
	    System.out.println();
	    System.out.println();

    	System.out.print("Please type 'member' or 'staff' ->  ");
	    Scanner scan= new Scanner(System.in);
	    String response = scan.next().toLowerCase(); 
	    
	    if(response.equals("member")){
	    	System.out.println("----------------");
	    	System.out.println("|    MEMBER    |");
			System.out.println("----------------");
	    	System.out.print("Please enter your memberID ->  ");
		    int user = scan.nextInt();
		    System.out.print("Please enter your password: ");
		    String pass = scan.next();
		    String compare = "SELECT user_id, p_word FROM Members WHERE user_id="+ user +" AND p_word= '"+pass+"'";

		    PreparedStatement preStatement = conn.prepareStatement(compare);
		    ResultSet rSet = preStatement.executeQuery();
		    
		    while(rSet.next()){
			    int user_id = rSet.getInt("user_id");
			    String p_word = rSet.getString("p_word"); 
			    if (user_id == user && p_word.equals(pass))
			    	LoginSuccess();
			    
			    System.out.println();
			    memberOptions(); 
		    }  
	    }
	    else if(response.equals("staff")){
		    System.out.println("----------------");
		    System.out.println("|     STAFF    |");
		    System.out.println("----------------");
		    
		    

	    	System.out.print("Please enter your staffID: ");
	   	    int user = scan.nextInt();
	   	    System.out.print("Please enter your password: ");
	   	    String pass = scan.next();
	   	    
		    String compare = "SELECT user_id, p_word FROM Staff WHERE user_id= " + user +" AND p_word= '"+pass+"'";
			
		    PreparedStatement preStatement = conn.prepareStatement(compare);
		
		    ResultSet rSet = preStatement.executeQuery();
		    
		    while(rSet.next()){
			    int user_id = rSet.getInt("user_id");
			    String p_word = rSet.getString("p_word");   
			    if (user_id == user && p_word.equals(pass)){
			    	LoginSuccess();
			    	System.out.println();
			    	staffOptions();
			    	conn.close();
			    }
		    }
		    	LoginFailure(); 
	    }  
    }
    
    public static void memberOptions() throws SQLException{
    	System.out.println();
    	System.out.println("Welcome to the Movie Website! Here you can: ");
    	
    	System.out.println("----------------");
    	System.out.println("|    SEARCH    |   ------> Search Movies, Actors, Directors, and Writers");
		System.out.println("----------------");
		
		System.out.println("----------------");
    	System.out.println("|  BUY TICKETS |   ------> Buy Tickets for Upcoming Showings");
		System.out.println("----------------");
		
		System.out.println("-----------------");
    	System.out.println("|CHECK AVAILABLE|  ------> Check for Availability of Shows Based on Movie Time");
		System.out.println("-----------------");
		
	    System.out.println();
	    System.out.println();
	    
	    System.out.print("Please type 'search', 'buy', or 'check' to continue: ");

		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		
		if(response.equals("search"))
			search();
				
		else if(response.equals("buy"))
			buy();
				
		else if(response.equals("check")) 
			check();
		
		else {
			System.out.println("Sorry, " + response +" is not an option. Please try again.");
			memberOptions(); 	
		}	
    }
      
    public static void staffOptions() throws SQLException{
    	System.out.println();
    	System.out.println("	Staff Options: ");
    	
    	System.out.println("----------------");
    	System.out.println("|    SEARCH    |   ------> Search Movies, Actors, Directors, and Writers");
		System.out.println("----------------");
		
		System.out.println("----------------");
    	System.out.println("|   TOGGLE DB  |   ------> Add, Modify, Delete Movie, Actor, Director, Writer Information");
		System.out.println("----------------");
		
		System.out.println("-----------------");
    	System.out.println("|CHECK AVAILABLE|  ------> Check for Availability of Shows Based on Movie Time");
		System.out.println("-----------------");
		
	    System.out.println();
	    System.out.println();
	    
	    System.out.print("Please type 'search', 'toggle', or 'check' to continue -> ");

		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		
		if(response.equals("search"))
			search();
				
		else if(response.equals("toggle"))
			toggle();
				
		else if(response.equals("check")) 
			check();		
		else{
			System.out.println();
			System.out.println("Sorry, " + response +" is not an option. Please try again.");
			staffOptions();
		}		
	}
    
    public static void LoginSuccess() {
   	    System.out.println();
    	System.out.println("Access granted.");
    }

    public static void LoginFailure() throws SQLException {
   	    System.out.println();
    	System.out.println("Access denied. Please try again");
    	Login();
    }
    
    public static void search() throws SQLException {
    	String [] table = {"MOVIE", "MOVIE", "MOVIE", "MOVIE", "MOVIE", "ACTOR", "DIRECTOR", "WRITER"};
    	String [] attributes = {"TITLE", "GENRE", "RATING", "RELEASE_YEAR", "LENGTH", "PERSON_NAME", "PERSON_NAME", "PERSON_NAME"};
    	
    	System.out.println();
    	System.out.println();
    	
    	System.out.println("	************");
    	System.out.println("	|| SEARCH ||");
    	System.out.println("	************");
    	
    	String input;
    	System.out.println();
    	System.out.println();


    	System.out.println("Please Type Information in this Order: (separate every value with comma, write 'null' for undesired categories of search)");
    	System.out.println();
    	System.out.println("============================================================================================================");
    	System.out.println("|| MOVIE TITLE || GENRE || RATING || RELEASE YEAR || LENGTH || ACTOR NAME || DIRECTOR NAME || WRITER NAME ||");
    	System.out.println("============================================================================================================");
    	System.out.println();
    	System.out.print("Enter Fields Here => ");
    	Scanner scan = new Scanner(System.in);
    	input = scan.nextLine();
    	input = input.toUpperCase();
    	
    	String [] prep = new String[8];

		prep = input.split(",");
    	
		//ELIMINATING NULL VALUES
		for(int i = 0; i<prep.length; i++){
			if(prep[i].contains("NULL")){
				prep[i] = "NULL";
				table[i] = "NULL";
				attributes[i] = "NULL";
			}
		}
				
		//SELECT CODE
		String select = "SELECT * FROM MOVIE"; 
		String query = "";
		query+= select;

	
		//WHERE CODE
		
		if(!table[5].equals("NULL") && table[6].equals("NULL") && table[7].equals("NULL")) {
			query += " NATURAL JOIN ACTOR WHERE PERSON_NAME = " +"'"+prep[5]+"'";
			displayQuery(query);
		}
		else if(table[5].equals("NULL") && !table[6].equals("NULL") && table[7].equals("NULL")) {
			query += " NATURAL JOIN DIRECTOR WHERE PERSON_NAME = " +"'"+prep[6]+"'";
			displayQuery(query);
		}
		
		else if (table[5].equals("NULL") && table[6].equals("NULL") &&!table[7].equals("NULL")){
			query += " NATURAL JOIN WRITER WHERE PERSON_NAME = " +"'"+prep[7]+"'";
			displayQuery(query);
		}
		
		else{
			String where = " WHERE ";
			query+= where; 
			
			for(int k = 0; k<prep.length; k++){
				if(!attributes[k].contains("NULL")){
					query+= attributes[k] + " = " + "'"+prep[k]+"'"+ " AND ";
				}
			}
			int max = query.length()-5;
			query = query.substring(0,max);
			displayQuery(query);
		}
    }//end of search()
    
    public static void displayQuery(String query) throws SQLException{
    	Properties props = new Properties();	
 	    props.setProperty("user", "smahmoo4");
 	    props.setProperty("password", "supralover11");
 	    Connection conn = DriverManager.getConnection(url,props);
    	PreparedStatement preStatement = conn.prepareStatement(query);
	    ResultSet rSet = preStatement.executeQuery();	
	    System.out.println();
    	System.out.print("Title"+"\t\t\t");
    	System.out.print("Genre"+"\t\t");
    	System.out.print("Release"+"\t\t");
    	System.out.print("Rating"+"\t\t");
    	System.out.println("Length"+"\t");
	    
    	while(rSet.next()){

	    	String title = rSet.getString("title");
	    	String genre = rSet.getString("genre");
	    	int release_year = rSet.getInt("release_year");
	    	String rating = rSet.getString("rating");
	    	int length = rSet.getInt("length");
	    	
	    	System.out.print(title+"\t\t");
	    	System.out.print(genre+"\t\t");
	    	System.out.print(release_year+"\t\t");
	    	System.out.print(rating+"\t\t");
	    	System.out.println(length+"\t");
	    }
	    
    		System.out.println();
        	System.out.println();
	    	System.out.print("Press 'c' to search again, for members, type 'm', for staff, type 's' to go back to options screen ->  ");
	    	Scanner scan = new Scanner(System.in);
	    	String answer = scan.next();
	    	
	    	if(answer.equals("c")){
	    		search();
	    	}
	    	
	    	else if(answer.equals("m")){
	    		memberOptions(); 
	    	}
	    	
	    	else if(answer.equals("s")){
	    		staffOptions(); 
	    	}
    }

    public static void toggle() throws SQLException{
    	System.out.println("	************");
    	System.out.println("	|| TOGGLE ||");
    	System.out.println("	************");
    	
    	System.out.println("PRESS");
    	System.out.println("'a' to ADD");
    	System.out.println("'m' to MODIFY");
    	System.out.println("'d' to DELETE");

    	System.out.print("->  ");
    	Scanner scan = new Scanner(System.in);
    	String action = scan.next();
    	
    	if(action.equals("a"))
    		add();
    	if(action.equals("m"))
    		modify();
    	if(action.equals("d"))
    		delete();
    }
    
    public static void add() throws SQLException{
    	
    	Properties props = new Properties();
        props.setProperty("user", "smahmoo4");
        props.setProperty("password", "supralover11");
        Connection conn = DriverManager.getConnection(url,props);
    	
        System.out.print("What table would you like to ADD to?  ");
    	Scanner scan = new Scanner(System.in);
    	String insert = "INSERT INTO ";
    	String table = scan.next();
    	
    	int person_id; 
    	String person_name;
    	String dob; 
    	int movie_id;
    	
    	PreparedStatement pstmt = null;
    	String title;
    	String genre;
    	int release_year;
    	String rating;
    	int length;
    	
    	
    	if(table.equals("Actor")){
    		System.out.println("1. Enter Actor ID");
    		System.out.println("2. Enter Actor Name");
    		System.out.println("3. Enter Actor Date of Birth");
    		System.out.println("4. Enter Actor Movie ID");
    		
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		movie_id = scan.nextInt();
    		insert+= table + "(person_id, person_name, date_of_birth, movie_id) values (?,?,?,?)";
    		
    		  pstmt = conn.prepareStatement(insert); 		// create a statement
    	      pstmt.setInt(1, person_id); 					// set input parameter 1
    	      pstmt.setString(2, person_name); 				// set input parameter 2
    	      pstmt.setString(3, dob); 						// set input parameter 3
    	      pstmt.setInt(4, movie_id); 					// set input parameter 4

    	      pstmt.executeUpdate(); 						// execute insert statement
      		  System.out.println();
    	      System.out.println("Actor Added.");
      		  System.out.println("Would you like to make another change to the Database? Press 'y' for yes or 'n' to go back to Staff Options ->  ");

    	      String decision = scan.next();
    	      if(decision.equals("y"))
    	    	  toggle();
    	      if(decision.equals("n"))
    	    	  staffOptions();
    	}
    	
    	if(table.equals("Director")){
    		System.out.println("1. Enter Director ID");
    		System.out.println("2. Enter Director Name");
    		System.out.println("3. Enter Director Date of Birth");
    		System.out.println("4. Enter Director Movie ID");
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		movie_id = scan.nextInt();
    		insert+= table + "(person_id, person_name, date_of_birth, movie_id) values (?,?,?,?)";
    		
    		  pstmt = conn.prepareStatement(insert); 				 // create a statement
    	      pstmt.setInt(1, person_id); 			 				 // set input parameter 1
    	      pstmt.setString(2, person_name.toUpperCase()); 		 // set input parameter 2
    	      pstmt.setString(3, dob); 				 				 // set input parameter 3
    	      pstmt.setInt(4, movie_id); 			 				 // set input parameter 4

    	      pstmt.executeUpdate(); 				 				 // execute insert statement
      		  System.out.println();
      	      System.out.println("Director Added.");
      		  System.out.println();
      		  System.out.println("Would you like to make another change to the Database? Press 'y' for yes or 'n' to go back to Staff Options ->  ");

    	      String decision = scan.next();
    	      if(decision.equals("y"))
    	    	  toggle();
    	      if(decision.equals("n"))
    	    	  staffOptions();
    	}
    	
    	if(table.equals("Writer")){
    		System.out.println("1. Enter Writer ID");
    		System.out.println("2. Enter Writer Name");
    		System.out.println("3. Enter Writer Date of Birth");
    		System.out.println("4. Enter Writer Movie ID");
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		movie_id = scan.nextInt();
    		insert+= table + "(person_id, person_name, date_of_birth, movie_id) values (?,?,?,?)";
    		
    		pstmt = conn.prepareStatement(insert); 			   // create a statement
    	    pstmt.setInt(1, person_id); 		   			   // set input parameter 1
    	    pstmt.setString(2, person_name.toUpperCase()); 	   // set input parameter 2
    	    pstmt.setString(3, dob); 			   			   // set input parameter 3
    	    pstmt.setInt(4, movie_id); 			  			   // set input parameter 4

    	    pstmt.executeUpdate(); 			   			   	   // execute insert statement
      		System.out.println();
    	    System.out.println("Writer Added.");
    	    System.out.println();
    		System.out.println("Would you like to make another change to the Database? Press 'y' for yes or 'n' to go back to Staff Options ->  ");

  	      	String decision = scan.next();
  	      	
  	      	if(decision.equals("y"))
  	    	  toggle();
  	      	if(decision.equals("n"))
  	    	  staffOptions();
   
    	}
    	
    	if(table.equals("Movie")){
    		System.out.println("1. Enter Movie ID");
    		System.out.println("2. Enter Movie Title");
    		System.out.println("3. Enter Movie Genre");
    		System.out.println("4. Enter Movie Release Year");
    		System.out.println("5. Enter Movie Rating");
    		System.out.println("6. Enter Movie Length");
    		
    		movie_id = scan.nextInt();
    		title = scan.next();
        	genre = scan.next();
        	release_year = scan.nextInt();
        	rating = scan.next();
        	length = scan.nextInt();
        	
        	insert+= table + " (movie_id, title, genre, release_year, rating, length) values (?,?,?,?,?,?)";
    		
        	pstmt = conn.prepareStatement(insert); 			// create a statement
        	
    	    pstmt.setInt(1, movie_id); 		   			    // set input parameter 1
    	    pstmt.setString(2, title.toUpperCase()); 	    // set input parameter 2
    	    pstmt.setString(3, genre.toUpperCase()); 		// set input parameter 3
    	    pstmt.setInt(4, release_year); 			  	    // set input parameter 4
    	    pstmt.setString(5, rating.toUpperCase()); 		// set input parameter 5
    	    pstmt.setInt(6, length); 						// set input parameter 6	
    	    
    	    pstmt.executeUpdate(); 			   			   // execute insert statement
    		System.out.println();
    	    System.out.println("Movie Added.");
    	    System.out.println();
    		System.out.println("Would you like to make another change to the Database? Press 'y' for yes or 'n' to go back to Staff Options ->  ");

  	      	String decision = scan.next();
  	      	
  	      	if(decision.equals("y"))
  	    	  toggle();
  	      	if(decision.equals("n"))
  	    	  staffOptions();
    	}
    	conn.close();
    } //end of add()	

    public static void modify() throws SQLException{
    	Properties props = new Properties();
        props.setProperty("user", "smahmoo4");
        props.setProperty("password", "supralover11");
        Connection conn = DriverManager.getConnection(url,props);
        PreparedStatement pstmt = null;
        System.out.println("What table would you like to UPDATE?");
    	System.out.print("->  ");
    	Scanner scan = new Scanner(System.in);
    	String table = scan.next();
    	int person_id; 
    	int person_id2;
    	String person_name;
    	String dob; 
    	int movie_id;
    	int movie_id2;
    	String title;
    	String genre;
    	int release_year;
    	String rating;
    	int length;
    	System.out.print("->  ");
    	String update ="UPDATE ";
    	
    	if(table.equals("Actor")){
    		System.out.println("1. Enter NEW Actor ID");
    		System.out.println("2. Enter NEW Actor Name");
    		System.out.println("3. Enter NEW Actor Date of Birth");
    		System.out.println("4. Enter Actor ID");
    		update= update+table+ " SET person_id = ?, person_name = ?, date_of_birth = ? WHERE person_id = ?";
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		person_id2 = scan.nextInt();
        	pstmt = conn.prepareStatement(update); 				// create a statement
        	pstmt.setInt(1,person_id);
        	pstmt.setString(2,person_name);
        	pstmt.setString(3,dob);
        	pstmt.setInt(4,person_id2);
        	pstmt.executeUpdate(); 			   			   // execute update statement
    		System.out.println();
    	    System.out.println("Actor Updated.");
    	    toggle();
    	}
    	
    	if(table.equals("Director")){
    		System.out.println("1. Enter NEW Director ID");
    		System.out.println("2. Enter NEW Director Name");
    		System.out.println("3. Enter NEW Director Date of Birth");
    		System.out.println("4. Enter Director ID");
    		update+= table+ " SET person_id = ?, person_name = ?, date_of_birth = ? WHERE person_id = ?";
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		person_id2 = scan.nextInt();
        	pstmt = conn.prepareStatement(update); 				// create a statement
        	pstmt.setInt(1,person_id);
        	pstmt.setString(2,person_name);
        	pstmt.setString(3,dob);
        	pstmt.setInt(4,person_id2);
    	    pstmt.executeUpdate(); 			   			   // execute update statement
    		System.out.println();
    	    System.out.println("Director Updated.");
    	    toggle();
    	}
    	
    	if(table.equals("Writer")){
    		System.out.println("1. Enter NEW Writer ID");
    		System.out.println("2. Enter NEW Writer Name");
    		System.out.println("3. Enter NEW Writer Date of Birth");
    		System.out.println("4. Enter Writer Movie ID");
    		update+= table+ " SET person_id = ?, person_name = ?, date_of_birth = ? WHERE person_id = ?";
    		
    		person_id = scan.nextInt();
    		person_name = scan.next().toUpperCase();
    		dob = scan.next();
    		person_id2 = scan.nextInt();
        	
    		pstmt = conn.prepareStatement(update); 				// create a statement
        	pstmt.setInt(1,person_id);
        	pstmt.setString(2,person_name);
        	pstmt.setString(3,dob);
        	pstmt.setInt(4,person_id2);
    	    pstmt.executeUpdate(); 			   			   // execute update statement
    		System.out.println();
    	    System.out.println("Writer Updated.");
    	    toggle();
    	}
    	
    	if(table.equals("Movie")){
    		System.out.println("1. Enter NEW Movie ID");
    		System.out.println("2. Enter NEW Movie Title");
    		System.out.println("3. Enter NEW Movie Genre");
    		System.out.println("4. Enter NEW Movie Release Year");
    		System.out.println("5. Enter NEW Movie Rating");
    		System.out.println("6. Enter NEW Movie Length");
    		System.out.println("7. Enter Current Movie ID");

    		update+= table+ " SET movie_id = ?, title = ?, genre = ?, release_year = ?, rating = ?, length = ? WHERE movie_id = ?";
    		
    		//adding in the update for movie
    		movie_id = scan.nextInt();
    		title = scan.next().toUpperCase();
    		genre = scan.next().toUpperCase();
    		release_year = scan.nextInt();
    		rating = scan.next().toUpperCase();
    		length = scan.nextInt();
    		movie_id2 = scan.nextInt();
    		pstmt = conn.prepareStatement(update);
    		pstmt.setInt(1,movie_id);
    		pstmt.setString(2, title);
    		pstmt.setString(3, genre);
    		pstmt.setInt(4, release_year);
    		pstmt.setString(5, rating);
    		pstmt.setInt(6, length);
    		pstmt.setInt(7, movie_id2);

    		pstmt.executeUpdate();
    		System.out.println();
    		System.out.println("Movie Updated.");
    		toggle();
    	}
    	conn.close();
    } //end modify

    public static void delete() throws SQLException {
    	Properties props = new Properties();
        props.setProperty("user", "smahmoo4");
        props.setProperty("password", "supralover11");
        Connection conn = DriverManager.getConnection(url,props);
        PreparedStatement pstmt = null;
        System.out.println("What table would you like to DELETE from?");
    	System.out.print("->  ");
    	Scanner scan = new Scanner(System.in);
    	String table = scan.next();
    	String delete = "DELETE FROM ";
    	int person_id;
    	int movie_id;
    	
    	if(table.equals("Actor")){
    		System.out.print("Enter Person ID to be deleted -> ");
    		delete = delete + table + " WHERE person_id = ?";
    		person_id = scan.nextInt();
    		pstmt = conn.prepareStatement(delete);
    		
    		pstmt.setInt(1, person_id);
    		
    		pstmt.executeUpdate();
    		System.out.println();
    		System.out.println("Actor Deleted.");
    		toggle();
    		
    	}
    	
    	if(table.equals("Director")){
    		System.out.print("Enter Person ID to be deleted -> ");
    		delete = delete + table + " WHERE person_id = ?";
    		person_id = scan.nextInt();
    		pstmt = conn.prepareStatement(delete);
    		
    		pstmt.setInt(1, person_id);
    		
    		pstmt.executeUpdate();
    		System.out.println();
    		System.out.println("Director Deleted.");
    		toggle();
    	}
    	
    	if(table.equals("Writer")){
    		System.out.print("Enter Person ID to be deleted -> ");
    		delete = delete + table + " WHERE person_id = ?";
    		person_id = scan.nextInt();
    		pstmt = conn.prepareStatement(delete);
    		
    		pstmt.setInt(1, person_id);
    		
    		pstmt.executeUpdate();
    		System.out.println();
    		System.out.println("Writer Deleted.");
    		toggle();
    	}
    	
    	if(table.equals("Movie")){
    		System.out.print("Enter Movie ID to be deleted -> ");

    		delete = delete + table + " WHERE movie_id = ?";
    		movie_id = scan.nextInt();
    		pstmt = conn.prepareStatement(delete);
    		
    		pstmt.setInt(1, movie_id);
    		
    		pstmt.executeUpdate();
    		System.out.println();
    		System.out.println("Movie Deleted.");
    		toggle();
    	}
    	
    	conn.close();
    }//end of delete()
    
    public static void buy() throws SQLException{
    	Properties props = new Properties();
        props.setProperty("user", "smahmoo4");
        props.setProperty("password", "supralover11");
        Connection conn = DriverManager.getConnection(url,props);
    	System.out.println();
    	System.out.println();
    	int tickets_Available = 0;
    	System.out.println("	*****************");
    	System.out.println("	|| BUY TICKETS ||");
    	System.out.println("	*****************");
    	
    	Scanner scan = new Scanner(System.in);
    	System.out.print("What Show ID would you like to purchase tickets for?  ");
    	int show_id = scan.nextInt();
    	PreparedStatement pstmt = null;
    	String showing = "SELECT tickets_Available FROM SHOWING WHERE show_id = ?";
    	pstmt = conn.prepareStatement(showing);
    	pstmt.setInt(1, show_id);
    	
	    ResultSet rSet = pstmt.executeQuery();	
	    
	    while(rSet.next()){
	    	tickets_Available = rSet.getInt("tickets_Available");
	    	System.out.println("There are "+ tickets_Available + " tickets left for this showing.");
	    }
	    
	    System.out.print("How many tickets would you like to purchase? ->  ");
	    
	    int amount = scan.nextInt();
	    
	    if(amount <= tickets_Available){
	    	String update = "UPDATE SHOWING SET tickets_Available = tickets_Available - "+amount+" WHERE show_id = "+"'"+show_id+"'";
	    	pstmt= conn.prepareStatement(update);
	    	pstmt.executeUpdate();
	    	System.out.println("PURCHASE SUCCESSFUL");
	    	System.out.println();	    	
	    }
	    
	    else{
	    	System.out.println("Sorry, there are not enough tickets available! Please try again.");
	    	buy();
	    }
	    
    	String insert = "INSERT INTO TICKETS(ticket_id,user_id,show_id,tickets_Booked,price) values"+"('"+tickets_Available+0+"','335',"+"'"+show_id+"',"+"'"+amount+"',"+"'5')";
    	pstmt = conn.prepareStatement(insert);	
    	pstmt.executeUpdate();
    	
    	System.out.print("Would you like to make another purchase? Type 'y' for yes and 'n' for no to go back to 'Member Options' ->  ");
    	String response = scan.next();
    	if(response.equals("y")){
    		buy();
    	}
    	else if(response.equals("n")){
    		conn.close();
    		memberOptions();
    	}
    		
    	else{
    		conn.close();
    		System.exit(0);
    	}	
    }
  
    public static void check() throws SQLException{
    	
    	System.out.println();
    	System.out.println();
    	
    	System.out.println("	************");
    	System.out.println("	||  CHECK ||");
    	System.out.println("	************");
    	
    	System.out.println();
    	System.out.println();
    	
    	Properties props = new Properties();
        props.setProperty("user", "smahmoo4");
        props.setProperty("password", "supralover11");
        Connection conn = DriverManager.getConnection(url,props);
        
    	Scanner scan = new Scanner(System.in);
    	int movie_id;
    	int tickets =0;
    	String title = "";
    	String showtime= ""; 
    	System.out.print("Please Enter a Movie Id and Showtime ->  ");
    	PreparedStatement pstmt = null;
    	movie_id = scan.nextInt();
    	showtime = scan.next();
    	String check = "SELECT tickets_Available FROM SHOWING WHERE movie_id = ? AND showtime = ?";
    	pstmt = conn.prepareStatement(check);
    	pstmt.setInt(1, movie_id);
    	pstmt.setString(2, showtime);
    	
    	ResultSet rSet = pstmt.executeQuery();
    	
    	while(rSet.next()){
    		tickets = rSet.getInt("tickets_Available");
    	}
    	
    	System.out.print("Enter Movie Title ->  ");
    	title = scan.next();
    	System.out.println("Current showing of "+ title +" has "+tickets+ " tickets available");
    	
    	System.out.println();
    	System.out.print("Press 'b' to go back ->  ");
    	String response = scan.next();
    	if (response.equals("b")){
    		Login();
    	}
    	
    	else
    		System.exit(0);
    } //end of check()
    
} //end of class Project