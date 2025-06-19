package SFMS;

import SFMS.filemanager.SecureFileManager;
import SFMS.models.Role;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SecureFileManager manager = new SecureFileManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "1. Add File\n2. Retrieve File\n3. Delete File\n4. Search Files (Wildcard)\n5. Reclaim Space\n6. Restore space\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String filename = scanner.nextLine();
                    System.out.print("Enter file content: ");
                    String content = scanner.nextLine();

                    System.out.println("Select Role: \n1. OWNER\n2. READ_ONLY\n3. ADMIN");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();

                    Role role = switch (roleChoice) {
                        case 1 -> Role.OWNER;
                        case 2 -> Role.READ_ONLY;
                        case 3 -> Role.ADMIN;
                        default -> Role.OWNER;
                    };

                    manager.addFile(filename, content, role);
                    break;

                case 2:
                    System.out.print("Enter file name to retrieve: ");
                    filename = scanner.nextLine();
                    System.out.println("Select Your Role: \n1. OWNER\n2. READ_ONLY\n3. ADMIN");
                    roleChoice = scanner.nextInt();
                    scanner.nextLine();

                    Role userRole = switch (roleChoice) {
                        case 1 -> Role.OWNER;
                        case 2 -> Role.READ_ONLY;
                        case 3 -> Role.ADMIN;
                        default -> Role.READ_ONLY;
                    };

                    manager.retrieveFile(filename, userRole);
                    break;

                case 3:
                    System.out.print("Enter file name to delete: ");
                    filename = scanner.nextLine();
                    System.out.println("Select Your Role: \n1. OWNER\n2. ADMIN");
                    roleChoice = scanner.nextInt();
                    scanner.nextLine();

                    userRole = switch (roleChoice) {
                        case 1 -> Role.OWNER;
                        case 2 -> Role.ADMIN;
                        default -> Role.READ_ONLY;
                    };

                    manager.deleteFile(filename, userRole);
                    break;

                case 4:
                    System.out.print("Enter file prefix (e.g., 'doc*'): ");
                    String prefix = scanner.nextLine();
                    manager.searchFilesByWildcard(prefix);
                    break;

                case 5:
                    manager.reclaimSpace();
                    break;

                case 6:
                    System.out.print("Enter file name to restore: ");
                    filename = scanner.nextLine();
                    manager.restoreFile(filename);
                    break;

                case 7:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}