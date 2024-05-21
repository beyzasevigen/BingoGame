/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingogame1;

/**
 *
 * @author Beyza
 */
public class Node {
     int data;
     Node next;
     boolean isChecked;
    Node down;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.down = null;
    }
}
