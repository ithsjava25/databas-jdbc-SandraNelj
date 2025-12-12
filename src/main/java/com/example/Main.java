package com.example;

import com.example.datasource.SimpleDataSource;
import com.example.repository.AccountRepository;
import com.example.repository.MoonMissionRepository;
import com.example.repository.jdbc.JdbcAccountRepository;
import com.example.repository.jdbc.JdbcMoonMissionRepository;
import com.example.service.AccountService;
import com.example.service.MoonMissionService;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    /**
     * Application entry point that initializes a development database when in dev mode and starts the interactive console.
     *
     * @param args command-line arguments; used to detect development mode (for example, "--dev" or system/VM properties)
     */

    public static void main(String[] args) {
        if (isDevMode(args)) {
            DevDatabaseInitializer.start();
        }
        new Main().run();
    }
    /**
     *
     * Runs the application's interactive console: initializes persistence, authenticates a user,
     * and enters the main command loop for managing moon missions and accounts.
     *
     * <p>Resolves database configuration (system properties first, then environment variables),
     * constructs a DataSource, repository and service layer, prompts for credentials and authenticates.
     * After successful login, presents a menu to list missions, retrieve a mission by ID, count missions
     * by year, create an account, update a password, delete an account, or exit.</p>
     *
     * @throws IllegalStateException if any of APP_JDBC_URL, APP_DB_USER or APP_DB_PASS is not provided
     */

    public void run() {
        // Resolve DB settings with precedence: System properties -> Environment variables
        String jdbcUrl = resolveConfig("APP_JDBC_URL", "APP_JDBC_URL");
        String dbUser  = resolveConfig("APP_DB_USER", "APP_DB_USER");
        String dbPass  = resolveConfig("APP_DB_PASS", "APP_DB_PASS");

        if (jdbcUrl == null || dbUser == null || dbPass == null) {
            throw new IllegalStateException(
                    "Missing DB configuration. Provide APP_JDBC_URL, APP_DB_USER, APP_DB_PASS " +
                            "as system properties (-Dkey=value) or environment variables.");
        }

        //Todo: Starting point for your code

        DataSource dataSource =  new SimpleDataSource(jdbcUrl, dbUser, dbPass);

        AccountRepository accountRepository = new JdbcAccountRepository(dataSource);
        MoonMissionRepository moonMissionRepository = new JdbcMoonMissionRepository(dataSource);

        AccountService accountService = new AccountService(accountRepository);
        MoonMissionService moonMissionService = new MoonMissionService(moonMissionRepository);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        boolean ok = accountService.login(username, password);

        if (!ok) {
            System.out.println("Invalid username or password.");
            System.out.println("(0) Exit");
            scanner.nextLine();
            return;
        }

        while (true) {
            System.out.println("(1) List moon missions");
            System.out.println("(2) Get mission by ID");
            System.out.println("(3) Count missions by year");
            System.out.println("(4) Create account");
            System.out.println("(5) Update password");
            System.out.println("(6) Delete account");
            System.out.println("(0) Exit");

            String choice = scanner.nextLine().trim();


            switch (choice) {
                /**
                 * List all missions
                 */
                case "1": {
                    moonMissionService.listSpacecraft().forEach(System.out::println);
                    break;
                }

                /**
                 * Get missions by ID
                 */
                case "2": {
                    System.out.println("Mission_id: ");
                    String input = scanner.nextLine().trim();

                    int id;
                    try {
                        id = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please try again.");
                        break;
                    }
                    var m = moonMissionService.getById(id);

                    if (m == null) {
                        System.out.println("Mission not found.");
                    } else {
                        System.out.println("Spacecraft: " + m.getSpacecraft());
                        System.out.println("Launch date: " + m.getLaunchDate());
                        System.out.println("Carrier Rocket: " + m.getCarrierRocket());
                        System.out.println("Operator: " + m.getOperator());
                        System.out.println("Mission type: " + m.getMissionType());
                        System.out.println("Outcome: " + m.getOutcome());
                    }
                    break;
                }

                /**
                 * Count missions per year
                 */
                case "3": {
                    System.out.println("year: ");
                    if (!scanner.hasNextLine()) return;
                    String yearStr = scanner.nextLine().trim();
                    int year;
                    try {
                        year = Integer.parseInt(yearStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year. Please enter a number!");
                        break;
                    }
                    int count = moonMissionService.countByYear(year);
                    System.out.println(count + " missions in " + year);
                    break;
                }

                /**
                 * Create account
                 */
                case "4": {
                    System.out.println("First name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("SSN: ");
                    String ssn = scanner.nextLine();
                    System.out.println("Password: ");
                    String pw = scanner.nextLine();

                    accountService.create(firstName, lastName, ssn, pw);
                    System.out.println("Account created.");
                    break;
                }

                /**
                 * Update password
                 */
                case "5": {
                    System.out.println("User_id: ");
                    String input = scanner.nextLine().trim();

                    int id;
                    try {
                        id = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid User-ID. Please enter a number!");
                        break;
                    }

                    System.out.println("New password: ");
                    String newPw = scanner.nextLine();

                    accountService.updatePassword(id, newPw);
                    System.out.println("Password updated.");
                    break;
                }


                /**
                 * Delete account
                 */
            case "6": {
                System.out.println("User_id: ");
                String input = scanner.nextLine().trim();

                int id;
                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid UserID. Please enter numbers!");
                    break;
                }
                    accountService.delete(id);
                    System.out.println("Account deleted.");
                    break;
                }

            case "0": {
                return;
            }
                default:  {
                    System.out.println("Invalid choice.");
                    break;
                }
        }
    }
}

    /**
     * Determines if the application is running in development mode based on system properties,
     * environment variables, or command-line arguments.
     */
    private static boolean isDevMode(String[] args) {
        if (Boolean.getBoolean("devMode"))  //Add VM option -DdevMode=true
            return true;
        if ("true".equalsIgnoreCase(System.getenv("DEV_MODE")))  //Environment variable DEV_MODE=true
            return true;
        return Arrays.asList(args).contains("--dev"); //Argument --dev
    }

    /**
     * Reads configuration with precedence: Java system property first, then environment variable.
     * Returns trimmed value or null if neither source provides a non-empty value.
     */
    private static String resolveConfig(String propertyKey, String envKey) {
        String v = System.getProperty(propertyKey);
        if (v == null || v.trim().isEmpty()) {
            v = System.getenv(envKey);
        }
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }
}
