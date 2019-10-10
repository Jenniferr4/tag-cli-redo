package com.improving.tagcli.database;

import com.improving.tagcli.Models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class DatabaseClient {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseClient.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void readDataFromTable() {
        try {
            List<String> names = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
                    (result, rowNum) -> (result.getString("Name")));

        } catch (DataAccessException e) {
            logger.error("Error: ", e);
        }
    }


    public void insertIntoTableWeapon() {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want prompt to be?");
        String prompt = input.nextLine();
        System.out.println("What do you want text to be?");
        String text = input.nextLine();

        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon (Name, Area, ItemType) " + "VALUES ('" + prompt + "','" + text + "')");
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        }

    }


    public List<Weapon> readWeaponsFromTable() {
        try {
            List<Weapon> weapons = jdbcTemplate.query("Select * FROM weapon LIMIT 10",
                    (result, rowNum) ->
                            new Weapon(result.getInt("Id"),
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));
            weapons.forEach(weapon -> logger.info("Weapon Id: {}, Name: {}", weapon.getId(), weapon.getName()));
            return weapons;
        } catch (DataAccessException e) {
            logger.error("Error: ", e);
        }
        return Collections.EMPTY_LIST;
    }


    public void insertIntoTableEmote() {
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want prompt to be?");
        String prompt = input.nextLine();
        System.out.println("What do you want text to be?");
        String text = input.nextLine();

        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO emote (Prompt, Text) " + "VALUES ('" + prompt + "','" + text + "')");
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        }
    }

}
