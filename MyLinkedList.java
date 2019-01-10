public class MyLinkedList {
    private MyLinkedData head;
    private MyLinkedData tail;
    private MyLinkedData prevCurrent;
    private MyLinkedData current;
    private int compareCount;

    MyLinkedList() {
        head = new MyLinkedData("*header*", "");
        tail = head;
        current = head;
        tail.next = null;
    }

    public MyLinkedData getCurrent() {
        return current;
    }

    public void setCurrent(String str) {
        if ("head".equals(str)) {
            current = head;
        } else if ("tail".equals(str)) {
            current = tail;
        }
    }

    public boolean moveNext() {
        if (current.next != null) {
            prevCurrent = current;
            current = current.next;
            return true;
        } else {
            return false;
        }
    }

    public void resetCompareCount() {
        compareCount = 0;
    }

    public void setCompareCount(int count) {
        compareCount = count;
    }

    public int getCompareCount() {
        return compareCount;
    }

    // current の後ろに新規データを挿入
    public void insert(String key, String data) {
        MyLinkedData newData = new MyLinkedData(key, data);

        newData.next= current.next; // (1)
		current.next= newData;      // (2)
		prevCurrent = current;
		current = newData;

    }

    // 連結リストの末尾に新規データを挿入
    public void insertLast(String key, String data) {
        MyLinkedData newData = new MyLinkedData(key, data);
        newData.next = null;
        tail.next = newData;
        prevCurrent = tail;

        tail = newData;
        current = newData;
    }

    public MyLinkedData searchByKey(String key) {
        MyLinkedData p = head;
        while (p != null) {
            if (strcmp(key, p.getKey()) == 0) {
                current = p;
                return p;
            }
            prevCurrent = p;
            p = p.next;
        }
        prevCurrent = null;
        current = head;
        return null;
    }

    public MyLinkedData searchByData(String data) {
        MyLinkedData p = head;
        while (p != null) {
            if (strcmp(data, p.getData()) == 0) {
                current = p;
                return p;
            }
            prevCurrent = p;
            p = p.next;
        }
        prevCurrent = null;
        current = head;
        return null;
    }

    public MyLinkedData delete() {
        if (current != null) {
            if (current.getKey().equals("*header*")) {
                return null;
            }
            MyLinkedData prevData = prevCurrent;
            prevData.next = current.next;
            MyLinkedData oldCurrent = current;
            current = current.next;
            if (current == null) {
                tail = prevCurrent;
                prevCurrent = null;
                current = head;
            }
            return oldCurrent;
        } else {
            return null;
        }
    }

    public void printData() {
        MyLinkedData p = head;
        while (p != null) {
            if (p == current){
                System.out.print("*cur* ");
            }else{
                System.out.print("      ");
            }
            System.out.println(p.toStringSimple());
            p = p.next;
        }
    }

    public int strcmp(String key1, String key2) {
        compareCount++;
        return key1.compareTo(key2);
    }
}
