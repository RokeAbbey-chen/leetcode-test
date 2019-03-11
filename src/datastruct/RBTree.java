package datastruct;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

public class RBTree<T extends Comparable> {
    private Node nil = new Node((T)null, null);
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    public RBTree(){
        nil.left = nil.right = nil;
    }

    public static void main(String[] args){
        RBTree<Integer> rbt = new RBTree<>();
        rbt.addAll(Arrays.asList(41, 38, 31, 12, 19, 8));
        System.out.println(rbt);
    }
    public void addAll(Collection<T> col){
        for(T c : col){ add(c); }
    }
    public void add(T v){
        Node node = new Node(v, nil);
        add(node);
    }

    private void add(Node<T> v){
        Node<T> root = nil.left;
        if(root == nil){
            nil.left = root = v;
            root.color = BLACK;
            return ;
        }

        Node<T> node = root;
        Node<T> lastNode = root;
        boolean flag = false;
        while(node != nil){
            flag = node.compareTo(v) < 0;
            lastNode = node;
            if (flag) {node = node.right;}
            else { node = node.left; }
        }
        if (flag){ lastNode.right = v; }
        else { lastNode.left = v; }
        v.parent = lastNode;
        v.color = RED;
        if(v.color == RED && lastNode.color == RED){
            addFix(v);
        }
    }
    private void addFix(Node<T> v){
        Node<T> vp = v.parent;
        while(!isRoot(v) && vp.color == RED){
            Node<T> vpp = vp.parent;
            if(vpp.left == vp){
                Node<T> r = vpp.right;
                if(r.color == RED){
                    vpp.color = RED;
                    r.color = vp.color = BLACK;
                    v = vpp;
                    vp = v.parent;
                } else {
                    if (v == vp.right) {
                        leftTransform(vp);
                        vp = v;
                    }
                    rightTransform(vpp);
                    vpp.color = RED;
                    vp.color = BLACK;
                    break;
                }
            }
            else if(vpp.right == vp){
                Node<T> l = vpp.left;
                if(l.color == RED){
                    l.color = vp.color = BLACK;
                    vpp.color = RED;
                    v = vpp;
                    vp = vpp.parent;
                }
                else{
                    if(v == vp.left){
                        rightTransform(vp);
                        vp = v;
                    }
                    leftTransform(vpp);
                    vpp.color = RED;
                    vp.color = BLACK;
                    break;
                }
            }
        }
        if(isRoot(v)){ v.color = BLACK; }
    }
    public Node<T> delete(Node<T> v){
        Node<T> root = nil.left;
        if(root == nil){ return v; }
        Node<T> node = root;
        while(node != nil){
            int flag = node.compareTo(v);
            if(flag > 0){ node = node.left; }
            else if(flag < 0){ node = node.right; }
            else { break; }
        }
        if(node == nil){ return null; }
        Node<T> target = rightMin(node);
        boolean ordColor = node.color;
        if(node.right == nil){
            target = node.left;
            if(node.parent.left == node){ node.parent.left = target; }
            else if(node.parent.right == node){ node.parent.right = target; }
        }
        else{
            if(node.right != target){
                Node<T> tempTarget = target.right;

                ordColor = target.color;
                tempTarget.parent = target.parent;
                target.parent.left = tempTarget;

                target.color = node.color;
                target.parent = node.parent;
                target.left = node.left;
                target.right = node.right;
                if(node.parent.left == node){ node.parent.left = target; }
                else if(node.parent.right == node){ node.parent.right = target; }

                target = tempTarget;
            }
            else{
                ordColor = target.color;
                target.color = node.color;
                leftTransform(target);
                target.left = node.left;
                target.left.parent = target;
            }
        }
        if(target.color){

        }



        return null;
    }
    private Node<T> rightMin(Node<T> node){
        Node<T> result = node;
        node = node.right;
        while(node != nil){
            result = node;
            node = node.left;
        }
        return result;
    }

    private boolean isRoot(Node<T> v){
        return v == nil.left;
    }

    @Override
    public String toString() {
        return "RBTree{" +
                "" + nil.left +
                '}';
    }

    private static<T extends Comparable> void leftTransform(Node<T> n){
        Node<T> p = n.parent;
        n.parent = n.right;
        n.right = n.parent.left;
        n.right.parent = n;
        n.parent.left = n;
        if(p.left == n){ p.left = n.parent; }
        else if(p.right == n){p.right = n.parent;}
    }

    private static<T extends Comparable> void rightTransform(Node<T> n){
        Node<T> p = n.parent;
        n.parent = n.left;
        n.left = n.parent.right;
        n.left.parent = n;
        n.parent.right = n;
        if(p.left == n){ p.left = n.parent; }
        else if(p.right == n){p.right = n.parent;}
    }
    private static class Node<T extends Comparable> implements Comparable<Node<T>>{
        T v;
        Node left;
        Node right;
        Node parent;
        boolean color;
        public Node(T v, Node<T> nil){
            this(v, BLACK, nil);
        }

        public Node(T v, boolean color, Node<T> nil){
            this.v = v;
            this.color = color;
            this.left = this.right = this.parent = nil == null ? this : nil;
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.v.compareTo(o.v);
        }

        @Override
        public String toString() {
            if(right == this){ return ""; }
            return "Node{" +
                    "v=" + v + " color=" + (color == RED ? "red" : "black") +
                    ", [left=" + left +
                    "], [right=" + right +
                    "]}";
        }
    }

}
