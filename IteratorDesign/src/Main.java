import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static Map<Integer,String> map = Map.of(2,"abc",3,"def",4,"ghi",5,"jkl",6,"mno",
            7,"pqrs",8,"tuv",9,"wxyz");

    public static List<String> helper(String res, String digits)
    {
        List<String> list = new ArrayList<>();
        if(digits.length() == 0)
        {
            list = new ArrayList<>();
            list.add(res);
            return list;
        }

        String s = map.get(digits.charAt(0)-'0');
        IntStream chars = s.chars();

        list.addAll(s.chars()
                .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
                .flatMap(c -> helper(res + c, digits.substring(1)).stream()) // Recursively process and flatten the result
                .collect(Collectors.toList()));

        return list;
    }
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode((3));
        root.left.right = new TreeNode(4);

        System.out.println("Iterating through the tree using custom iterator");
        IIterator it = new TreeNodeIterator(root);
        while(it.hasNext())
        {
            System.out.println(it.next());
        }

        helper("",digits);
    }
}
