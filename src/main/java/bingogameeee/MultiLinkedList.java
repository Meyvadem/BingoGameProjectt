/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingogameeee;

/**
 *
 * @author Merve
 */
public class MultiLinkedList {
    Node head;
    int size;

    public MultiLinkedList() {
        this.head = null;
    }

    // Yatay (next) bağlantıları oluşturma
    private void connectNext(Node a, Node b) {
        a.next = b;
    }

    // Dikey (down) bağlantıları oluşturma
    private void connectDown(Node a, Node b) {
        a.down = b;
    }

    public void createMultilinkedList() {
        // Başlangıç düğümünü oluştur
        head = new Node(1);
        Node current = head;

        // Döngüyle diğer düğümleri oluştur
        for (int i = 2; i <= 15; i++) {
            Node newNode = new Node(i);

            // Yatay bağlantıları kur
            if (i % 5 != 0 && i != 6 && i != 11) {
                connectNext(current, newNode);
            }

            // Dikey bağlantıları kur
            if (i == 6) {
                connectDown(head, newNode); // 1. düğümün altına bağlantı kur
            } else if (i == 11) {
                connectDown(head.down, newNode); // 6. düğümün altına bağlantı kur
            }

            // Geçerli düğümü güncelle
            current = newNode;

            // 4. düğümün next'ini 5. düğüm, 9. düğümün next'ini 10. düğüm, 14. düğümün next'ini 15. düğüm olarak ayarla
            if (i == 4) {
                connectNext(current, new Node(i + 1));
            } else if (i == 9) {
                connectNext(current, new Node(i + 1));
            } else if (i == 14) {
                connectNext(current, new Node(i + 1));
            }
        }
    }

    public Node getNodeFromIndex(int index) {
        if (index < 0 || index > 14) {
            return null; // Geçersiz indis, null döndür
        }
        Node current = head;
        if (index <= 4) {
            for (int i = 0; i < index; i++) {
                if (current == null) {
                    return null; // Düğüm yok, null döndür
                }
                current = current.next;
            }
        } else if (index <= 9) {
            current = head.down;
            for (int i = 5; i < index; i++) {
                if (current == null) {
                    return null; // Düğüm yok, null döndür
                }
                current = current.next;
            }
        } else if (index <= 14) {
            current = head.down.down;
            for (int i = 10; i < index; i++) {
                if (current == null) {
                    return null; // Düğüm yok, null döndür
                }
                current = current.next;
            }
        }

        return current;
    }
    
    // Çoklu bağlantılı listeyi yazdırma
    public void printMultilinkedList() {
        Node current = this.head;
        while (current != null) {
            Node tempDown = current;
            while (tempDown != null) {
                System.out.print(tempDown.data + " ");
                tempDown = tempDown.next;
            }
            System.out.println();
            current = current.down;
        }

        System.out.println("");
    }
}
