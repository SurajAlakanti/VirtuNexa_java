import java.util.ArrayList;
import java.util.Scanner;

class YogaPoseGuide {
    static class YogaPose {
        String name;
        String benefits;
        int duration;

        public YogaPose(String name, String benefits, int duration) {
            this.name = name;
            this.benefits = benefits;
            this.duration = duration;
        }

        public void displayPose() {
            System.out.println("Pose: " + name);
            System.out.println("Benefits: " + benefits);
            System.out.println("Hold for " + duration + " seconds.");
        }
    }

    private ArrayList<YogaPose> poses;

    public YogaPoseGuide() {
        poses = new ArrayList<>();
        poses.add(new YogaPose("Downward Dog", "Improves flexibility, strengthens muscles.", 30));
        poses.add(new YogaPose("Tree Pose", "Improves balance and stability.", 45));
        poses.add(new YogaPose("Warrior I", "Strengthens legs, improves focus.", 60));
    }

    public void showPoses() {
        System.out.println("\nList of Yoga Poses:");
        for (int i = 0; i < poses.size(); i++) {
            System.out.println((i + 1) + ". " + poses.get(i).name);
        }
    }

    public void showPoseDetails(int poseIndex) {
        if (poseIndex >= 0 && poseIndex < poses.size()) {
            poses.get(poseIndex).displayPose();
        } else {
            System.out.println("Invalid pose number.");
        }
    }

    public void startTimer(int seconds) {
        System.out.println("Starting timer for " + seconds + " seconds...");
        try {
            for (int i = 1; i <= seconds; i++) {
                Thread.sleep(1000);
                System.out.println(i + " seconds passed.");
            }
            System.out.println("Timer completed!");
        } catch (InterruptedException e) {
            System.out.println("Timer was interrupted.");
        }
    }

    public static void main(String[] args) {
        YogaPoseGuide guide = new YogaPoseGuide();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nYoga Pose Guide");
            System.out.println("1. View Yoga Poses");
            System.out.println("2. Select a Pose");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    guide.showPoses();
                    break;
                case 2:
                    System.out.print("Enter pose number to view details: ");
                    int poseChoice = scanner.nextInt() - 1; 
                    guide.showPoseDetails(poseChoice);
                    System.out.print("Do you want to start the timer for this pose? (yes/no): ");
                    scanner.nextLine(); 
                    String timerChoice = scanner.nextLine();
                    if (timerChoice.equalsIgnoreCase("yes")) {
                        guide.startTimer(guide.poses.get(poseChoice).duration);
                    }
                    break;
                case 3:
                    System.out.println("Exiting Yoga Pose Guide.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }
}