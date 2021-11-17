package org.example;    // DO NOT CHANGE THIS FILE      November2021

// This class defines an unique ID generator which can be used
// to generate ID values (of type int).  The class maintains an int variable
// 'nextId' that stores the next available number that can be used as an id.
//  It is simply incremented each time therefore providing unique numbers.
//  It persists the next ID, by saving it to a text file, and updates the text file
//  with the new nextId every time a new ID is allocated.
//
// The intention is that this generator would be used by Vehicle, Passenger and
// Booking classes when they need to generate a new id field value
//
// This class creates a Singleton object-
// meaning that there can only be ONE instance of this class created.
//
// The file "next-id-store.txt" is be seeded with an initial id number (e.g. 101)
// so, that number will be the first id allocated.  Note that there is no meaning to the
// ids being allocated - and you should not assume that they give any sequencing
// information about the objects.  They are simply unique numbers (ids).
//
// This work is usually done by the DBMS
// The write-to-file each time approach may be inefficient,
// but it is easy to understand.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IdGenerator {

    private static IdGenerator idGenerator; // reference to instance of this class
    private int nextId;
    private String fileName;

    private IdGenerator(String fileName) {     // "private" prevents external construction
    this.fileName = fileName;
        // open file and read the last saved "nextId" value
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                nextId = scanner.nextInt();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading nextId from file: next-id-store.txt");
            e.printStackTrace();
        }
    }

    // get an Instance of this class (an IdGenerator object)
    // If an instance exists return that instance, and if not
    // create a new instance.  This setup ensures that there
    // will be only one instance created (a singleton)
    //
    public static IdGenerator getInstance(String fileName) {
        if (idGenerator == null) {
            idGenerator = new IdGenerator(fileName);
        }
        return idGenerator;
    }

    // Return nextId available and pre-save it to the file.
    // This ensures that when the system exits, the up to date
    // nextId value will have been written to the file
    //
    public int getNextId() {
        File file = new File(fileName);
        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter(file);
            fWriter.write(Integer.toString(nextId+1));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return nextId++;    // return id and then increment it
    }

}

