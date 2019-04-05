/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.utils;

import java.util.Random;

/**
 *
 * @author Blackstorm
 */
public final class GenerateID {
    private String[] id = new String[66];
    private Random rand;
    
    public GenerateID() {
        id = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "#", "$", "@", "&"
        };
        rand = new Random();
    }
    
    public String createID() {
        StringBuilder IDbuilder = new StringBuilder();
        
        for (int i = 0; i < 10; i++) {
            IDbuilder.append(this.id[rand.nextInt(66)]);
        }
        
        return IDbuilder.toString();
    }
}
