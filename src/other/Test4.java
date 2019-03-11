package other;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
    String abcd = "abcd";
    String aBcd = "aBcd";
    String aBCd = "aBCd";
    String ABCd = "ABCd";

    public static void main(String[] args) throws IllegalAccessException {
        Test4 t = new Test4();
        HashMap<String, Object> json = new HashMap<>();
        HashMap<String, String> nameMap = new HashMap<>();
        nameMap.put("ABC", "ABC");
        t.getDataHelper(json, new UserInfo(), nameMap);
        System.out.println(json);
    }
    public static final Pattern HUMP2_ = Pattern.compile("([A-Z])");

    private void getDataHelper(Map<String, Object> json, Object obj, Map<String, String> nameMap) throws IllegalAccessException {
        Field[] fds = obj.getClass().getDeclaredFields();
        for (Field f : fds){
            f.setAccessible(true);
            String oldName = f.getName();
            String newName = nameMap == null ? null :nameMap.get(f.getName());
            StringBuilder sb = new StringBuilder();
            if (newName == null){
                Matcher m = HUMP2_.matcher(oldName);
                int head = 0;
                int end = 0;
                boolean flag = true;
                while(m.find()){
                    flag = false;
                    end = m.end() - 1;
                    sb.append(oldName.substring(head, end));
                    sb.append('_');
                    sb.append((char)(oldName.charAt(end) + (- 'A' + 'a')));
                    head = end + 1;
                }
                sb.append(oldName.substring(head, oldName.length()));
                newName = flag ? oldName : sb.toString();
            }
            json.put(newName, f.get(obj));
        }

    }

}
