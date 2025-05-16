package units.enemy;

import java.util.ArrayList;

public class Enemies {
    private ArrayList<ArrayList<Enemy>> enemiesRowList;
    private ArrayList<Enemy> enemiesRow1;
    private ArrayList<Enemy> enemiesRow2;
    private ArrayList<Enemy> enemiesRow3;
    private ArrayList<Enemy> enemiesRow4;
    private ArrayList<Enemy> enemiesRow0;

    public Enemies(){
        enemiesRowList = new ArrayList<>();
        enemiesRow1 = new ArrayList<>();
        enemiesRow2 = new ArrayList<>();
        enemiesRow3 = new ArrayList<>();
        enemiesRow4 = new ArrayList<>();
        enemiesRow0 = new ArrayList<>();
        enemiesRowList.add(enemiesRow0);
        enemiesRowList.add(enemiesRow1);
        enemiesRowList.add(enemiesRow2);
        enemiesRowList.add(enemiesRow3);
        enemiesRowList.add(enemiesRow4);

    }

    public void removeDeadEnemies(ArrayList<Enemy> enemyInRow, Enemy enemy) {
        enemyInRow.remove(enemy);
    }

    public ArrayList<ArrayList<Enemy>> getEnemiesRowList() {
        return enemiesRowList;
    }

    public ArrayList<Enemy> getEnemiesRow1() {
        return enemiesRow1;
    }

    public ArrayList<Enemy> getEnemiesRow2() {
        return enemiesRow2;
    }

    public ArrayList<Enemy> getEnemiesRow3() {
        return enemiesRow3;
    }

    public ArrayList<Enemy> getEnemiesRow4() {
        return enemiesRow4;
    }

    public ArrayList<Enemy> getEnemiesRow0() {
        return enemiesRow0;
    }
}
