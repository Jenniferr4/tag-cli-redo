package com.improving.tagcli;


import com.improving.tagcli.database.DatabaseClient;
import com.improving.tagcli.database.OldSchoolDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {

    @Autowired
    private final DatabaseClient databaseClient;

    public TagCliApplication(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(TagCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //databaseClient.insertIntoTable();
//		databaseClient.readWeaponsFromTable();

        Scanner input = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
			System.out.println("Do you want to [1] Add Data, [2] Read Data");
            int choice = input.nextInt();
            if (choice == 1) {
                System.out.println("Would you like to [1] Add Emote, [2] Add Weapon");
            }
            if (choice == 2) {
                System.out.println("Would you like to [1] Add Emote, [2] Add Weapon");
            }
            choice = input.nextInt();
            if (choice == 1) {
                databaseClient.insertIntoTableEmote();
            }
            if (choice == 2) {
                databaseClient.insertIntoTableWeapon();
            }
        }
    }
}


