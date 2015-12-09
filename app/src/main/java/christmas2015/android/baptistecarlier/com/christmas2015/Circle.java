package christmas2015.android.baptistecarlier.com.christmas2015;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by BapNesS on 09/12/15.
 */
public class Circle {

    private final static String TAG = "Circle";

    public final static int DEFAULT_COUNTER_VALUE = 10;

    private List<People> everybody;

    private void log(String message) {
        Log.d(TAG, message);
    }

    public Circle() {
        this.everybody = new ArrayList<People>();
    }

    public void generate(int counterMaxLimit ) {
        int counter = 0;
        if ( counterMaxLimit <= 0 ) {
            counterMaxLimit = DEFAULT_COUNTER_VALUE;
        }

        do {
            log("generate : "+counter+"/"+counterMaxLimit);
            init();
            // Creepy strategy but will works
            Collections.shuffle(this.everybody);
            for (int i = 0;i < this.everybody.size();i++) {
                log("generate : " + i + ". " + this.everybody.get(i).getName() + " pour "+((i+1)%this.everybody.size())+". "+this.everybody.get((i+1)%this.everybody.size()).getName());
                this.everybody.get(i).setOfferTo( this.everybody.get((i+1)%this.everybody.size()) );
            }
            counter++;
            displayCircle();
        } while ( !testFinalLoop() && counter < counterMaxLimit );
    }

    public boolean testFinalLoop() {
        People current = null, next = null;
        List<People> checkList = new ArrayList<People>();

        if ( this.everybody == null ) {
            return false;
        }

        for (int i = 0 ; i < this.everybody.size() ; i++) {
            if ( current == null ) {
                current = this.everybody.get(i);
            } else {
                current = next;
            }

            next = current.getOfferTo();
            if ( next == null ) {
                return false;
            }
            if ( checkList.contains(next) ) {
                return false;
            }
            checkList.add(next);
            if ( current.getCantOfferTo().contains(next) ) {
                return false;
            }
        }
        return checkList.size() == this.everybody.size();
    }

    private void init() {
        this.everybody = new ArrayList<People>();

        List<People> couple;

        // Données
        couple = initCouple("S", "0000000001", "JL", "0000000002");
        addCoupleToParticipants(couple);
        couple = initCouple("F", "0000000001", "G", "0000000002");
        addCoupleToParticipants(couple);
        couple = initCouple("A", "0000000001", "Su", "0000000002");
        addCoupleToParticipants(couple);
        couple = initCouple("L", "0000000001", "B", "0000000002");
        addCoupleToParticipants(couple);

    }

    private List<People> initCouple(String nomA, String numeroA,
                                    String nomB, String numeroB) {

        People personneA = new People(nomA, numeroA);
        People personneB = new People(nomB, numeroB);
        personneA.addCantOfferTo(personneB);
        personneB.addCantOfferTo(personneA);

        List<People> monCouple = new ArrayList<People>();

        monCouple.add(personneA);
        monCouple.add(personneB);

        return monCouple;
    }

    private void addCoupleToParticipants(List<People> couple) {
        if ( couple != null && couple.size() == 2 ) {
            this.everybody.add(couple.get(0));
            this.everybody.add(couple.get(1));
        }
    }
    public List<People> getEverybody() {
        return this.everybody;
    }

    private void displayCircle() {

        StringBuilder sb = new StringBuilder();
        for ( People p : this.everybody ) {
            sb.append(p.getName());
            sb.append(" -> ");
            sb.append(p.getOfferTo().getName());
            sb.append(" ; ");
        }
        log(sb.toString());
    }

}
