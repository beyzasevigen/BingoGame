/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingogame1;

import static com.mycompany.bingogame1.MainGamePanel.generatePermutation;
import java.awt.Color;

/**
 *
 * @author Beyza
 */
public class LinkedList {

    public Node head;
    private int size;

    public int size() {
        return size;
    }

    LinkedList() {
        head = null;
    }

    void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        size++;
    }

    void remove() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        size--;
        System.out.println();
    }

    public int get(int index) {
        Node current = head;
        int currentIndex = 0;

        while (current != null) {
            if (currentIndex == index) {
                return current.data; 
            }
            current = current.next;
            currentIndex++;
        }

        return -1;
    }

    public void markNumber(int randomNum) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == randomNum) {
                temp.isChecked = true;
            }
            temp = temp.next;
        }
    }

    public int checkBingo() {
        int firstRowCount = 0;
        int secondRowCount = 0;
        int thirdRowCount = 0;
        int cinkoCount = 0;

        Node temp = head;
        int count = 0;
        while (temp != null) {
            if (count < 5 && temp.isChecked) {
                firstRowCount++;
            } else if (count < 10 && temp.isChecked) {
                secondRowCount++;
            } else if (count < 15 && temp.isChecked) {
                thirdRowCount++;
            }
            count++;
            temp = temp.next;
        }

        if (firstRowCount == 5) {
            cinkoCount++;
        }
        if (secondRowCount == 5) {
            cinkoCount++;
        }
        if (thirdRowCount == 5) {
            cinkoCount++;
        }
        return cinkoCount;
    }
}
