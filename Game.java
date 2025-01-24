import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    for(int i = 0; i<WIDTH+1; i++){ //horizontal line
      Text.go(0,i);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.println(".");

      Text.go(30, i);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");
      
      Text.go(7, i);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");

      Text.go(21, i);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");

      Text.go(27, i);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");
    }
    for (int i = 0; i < HEIGHT+1;i++){ //vertical lines
      Text.go(i, 0);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");
      
      Text.go(i, 80);
      Text.colorize(" ", BORDER_BACKGROUND);
      System.out.print(".");
    }
  Text.reset();
  Text.showCursor();
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Text.go(startRow, startCol);
    System.out.print(s);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    int currentRow = row;
    int i = 0;
    for (int r = 0; r < height; r++) {
      Text.go(currentRow + r, col);
      for (int j = 0; j < width; j++){
        System.out.print(" ");
      }
    }
    while(i<text.length() && currentRow < row + height){
      drawText(text.substring(i, Math.min(i+width, text.length())), currentRow, col);
      i += width;
      currentRow++;
    }
    while(currentRow < row+height){
      Text.go(currentRow++, col);
      for(int j=0; j < width; j++){
        System.out.print(" ");
      }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int random = (int)(Math.random()*3);
      if (random == 0) {
        return new Chef("Chef", 20 + (int)(Math.random() * 6));
      }
      else if (random == 1) {
        return new Dishwasher("Dishwasher", 30 + (int)(Math.random() * 6));
      }
      return new Butcher("Butcher", 25 + (int)(Math.random() * 6));
    }

    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow){

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      int startCol = 10;
      int startColEnemies = startCol + 40;
      for (int i = 0; i < party.size(); i++) {
        Adventurer adventurer = party.get(i);
        String name = adventurer.getName();
        String hp = colorByPercent(adventurer.getHP(),adventurer.getmaxHP());
        String special = "Special: " + adventurer.getSpecial();
        
        int colset = i*26;
        drawText(name, startRow, startCol + colset);
        drawText(hp, startRow+1, startCol + colset);
        drawText(special, startRow+2, startCol + colset);
      }
      
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    }


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    double percent = (double) hp / maxHP * 100;
    // under 25% : red
    if(percent < 25){
      return Text.colorize(output, Text.RED);
    }
    // under 75% : yellow
    else if(percent < 75){
      return Text.colorize(output, Text.YELLOW);
    }
    // otherwise : white
    else{
      return Text.colorize(output, Text.WHITE);
    }
  }


  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){

    drawBackground();
    //draw player party
    drawParty(party, 3);
    //draw enemy party
    drawParty(enemies,23);
  }

  public static String userInput(Scanner in){
    clearLine(29,3,75);
    //Move cursor to prompt location
    Text.go(29,3);
    //show cursor
    String input = in.nextLine();

    //clear the text that was written
    return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void clearLine(int row, int col, int width) {
    Text.go(row, col);
    for (int i = 0; i < width; i++) {
        System.out.print(" ");
    }
    Text.go(row,col);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.

    ArrayList<Adventurer> enemies = new ArrayList<>();

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    int randnumberEnemies = 1 + (int)(Math.random() * 2);
    if(randnumberEnemies == 1){
      enemies.add(new Boss());
    }
    else{
      for (int i = 0; i < randnumberEnemies;i++){
        enemies.add(createRandomAdventurer());
      }
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    int randnumberAdventurers = 2 + (int)(Math.random() * 2);
    for (int i = 0; i < randnumberAdventurers; i++){
      party.add(createRandomAdventurer());
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party,enemies);//initial state.

    //Main loop
    //display this prompt at the start of the game.
    String preprompt = "> Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    drawText(preprompt,28,3);

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      clearLine(24, 2, 78);
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );
      //display event based on last turn's input
      if(partyTurn){
        Adventurer you = party.get(whichPlayer);
        Adventurer opp = enemies.get(whichOpponent);
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          clearLine(28,3,75);
          TextBox(8,3,75,2, you.attack(opp));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          clearLine(28,3,75);
          TextBox(8,3,75,2,you.specialAttack(opp));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          int team = Integer.parseInt(input.split(" ")[1]);
          if(team == whichPlayer){
            clearLine(28,3,75);
            TextBox(8,3,75,2, you.support());
          }
          else if(team < party.size()){
            clearLine(28,3,75);
            TextBox(8,3,75,2,you.support(party.get(team)));
          }
        }
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          clearLine(28,3,75);
          String prompt = "> Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          drawText(prompt, 28,3);

        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          clearLine(28,3,75);
          String prompt = "> press enter to see monster's turn";
          drawText(prompt, 28,3);
          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!
        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        Adventurer opp = enemies.get(whichOpponent);
        Adventurer target = party.get((int)(Math.random() * party.size()));
        TextBox(8,3,75,2,opp.attack(target));
        whichOpponent++;

        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        if(whichOpponent >= enemies.size()){
          //This is a player turn.
            //THIS BLOCK IS TO END THE ENEMY TURN
            //It only triggers after the last enemy goes.
            whichPlayer = 0;
            turn++;
            partyTurn=true;
            //display this prompt before player's turn
            String prompt = "> Enter command for "+party.get(whichPlayer)+": attack/special/quit";
            clearLine(28,3,75);
            drawText(prompt, 28,3);
        }
      }//end of one enemy.
      
      //display the updated screen after input has been processed.
      for (int i = 0; i<party.size();i++){
        if(party.get(i).getHP() <= 0){
          party.remove(i);
          i--;
        }
      }
      for(int i = 0; i< enemies.size(); i++){
        if(enemies.get(i).getHP() <= 0){
          enemies.remove(i);
          i--;
        }
      }
      if(party.size() == 0 && enemies.size() > 0){
        drawText("Game Over", 15, 3);
        quit();
      }
      else if(party.size() > 0 && enemies.size() == 0){
        drawText("Victory", 15, 3);
        quit();
      }
      drawScreen(party,enemies);

    }//end of main game loop


    //After quit reset things:
    quit();
}
}

  
