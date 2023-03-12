import java.util.*;

class Train {
    int trainNumber;
    String sourcePlace;
    String destinationPlace;
    ArrayList<String> trainCoaches;
    int distance;
    HashMap<String, Integer> map;

    Train(int trainNumber, String sourcePlace, String destinatonPlace, String coach, int distance) {
        this.trainNumber = trainNumber;
        this.sourcePlace = sourcePlace;
        this.destinationPlace = destinatonPlace;
        String[] coaches = coach.split(" ");
        trainCoaches = new ArrayList<>();
        for (int i = 1; i < coach.length(); i++) {
            trainCoaches.add(coaches[i]);
        }
        map = new HashMap<>();
        this.distance = distance;
        for (int i = 0; i < coaches.length; i++) {
            map.put(coaches[i].substring(0, 3), Integer.parseInt(coaches[i].substring(3)));
        }
    }

};

class trainBooking {

    public int checkAvailability(String userInput, int numberOfTrains, Train[] train) {
        String[] input = userInput.split(" ");

        for (int i = 0; i < numberOfTrains; i++) {
            if (train[i].sourcePlace.equals(input[0]) && train[i].sourcePlace.equals(input[1])
                    && checkSeats(train[i], input[2], input[3], Integer.parseInt(input[4]))) {
                if (input[3].equals("SL")) {
                    return (Integer.parseInt(input[4])) * train[i].distance;
                } else if (input[3].equals("3A")) {
                    return 2 * (Integer.parseInt(input[4])) * train[i].distance;
                } else if (input[3].equals("2A")) {
                    return 3 * (Integer.parseInt(input[4])) * train[i].distance;
                } else if (input[3].equals("1A")) {
                    return 4 * (Integer.parseInt(input[4])) * train[i].distance;
                }
            }
        }
        return -1;
    }

    public boolean checkSeats(Train train, String date, String category, int number) {
        for (int i = 0; i < train.trainCoaches.size(); i++) {
            String coach = "" + train.trainCoaches.get(i).charAt(0);
            if (coach.equals(category) && train.map.get(coach) > number) {
                train.map.put(coach, train.map.get(coach) - number);
                return true;
            }

        }
        return false;
    }

}

public class tarkFinal {
   static int pnr = 100000001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTrains = sc.nextInt();
        Train[] train=new Train[numberOfTrains];

        for (int i = 0; i < numberOfTrains; i++) {
            char c=sc.next().charAt(0);
            String inputP = sc.nextLine();
            // System.out.println("aaa");
            String inputC = sc.nextLine();
            String[] inputPlace = inputP.split(" ");
            System.out.println(inputPlace.length);
            // String[] inputCoaches=inputC.split(" ");
            int index = 0;
            for (int j = 0; j < inputPlace[2].length(); j++) {
                if (inputPlace[2].charAt(j) == '-') {
                    index = j;
                }
            }
            int distance = Integer.parseInt(inputPlace[2].substring(index + 1));
            train[i] = new Train(Integer.parseInt(inputPlace[0]), inputPlace[1], inputPlace[2], inputC, distance);
        }
        trainBooking t = new trainBooking();
        while (true) {
            String userInput = sc.nextLine();
            int cost = t.checkAvailability(userInput, numberOfTrains, train);
            if (cost == -1) {
                System.out.println("no seats available");
            } else {
                System.out.println(pnr + " " + cost);
            }
        }
    }
}
