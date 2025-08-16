import com.sun.source.tree.Tree;

import java.util.Stack;

public class TreeNodeIterator<T> implements IIterator<T>{
    Stack<TreeNode<T>> stack;
    final TreeNode<T> root;

    public TreeNodeIterator(TreeNode<T> root)
    {
        this.root = root;
        this.stack = new Stack<>();
        stack.push(this.root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        TreeNode node = stack.pop();
        if(node.left != null)
            stack.push(node.left);
        if(node.right != null)
            stack.push(node.right);
        return (T) node.val;
    }
}
