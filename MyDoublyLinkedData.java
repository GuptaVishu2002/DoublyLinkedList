public class MyDoublyLinkedData {
    private String key;
    private String data;
    MyDoublyLinkedData next;
    MyDoublyLinkedData prev;

    MyDoublyLinkedData(String key, String data){
        this.key = key;
        this.data = data;
    }

    public String getKey(){
        return this.key;
    }

    public String getData(){
        return this.data;
    }

    public void setKey(String key){
        this.key = key;
    }

    public void setData(String data){
        this.data = data;
    }
    
    public String toStringSimple(){
        String str = "[" + this.key + "] " + this.data + ": prev [";
        str = str + (this.prev != null ? this.prev.getKey() : "null");
        str = str + "]: next[";
        str = str + (this.next != null ? this.next.getKey() : "null");
        str = str + "]";
        return str;
    }
    
    public String toStringNodeData(){
		return "prev:[" + ((this.prev==null) ? "null" : this.prev.getKey()) + "] current:[" + this.key + "] next:[" + ((this.next==null) ? "null" : this.next.getKey()) + "]";
    }

}
