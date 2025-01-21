public class Boss extends Adventurer {
  int insults, insultsMax;

  public Boss() {
    super("Gordon Ramsay", 40);
    insultsMax = 10;
    insults = insultsMax;
  }

  public String getSpecialName(){
    return "insults";
  }

  public int getSpecial(){
    return insults;
  }

  public void setSpecial(int n){
    insults = n;
  }

  public int getSpecialMax(){
    return insultsMax;
  }

  public String attack(Adventurer other){
    int damage = (int) (Math.random() * 3) + 4;
    other.applyDamage(damage);
    setSpecial(getSpecial() - 2);
    return this + " spouted harsh words at "+ other + ", dealing "+ damage + " points of emotional damage. " + this + " has " + getSpecial() + " insults left.";
  }

  public String specialAttack(Adventurer other){
    if(getSpecial() >= 6){
      setSpecial(getSpecial()-6);
      int damage = (int)(Math.random()*5)+9;
      other.applyDamage(damage);
      return this + " delivered the ultimate insult at " + other + "." + " This dealt "+ damage +" points of emotional damage to " + other + "." + this + " has " + getSpecial() + " insults left.";
    }else{
      return "Not enough insults to use the ultimate insult. Instead, "+attack(other);
    }
  }

  public String support() {
    int hp = 7;
    setHP(getHP() + hp);
    setSpecial(getSpecial() + 3);
    if (getSpecial() > getSpecialMax()) {
        setSpecial(getSpecialMax());
    }
    return this + " consumed delicious food, recovering " + hp + " HP and restoring 3 insults.";
}
