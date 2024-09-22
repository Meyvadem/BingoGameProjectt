/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingogameeee;

/**
 *
 * @author Merve
 */
public class Node {
    int data;
    Node next;
    Node down;
    boolean isFound;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.down = null;
        boolean isFound = false;
    }
}
