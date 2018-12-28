public class MyDoublyLinkedList {
    private MyDoublyLinkedData head;
    private MyDoublyLinkedData tail;
    private MyDoublyLinkedData prevCurrent;
    private MyDoublyLinkedData nextCurrent;    
    private MyDoublyLinkedData current;
    private int compareCount;

    MyDoublyLinkedList() {
        head = new MyDoublyLinkedData("*header*", "");
        tail = head;
        current = head;
        tail.next = null;
    }

    public MyDoublyLinkedData getCurrent() {
        return current;
    }
    
    public boolean movePrev(){
    	if (current.prev != null) {
            nextCurrent = current;
            current = current.prev;
            return true;
        } else {
            return false;
        }
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
    public void insertAfter(String key, String data) {
        MyDoublyLinkedData newData = new MyDoublyLinkedData(key, data);

        newData.prev = current;
        if(current.next == null){
        	newData.next = null;
        	tail.next = newData;
        }
        else{
        	newData.next = current.next;
        	current.next.prev = newData;
        	current.next = newData;
        }
    }
    
    public void insertBefore(String key, String data) {
        MyDoublyLinkedData newData = new MyDoublyLinkedData(key, data);

        newData.next = current;
        if(current.prev == null){
        	newData.prev = null;
        	head.prev = newData;
        }
        else{
        	newData.prev = current.prev;
        	current.prev.next = newData;
        	current.prev = newData;
        }

    }
    
    public void printInfo() {
        MyDoublyLinkedData data = head;
        while (data != null) {
            System.out.println(data.toStringNodeData());
            data = data.next;
        }
    }

    // 連結リストの末尾に新規データを挿入
    public void insertLast(String key, String data) {
        MyDoublyLinkedData newData = new MyDoublyLinkedData(key, data);
        newData.next = null;
        tail.next = newData;
        prevCurrent = tail;

        tail = newData;
        current = newData;
    }

    public MyDoublyLinkedData searchByKey(String key) {
        MyDoublyLinkedData p = head;
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

    public MyDoublyLinkedData searchByData(String data) {
        MyDoublyLinkedData p = head;
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

    public MyDoublyLinkedData delete() {
    	if (current != null) {
            if (current.getKey().equals("*header*")) {
                return null;
            }
            MyDoublyLinkedData prevData = prevCurrent;
            prevData.next = current.next;
            MyDoublyLinkedData oldCurrent = current;
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
        MyDoublyLinkedData p = head;
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
