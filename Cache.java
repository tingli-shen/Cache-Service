
import java.lang.String;
public interface Cache {
    void set(int key, int value);
    int get(int key);
    String getName();
}
