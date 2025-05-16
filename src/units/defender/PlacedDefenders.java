package units.defender;

import java.util.ArrayList;

public class PlacedDefenders {
    private ArrayList<ArrayList<Defender>> defendersRowList;
    private ArrayList<Defender> defendersRow0;
    private ArrayList<Defender> defendersRow1;
    private ArrayList<Defender> defendersRow2;
    private ArrayList<Defender> defendersRow3;
    private ArrayList<Defender> defendersRow4;

    public PlacedDefenders(){
        defendersRowList = new ArrayList<>();
        defendersRow0 = new ArrayList<>();
        defendersRow1 = new ArrayList<>();
        defendersRow2 = new ArrayList<>();
        defendersRow3 = new ArrayList<>();
        defendersRow4 = new ArrayList<>();
        defendersRowList.add(defendersRow0);
        defendersRowList.add(defendersRow1);
        defendersRowList.add(defendersRow2);
        defendersRowList.add(defendersRow3);
        defendersRowList.add(defendersRow4);

    }
    public void addDefender(Defender defender, int row, int column){
        defendersRowList.get(row).add(defender);
    }
    public void removeDeadDefender(Defender defender, int row) {
        defendersRowList.get(row).remove(defender);
    }

    public ArrayList<ArrayList<Defender>> getDefendersRowList() {
        return defendersRowList;
    }

    public ArrayList<Defender> getDefendersRow0() {
        return defendersRow0;
    }

    public ArrayList<Defender> getPlantsRow1() {
        return defendersRow1;
    }

    public ArrayList<Defender> getPlantsRow2() {
        return defendersRow2;
    }

    public ArrayList<Defender> getDefendersRow3() {
        return defendersRow3;
    }

    public ArrayList<Defender> getDefendersRow4() {
        return defendersRow4;
    }

}
