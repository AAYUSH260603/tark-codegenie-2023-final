//WORK IN PROGRESS

import java.util.*;

class Train {
    int trainNumber;
    String sourcePlace;
    String destinationPlace;
    ArrayList<String> trainCoaches;
    int distance;
    HashMap<Character, Integer> map;
    String date;

    Train(int trainNumber, String sourcePlace, String destinatonPlace, String coach, int distance) {
        this.trainNumber = trainNumber;
        this.sourcePlace = sourcePlace;
        this.destinationPlace = destinatonPlace;
        String[] coaches = coach.split(" ");
        for (int i = 0; i < coaches.length; i++) {
            System.out.println(coaches[i]);
        }

        trainCoaches = new ArrayList<>();
        for (int i = 1; i < coaches.length; i++) {
            trainCoaches.add(coaches[i].substring(0, 2));
        }
       
        map = new HashMap<>();
        this.distance = distance;
        for (int i = 1; i < coaches.length; i++) {

            if (map.containsKey(coaches[i].charAt(0))) {
                map.put(coaches[i].charAt(0),
                        map.get(coaches[i].charAt(0)) + Integer.parseInt(coaches[i].substring(3)));
            } else {

                map.put(coaches[i].charAt(0), Integer.parseInt(coaches[i].substring(3)));
            }
        }
        System.out.println(map);
    }

};

class trainBooking {
    public int checkAvailability(String userInput, int numberOfTrains, Train[] train) {
        String[] input = userInput.split(" ");
        for (int i = 0; i < numberOfTrains; i++) {
            String[] src = train[i].sourcePlace.split("-");
            String[] des = train[i].destinationPlace.split("-");
            if (src[0].equals(input[0]) && des[0].equals(input[1])
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
            char coach = train.trainCoaches.get(i).charAt(0);
            System.out.println(coach);
            if (coach == (category.charAt(0)) && train.map.get(coach) > number) {
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
        int numberOfTrains = Integer.parseInt(sc.nextLine());

        Train[] train = new Train[numberOfTrains];

        for (int i = 0; i < numberOfTrains; i++) {
            String inputP = sc.nextLine();
            String inputC = sc.nextLine();
            String[] inputPlace = inputP.split(" ");
            int index = 0;

            for (int j = 0; j < inputPlace[2].length(); j++) {
                if (inputPlace[2].charAt(j) == '-') {
                    index = j;
                }
            }
            int distance = Integer.parseInt(inputPlace[2].substring(index + 1));

            train[i] = new Train(Integer.parseInt(inputPlace[0]), inputPlace[1], inputPlace[2], inputC, distance);
            // System.out.println("ff");
        }
        trainBooking t = new trainBooking();
        while (true) {
            String userInput = sc.nextLine();
            int cost = t.checkAvailability(userInput, numberOfTrains, train);
            if (cost == -1) {
                System.out.println("no seats available");
            } else {
                System.out.println(pnr + " " + cost);
                pnr++;
            }
        }
    }
}
