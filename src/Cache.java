import java.util.LinkedList;

public class Cache {
    private int size;
    private int sizeLimit;
    private int index;
    LinkedList<String> list = new LinkedList<String>();
    public Cache(int sizeInput) {
        sizeLimit = sizeInput;
    }
    public int getObject(String input) {
        index = list.indexOf(input);
        return index;
    }
    public void addObject(String input) {
        list.addFirst(input);
        size+=1;
        if (size > sizeLimit) {
            list.removeLast();
            size-=1;
        }
    }
    public void removeObject(int input) {
        list.remove(input);
        size-=1;
    }
    public void clearCache() {
        list.clear();
        size=0;
    }
}
